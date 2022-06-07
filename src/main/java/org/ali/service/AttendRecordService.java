package org.ali.service;

import org.ali.entity.AttendRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpAttendVo;

import java.util.Date;
import java.util.List;

public interface AttendRecordService {
    PageParam<AttendRecord> listByPage(int currentPage, int limit);
    boolean save(AttendRecord attendRecord);
    List<AttendRecord> findById(int eid);
    PageParam<EmpAttendVo> listNestedByPage(int currentPage, int limit, Date begin, Date end);

    // 删除一条加班记录
    int delete(Integer id);

    // 修改一条记录
    int update(AttendRecord attendRecord);

    AttendRecord findOne(int id);

    List<Integer> findIdsByEmpId(int emp_id);
}
