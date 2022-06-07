package org.ali.dao;

import org.ali.entity.OutJobRecord;
import org.ali.entity.vo.EmpOutVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface OutJobRecordDao {
    @Select("select * from tis_go_out_job limit #{start},#{limit}")
    List<OutJobRecord> list(@Param("start") int start, @Param("limit") int limit);

    //增加一条请假记录
    @Insert("insert into tis_go_out_job  values (null,#{emp_id},#{type},#{begin},#{end})")
    int save(OutJobRecord addJobRecord);

    @Select("select * from tis_go_out_job where emp_id = #{eid}")
    List<OutJobRecord> findById(int eid);

    @Select("select count(*) from tis_go_out_job")
    int getTotal();

    @Select("select count(*) from tis_go_out_job where `begin` >= #{begin} and `end` < #{end}")
    int getTotalByDate(@Param("begin") Date begin, @Param("end") Date end);

    @Select("SELECT ta.id 'id',ta.`emp_id` 'emp_id',ta.`type` 'type',ta.`begin` 'begin',ta.`end` 'end',e.`name` 'name',e.`age` 'age',e.`gender` 'gender',e.`tel_num` 'tel_num',e.`name` 'name'\n" +
            "FROM tis_go_out_job ta JOIN tis_emp e WHERE e.id = ta.emp_id limit #{start},#{limit}")
    List<EmpOutVo> listNestedByPage(@Param("start") int start, @Param("limit")int limit);

    // 删除一条加班记录
    @Delete("delete from tis_go_out_job where id = #{id}")
    int delete(Integer id);

    // 修改一条请假记录
    @Update("update tis_go_out_job set emp_id = #{emp_id},type = #{type},begin=#{begin},end=#{end} where id = #{id}")
    int update(OutJobRecord addJobRecord);

    // 查询某个时间段的请假记录
    @Select("SELECT ta.id 'id',ta.`emp_id` 'emp_id',ta.`type` 'type',ta.`begin` 'begin',ta.`end` 'end',e.`name` 'name',e.`age` 'age',e.`gender` 'gender',e.`tel_num` 'tel_num',e.`name` 'name'\n" +
            "FROM tis_go_out_job ta JOIN tis_emp e WHERE e.id = ta.emp_id and ta.`begin` >= #{begin} and ta.`end` < #{end} limit #{start},#{limit}")
    List<EmpOutVo> listNestedByTime(@Param("begin") Date begin,@Param("end")Date end,@Param("start") int start,@Param("limit") int limit);

    // 跟据id查询一条请假记录
    @Select("select * from tis_go_out_job where id = #{id}")
    OutJobRecord findOne(int id);

    // 跟据emp_id 获取出差id列表
    @Select("select id from tis_go_out_job where emp_id = #{emo_id}")
    List<Integer> findIdsByEmpId(int emp_id);
}
