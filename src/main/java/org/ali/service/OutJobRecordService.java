package org.ali.service;

import org.ali.entity.OutJobRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpOutVo;

import java.util.Date;
import java.util.List;

public interface OutJobRecordService {
    PageParam<OutJobRecord> listByPage(int currentPage, int limit);
    boolean save(OutJobRecord outJobRecord);
    List<OutJobRecord> findById(int eid);
    PageParam<EmpOutVo> listNestedByPage(int currentPage, int limit, Date begin, Date end);

    // 删除一条加班记录
    int delete(Integer id);

    // 修改一条记录
    int update(OutJobRecord outJobRecord);

    OutJobRecord findOne(int id);

    List<Integer> findIdsByEmpId(int emp_id);
}
