package org.ali.service.impl;

import org.ali.dao.OutJobRecordDao;
import org.ali.entity.OutJobRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpOutVo;
import org.ali.service.OutJobRecordService;
import org.ali.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OutJobRecordServiceImpl implements OutJobRecordService {
    @Resource
    private OutJobRecordDao outJobRecordDao;

    @Override
    public PageParam<OutJobRecord> listByPage(int cu, int limit) {
        PageParam<OutJobRecord> pageParam = new PageParam<>(cu, limit);
        int total = outJobRecordDao.getTotal();
        pageParam.setTotal(total);
        int start = (cu - 1) * limit;
        pageParam.setPageList(outJobRecordDao.list(start,limit));
        PageParam<OutJobRecord> outJobRecordDaoPageParam = new Page<OutJobRecord>().setPages(pageParam);
        return outJobRecordDaoPageParam;
    }

    @Override
    public boolean save(OutJobRecord addJobRecord) {
        return  outJobRecordDao.save(addJobRecord) > 0;
    }

    @Override
    public List<OutJobRecord> findById(int eid) {
        return outJobRecordDao.findById(eid);
    }

    @Override
    public PageParam<EmpOutVo> listNestedByPage(int cu, int limit,Date begin,Date end) {
        PageParam<EmpOutVo> pageParam = new PageParam<>(cu, limit);
        int start = (cu - 1) * limit;
        if (begin!=null && end !=null) {
            int total = outJobRecordDao.getTotalByDate(begin,end);
            pageParam.setTotal(total);
            pageParam.setPageList(outJobRecordDao.listNestedByTime(begin,end,start,limit));
        }else {
            int total = outJobRecordDao.getTotal();
            pageParam.setTotal(total);
            pageParam.setPageList(outJobRecordDao.listNestedByPage(start,limit));
        }
        PageParam<EmpOutVo> outJobRecordDaoPageParam = new Page<EmpOutVo>().setPages(pageParam);
        return outJobRecordDaoPageParam;
    }

    // 删除一条员工记录
    @Override
    public int delete(Integer id) {
        return outJobRecordDao.delete(id);
    }

    @Override
    public int update(OutJobRecord addJobRecord) {
        return outJobRecordDao.update(addJobRecord);
    }

    @Override
    public OutJobRecord findOne(int id) {
        return outJobRecordDao.findOne(id);
    }

    @Override
    public List<Integer> findIdsByEmpId(int emp_id) {
        return outJobRecordDao.findIdsByEmpId(emp_id);
    }
}
