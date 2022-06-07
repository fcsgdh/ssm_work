package org.ali.service;

import org.ali.entity.OffJobRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpOffVo;

import java.util.Date;
import java.util.List;

public interface OffJobRecordService {

    PageParam<OffJobRecord> listByPage(int currentPage, int limit);
    boolean save(OffJobRecord offJobRecord);
    List<OffJobRecord> findById(int eid);
    PageParam<EmpOffVo> listNestedByPage(int currentPage, int limit, Date begin, Date end);

    // 删除一条加班记录
    int delete(Integer id);

    // 修改一条记录
    int update(OffJobRecord offJobRecord);

    OffJobRecord findOne(int id);

    List<Integer> findIdsByEmpId(int emp_id);
}
