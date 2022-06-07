package org.ali.dao;

import org.ali.entity.OffJobRecord;
import org.ali.entity.vo.EmpJobAddVo;
import org.ali.entity.vo.EmpOffVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface OffJobRecordDao {
    @Select("select * from tis_off_job limit #{start},#{limit}")
    List<OffJobRecord> list(@Param("start") int start, @Param("limit") int limit);

    //增加一条请假记录
    @Insert("insert into tis_off_job  values (null,#{emp_id},#{type},#{begin},#{end})")
    int save(OffJobRecord addJobRecord);

    @Select("select * from tis_off_job where emp_id = #{eid}")
    List<OffJobRecord> findById(int eid);

    @Select("select count(*) from tis_off_job")
    int getTotal();

    @Select("select count(*) from tis_off_job where `begin` >= #{begin} and `end` < #{end}")
    int getTotalByDate(@Param("begin") Date begin, @Param("end") Date end);

    @Select("SELECT ta.id 'id',ta.`emp_id` 'emp_id',ta.`type` 'type',ta.`begin` 'begin',ta.`end` 'end',e.`name` 'name',e.`age` 'age',e.`gender` 'gender',e.`tel_num` 'tel_num',e.`name` 'name'\n" +
            "FROM tis_off_job ta JOIN tis_emp e WHERE e.id = ta.emp_id limit #{start},#{limit}")
    List<EmpOffVo> listNestedByPage(@Param("start") int start, @Param("limit")int limit);

    // 删除一条加班记录
    @Delete("delete from tis_off_job where id = #{id}")
    int delete(Integer id);

    // 修改一条请假记录
    @Update("update tis_off_job set emp_id = #{emp_id},type = #{type},begin=#{begin},end=#{end} where id = #{id}")
    int update(OffJobRecord addJobRecord);

    // 查询某个时间段的请假记录
    @Select("SELECT ta.id 'id',ta.`emp_id` 'emp_id',ta.`type` 'type',ta.`begin` 'begin',ta.`end` 'end',e.`name` 'name',e.`age` 'age',e.`gender` 'gender',e.`tel_num` 'tel_num',e.`name` 'name'\n" +
            "FROM tis_off_job ta JOIN tis_emp e WHERE e.id = ta.emp_id and ta.`begin` >= #{begin} and ta.`end` < #{end} limit #{start},#{limit}")
    List<EmpOffVo> listNestedByTime(@Param("begin") Date begin,@Param("end")Date end,@Param("start") int start,@Param("limit") int limit);

    // 跟据id查询一条请假记录
    @Select("select * from tis_off_job where id = #{id}")
    OffJobRecord findOne(int id);

    // 跟据emp_id获取请假id列表
    @Select("select id from tis_off_job where id = #{emp_id}")
    List<Integer> findIdsByEmpId(int emp_id);
}
