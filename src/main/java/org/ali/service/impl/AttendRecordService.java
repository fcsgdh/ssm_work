package org.ali.service.impl;

import org.ali.dao.AttendRecordDao;
import org.ali.entity.AttendRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpAttendVo;
import org.ali.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AttendRecordService implements org.ali.service.AttendRecordService {
    @Resource
    private AttendRecordDao attendRecordDao;

    @Override
    public PageParam<AttendRecord> listByPage(int cu, int limit) {
        PageParam<AttendRecord> pageParam = new PageParam<>(cu, limit);
        int total = attendRecordDao.getTotal();
        pageParam.setTotal(total);
        int start = (cu - 1) * limit;
        pageParam.setPageList(attendRecordDao.list(start,limit));
        PageParam<AttendRecord> attendRecordDaoPageParam = new Page<AttendRecord>().setPages(pageParam);
        return attendRecordDaoPageParam;
    }

    @Override
    public boolean save(AttendRecord addJobRecord) {
        return  attendRecordDao.save(addJobRecord) > 0;
    }

    @Override
    public List<AttendRecord> findById(int eid) {
        return attendRecordDao.findById(eid);
    }

    @Override
    public PageParam<EmpAttendVo> listNestedByPage(int cu, int limit, Date begin, Date end) {
        PageParam<EmpAttendVo> pageParam = new PageParam<>(cu, limit);
        int start = (cu - 1) * limit;
        if (begin!=null && end !=null) {
            int total = attendRecordDao.getTotalByDate(begin,end);
            pageParam.setTotal(total);
            pageParam.setPageList(attendRecordDao.listNestedByTime(begin,end,start,limit));
        }else {
            int total = attendRecordDao.getTotal();
            pageParam.setTotal(total);
            pageParam.setPageList(attendRecordDao.listNestedByPage(start,limit));
        }
        PageParam<EmpAttendVo> attendRecordDaoPageParam = new Page<EmpAttendVo>().setPages(pageParam);
        return attendRecordDaoPageParam;
    }

    // 删除一条员工记录
    @Override
    public int delete(Integer id) {
        return attendRecordDao.delete(id);
    }

    @Override
    public int update(AttendRecord addJobRecord) {
        return attendRecordDao.update(addJobRecord);
    }

    @Override
    public AttendRecord findOne(int id) {
        return attendRecordDao.findOne(id);
    }

    @Override
    public List<Integer> findIdsByEmpId(int emp_id) {
        return attendRecordDao.findIdsByEmpId(emp_id);
    }
}
