package org.ali.dao;

import org.ali.entity.Admin;
import org.apache.ibatis.annotations.Select;

public interface AdminDao {
    @Select("select count(*) from admin where username = #{id} and password = #{password}")
    int findByUsernameAndPassword(Admin admin);
}
