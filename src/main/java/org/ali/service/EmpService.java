package org.ali.service;

import org.ali.entity.Emp;
import org.ali.entity.PageParam;


public interface EmpService {

    PageParam<Emp> listByPage(String name,int currentPage,int limit);

    Emp findById(int id);
    int delete(Integer id);

    boolean update(Emp emp);
    boolean insert(Emp emp);


}
