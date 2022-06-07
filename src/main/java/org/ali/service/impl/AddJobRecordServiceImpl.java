package org.ali.service.impl;

import org.ali.dao.AddJobRecordDao;
import org.ali.entity.AddJobRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpJobAddVo;
import org.ali.service.AddJobRecordService;
import org.ali.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddJobRecordServiceImpl implements AddJobRecordService {
    
    @Resource
    private AddJobRecordDao addJobRecordDao;
    
    @Override
    public PageParam<AddJobRecord> listByPage(int cu, int limit) {
        PageParam<AddJobRecord> pageParam = new PageParam<>(cu, limit);
        int total = addJobRecordDao.getTotal();
        pageParam.setTotal(total);
        int start = (cu - 1) * limit;
        pageParam.setPageList(addJobRecordDao.list(start,limit));
        PageParam<AddJobRecord> addJobRecordDaoPageParam = new Page<AddJobRecord>().setPages(pageParam);
        return addJobRecordDaoPageParam;
    }

    @Override
    public boolean save(AddJobRecord addJobRecord) {
        return  addJobRecordDao.save(addJobRecord) > 0;
    }

    @Override
    public List<AddJobRecord> findById(int eid) {
        return addJobRecordDao.findById(eid);
    }

    @Override
    public PageParam<EmpJobAddVo> listNestedByPage(int cu, int limit,Date begin,Date end) {
        PageParam<EmpJobAddVo> pageParam = new PageParam<>(cu, limit);
        int start = (cu - 1) * limit;
        if (begin!=null && end !=null) {
            int total = addJobRecordDao.getTotalByDate(begin,end);
            pageParam.setTotal(total);
            pageParam.setPageList(addJobRecordDao.listNestedByTime(begin,end,start,limit));
        }else {
            int total = addJobRecordDao.getTotal();
            pageParam.setTotal(total);
            pageParam.setPageList(addJobRecordDao.listNestedByPage(start,limit));
        }
        PageParam<EmpJobAddVo> addJobRecordDaoPageParam = new Page<EmpJobAddVo>().setPages(pageParam);
        return addJobRecordDaoPageParam;
    }

    // 删除一条员工记录
    @Override
    public int delete(Integer id) {
        return addJobRecordDao.delete(id);
    }

    @Override
    public int update(AddJobRecord addJobRecord) {
        return addJobRecordDao.update(addJobRecord);
    }

    @Override
    public AddJobRecord findOne(int id) {
        return addJobRecordDao.findOne(id);
    }

    @Override
    public List<Integer> findIdsByEmpId(int emp_id) {
        return findIdsByEmpId(emp_id);
    }


}
