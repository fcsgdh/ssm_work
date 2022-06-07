package org.ali.controller.Admin;

import org.ali.controller.Code;
import org.ali.controller.Result;
import org.ali.entity.Book;
import org.ali.entity.Emp;
import org.ali.entity.PageParam;
import org.ali.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Result save(@RequestBody Book book) {
        System.out.println(book);
        return new Result(bookService.add(book)? Code.SAVE_OK:Code.SAVE_ERR);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return new Result(bookService.delete(id)?Code.DELETE_OK:Code.DELETE_ERR);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        return new Result(bookService.update(book)?Code.UPDATE_OK:Code.UPDATE_ERR);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable  Integer id) {
        Book book = bookService.findById(id);
        int code = book!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = book!=null ? "":"查询失败，请重试";
        return new Result(code,book,msg);
    }

    @GetMapping
    public Result findAll() {
        List<Book> all = bookService.findAll();
        int code = all!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = all!=null ? "":"查询失败，请重试";
        return new Result(code,all,msg);
    }

    @GetMapping("/{page}/{limit}")
    public Result listByPage(@PathVariable int page,@PathVariable int limit) {
        PageParam<Book> bookPageParam = bookService.listByPage(page, limit);
        int code = bookPageParam!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = bookPageParam!=null ? "":"查询失败，请重试";
        return new Result(code,bookPageParam,msg);
    }
    
}
