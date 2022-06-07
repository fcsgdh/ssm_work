package org.ali.dao;

import org.ali.entity.AttendRecord;
import org.ali.entity.vo.EmpAttendVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface AttendRecordDao {
    @Select("select * from tis_attend_record limit #{start},#{limit}")
    List<AttendRecord> list(@Param("start") int start, @Param("limit") int limit);

    //增加一条请假记录
    @Insert("insert into tis_attend_record  values (null,#{emp_id},#{begin},#{end})")
    int save(AttendRecord AttendRecord);

    @Select("select * from tis_attend_record where emp_id = #{eid}")
    List<AttendRecord> findById(int eid);

    @Select("select count(*) from tis_attend_record")
    int getTotal();

    @Select("select count(*) from tis_attend_record where `begin` >= #{begin} and `end` < #{end}")
    int getTotalByDate(@Param("begin") Date begin, @Param("end") Date end);

    @Select("SELECT ta.id 'id',ta.`emp_id` 'emp_id',ta.`begin` 'begin',ta.`end` 'end',e.`name` 'name',e.`age` 'age',e.`gender` 'gender',e.`tel_num` 'tel_num',e.`name` 'name'\n" +
            "FROM tis_attend_record ta JOIN tis_emp e WHERE e.id = ta.emp_id limit #{start},#{limit}")
    List<EmpAttendVo> listNestedByPage(@Param("start") int start, @Param("limit")int limit);

    // 删除一条加班记录
    @Delete("delete from tis_attend_record where id = #{id}")
    int delete(Integer id);

    // 修改一条请假记录
    @Update("update tis_attend_record set emp_id = #{emp_id},begin=#{begin},end=#{end} where id = #{id}")
    int update(AttendRecord AttendRecord);

    // 查询某个时间段的请假记录
    @Select("SELECT ta.id 'id',ta.`emp_id` 'emp_id,ta.`begin` 'begin',ta.`end` 'end',e.`name` 'name',e.`age` 'age',e.`gender` 'gender',e.`tel_num` 'tel_num',e.`name` 'name'\n" +
            "FROM tis_attend_record ta JOIN tis_emp e WHERE e.id = ta.emp_id and ta.`begin` >= #{begin} and ta.`end` < #{end} limit #{start},#{limit}")
    List<EmpAttendVo> listNestedByTime(@Param("begin") Date begin, @Param("end")Date end, @Param("start") int start, @Param("limit") int limit);

    // 跟据id查询一条请假记录
    @Select("select * from tis_attend_record where id = #{id}")
    AttendRecord findOne(int id);

    // 跟据emp_id获取打卡id列表
    @Select("select * from tis_attend_record where emp_id = #{emp_id}")
    List<Integer> findIdsByEmpId(int emp_id);
}
