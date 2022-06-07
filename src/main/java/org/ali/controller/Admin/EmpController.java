package org.ali.controller.Admin;

import org.ali.controller.Code;
import org.ali.controller.Result;
import org.ali.entity.Emp;
import org.ali.entity.PageParam;
import org.ali.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    

    @GetMapping("/{id}")
    public Result getById(@PathVariable  Integer id) {
        Emp emp = empService.findById(id);
        int code = emp!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = emp!=null ? "":"查询失败，请重试";
        return new Result(code,emp,msg);
    }

    @GetMapping("/{currentPage}/{limit}")
    public Result findAll(@PathVariable int currentPage,@PathVariable int limit,String name) {
        PageParam<Emp> empPageParam = empService.listByPage(name,currentPage, limit);
        int code = empPageParam!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = empPageParam!=null ? "":"查询失败，请重试";
        return new Result(code,empPageParam,msg);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return new Result(empService.delete(id) > 0 ? Code.DELETE_OK:Code.DELETE_ERR);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp) {
        return new Result(empService.update(emp)?Code.UPDATE_OK:Code.UPDATE_ERR);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) {
        System.out.println(emp);
        return new Result(empService.insert(emp)? Code.SAVE_OK:Code.SAVE_ERR);
    }

}
