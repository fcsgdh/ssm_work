package org.ali.service.impl;

import org.ali.dao.BookDao;
import org.ali.entity.Book;
import org.ali.entity.Emp;
import org.ali.entity.PageParam;
import org.ali.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements org.ali.service.BookService {

    @Resource
    private BookDao bookDao;

    @Override
    public boolean add(Book book) {
        System.out.println("save...");
        return bookDao.save(book) > 0;
    }

    @Override
    public boolean update(Book book) {
        System.out.println("update...");
        return bookDao.update(book) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println("delete...");
        return bookDao.delete(id) > 0;
    }

    @Override
    public Book findById(Integer id) {
        System.out.println("findById");
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        System.out.println("find all");
        return bookDao.list();
    }

    @Override
    public PageParam<Book> listByPage(int cu, int limit) {
        PageParam<Book> pageParam = new PageParam<>(cu, limit);
        int total = bookDao.getTotal();
        pageParam.setTotal(total);
        int start = (cu - 1) * limit;
        pageParam.setPageList(bookDao.listByPage(start,limit));
        PageParam<Book> bookPageParam = new Page<Book>().setPages(pageParam);
        return bookPageParam;
    }
}
