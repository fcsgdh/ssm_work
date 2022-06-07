package org.ali.dao;

import org.ali.entity.Book;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface BookDao {
    @Insert("insert into book(type,name,description) values(#{type},#{name},#{description})")
    int save(Book book);
    @Delete("delete from book where id = #{id}")
    int delete(Integer id);
    @Update("update book set type = #{type},name = #{name},description = #{description} where id = #{id}")
    int update(Book book);
    @Select("select * from book")
    List<Book> list();
    @Select("SELECT * FROM book WHERE id = #{id}")
    Book findById(Integer id);
    @Select("select * from book limit #{start},#{limit}")
    List<Book> listByPage(@Param("start") int start,@Param("limit") int limit);
    @Select("select count(*) from book")
    int getTotal();
}
