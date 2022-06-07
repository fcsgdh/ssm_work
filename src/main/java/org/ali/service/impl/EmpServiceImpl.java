package org.ali.service.impl;

import org.ali.dao.*;
import org.ali.entity.*;
import org.ali.service.EmpService;
import org.ali.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Resource
    private EmpDao empDao;
    @Resource
    private AddJobRecordDao addJobRecordDao;
    @Resource
    private AttendRecordDao attendRecordDao;
    @Resource
    private OutJobRecordDao outJobRecordDao;
    @Resource
    private OffJobRecordDao offJobRecordDao;


    @Override
    public PageParam<Emp> listByPage(String name,int cu,int limit) {
        PageParam<Emp> pageParam = new PageParam<>(cu, limit);

        int start = (cu - 1) * limit;
        if (name==null || name.equals("")) {
            int total = empDao.getTotal();
            pageParam.setTotal(total);
            pageParam.setPageList(empDao.listByPage(start,limit));
        }else {
            int total = empDao.getTotalByName(name);
            pageParam.setTotal(total);
            pageParam.setPageList(empDao.findByName(name,start,limit));
        }

        PageParam<Emp> empPageParam = new Page<Emp>().setPages(pageParam);
        return empPageParam;
    }

    /**
     * 删除用户的一切信息，关联的其他四张表都需要删除
     * @param id
     * @return
     */
    @Override
    public Emp findById(int id) {
        return empDao.findById(id);
    }

    @Override
    public int delete(Integer id) {
        // to do 加事务 非常多的sql操作
        List<Integer> aids = addJobRecordDao.findIdsByEmpId(id);
        List<Integer> arids = attendRecordDao.findIdsByEmpId(id);
        List<Integer> oids = outJobRecordDao.findIdsByEmpId(id);
        List<Integer> ojrids = offJobRecordDao.findIdsByEmpId(id);
        aids.forEach(addJobRecordDao::delete);
        arids.forEach(attendRecordDao::delete);
        oids.forEach(outJobRecordDao::delete);
        ojrids.forEach(offJobRecordDao::delete);
        return empDao.delete(id);
    }

    @Override
    public boolean update(Emp emp) {
        return empDao.update(emp) > 0;
    }

    @Override
    public boolean insert(Emp emp) {
        return  empDao.insert(emp) > 0;
    }

}
