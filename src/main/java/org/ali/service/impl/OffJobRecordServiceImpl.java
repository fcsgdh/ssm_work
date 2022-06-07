package org.ali.service.impl;

import org.ali.dao.OffJobRecordDao;
import org.ali.entity.OffJobRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpOffVo;
import org.ali.service.OffJobRecordService;
import org.ali.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OffJobRecordServiceImpl implements OffJobRecordService {
    
    @Resource
    private OffJobRecordDao offJobRecordDao;

    @Override
    public PageParam<OffJobRecord> listByPage(int cu, int limit) {
        PageParam<OffJobRecord> pageParam = new PageParam<>(cu, limit);
        int total = offJobRecordDao.getTotal();
        pageParam.setTotal(total);
        int start = (cu - 1) * limit;
        pageParam.setPageList(offJobRecordDao.list(start,limit));
        PageParam<OffJobRecord> offJobRecordDaoPageParam = new Page<OffJobRecord>().setPages(pageParam);
        return offJobRecordDaoPageParam;
    }

    @Override
    public boolean save(OffJobRecord offJobRecord) {
        return  offJobRecordDao.save(offJobRecord) > 0;
    }

    @Override
    public List<OffJobRecord> findById(int eid) {
        return offJobRecordDao.findById(eid);
    }

    @Override
    public PageParam<EmpOffVo> listNestedByPage(int cu, int limit, Date begin, Date end) {
        PageParam<EmpOffVo> pageParam = new PageParam<>(cu, limit);
        int start = (cu - 1) * limit;
        if (begin!=null && end !=null) {
            int total = offJobRecordDao.getTotalByDate(begin,end);
            pageParam.setTotal(total);
            pageParam.setPageList(offJobRecordDao.listNestedByTime(begin,end,start,limit));
        }else {
            int total = offJobRecordDao.getTotal();
            pageParam.setTotal(total);
            pageParam.setPageList(offJobRecordDao.listNestedByPage(start,limit));
        }
        PageParam<EmpOffVo> offJobRecordDaoPageParam = new Page<EmpOffVo>().setPages(pageParam);
        return offJobRecordDaoPageParam;
    }

    // 删除一条员工记录
    @Override
    public int delete(Integer id) {
        return offJobRecordDao.delete(id);
    }

    @Override
    public int update(OffJobRecord offJobRecord) {
        return offJobRecordDao.update(offJobRecord);
    }

    @Override
    public OffJobRecord findOne(int id) {
        return offJobRecordDao.findOne(id);
    }

    @Override
    public List<Integer> findIdsByEmpId(int emp_id) {
        return offJobRecordDao.findIdsByEmpId(emp_id);
    }
}
