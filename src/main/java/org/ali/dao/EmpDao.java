package org.ali.dao;

import org.ali.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmpDao {
    @Select("select count(*) from tis_emp")
    int getTotal();

    @Select("select count(*) from tis_emp where name like concat('%',#{name},'%')")
    int getTotalByName(String name);

    @Select("select * from tis_emp limit #{start},#{limit}")
    List<Emp> listByPage(@Param("start") int start, @Param("limit") int limit);

    @Select("select * from tis_emp where id = #{id}")
    Emp findById(int id);

    @Select("select * from tis_emp where name like concat('%',#{name},'%') limit #{start},#{limit}")
    List<Emp> findByName(@Param("name") String name,@Param("start") int start, @Param("limit") int limit);

    @Delete("delete from tis_emp where id = #{id}")
    int delete(Integer id);

    @Update("update tis_emp set age = #{age},name = #{name},gender = #{gender} where id = #{id}")
    int update(Emp emp);

    @Insert("insert into tis_emp values(null,#{age},#{gender},#{name},#{tel_num})")
    int insert(Emp emp);
}
