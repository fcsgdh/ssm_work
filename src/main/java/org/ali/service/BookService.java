package org.ali.service;

import org.ali.entity.Book;
import org.ali.entity.PageParam;

import java.util.List;

public interface BookService {
    boolean add(Book book);
    boolean update(Book book);
    boolean delete(Integer id);
    Book findById(Integer id);
    List<Book> findAll();
    PageParam<Book> listByPage(int start,int limit);
}
