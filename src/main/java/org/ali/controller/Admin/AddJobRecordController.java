package org.ali.controller.Admin;

import org.ali.controller.Code;
import org.ali.controller.Result;
import org.ali.entity.AddJobRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpJobAddVo;
import org.ali.service.AddJobRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/add_job")
public class AddJobRecordController {

    @Resource
    private AddJobRecordService addJobRecordService;
    
    @GetMapping("/{currentPage}/{limit}")
    public Result list(@PathVariable int currentPage, @PathVariable int limit) {
        PageParam<AddJobRecord> page = addJobRecordService.listByPage(currentPage, limit);
        int code = page!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = page!=null ? "":"查询失败，请重试";
        return new Result(code,page,msg);
    }

    @PostMapping
    public Result save(@RequestBody AddJobRecord addJobRecord) {
        boolean flag = addJobRecordService.save(addJobRecord);
        int code = flag ? Code.SAVE_OK:Code.SAVE_ERR;
        String msg = flag ? "":"查询失败，请重试";
        return new Result(code,flag,msg);
    }


    @GetMapping("/nested/{currentPage}/{limit}")
    public Result listNested(@PathVariable int currentPage,
                             @PathVariable int limit,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
        PageParam<EmpJobAddVo> page = addJobRecordService.listNestedByPage(currentPage,limit,begin,end);
        addJobRecordService.listNestedByPage(currentPage,limit,begin,end);
        int code = page!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = page!=null ? "":"查询失败，请重试";
        return new Result(code,page,msg);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return new Result(addJobRecordService.delete(id) > 0 ? Code.DELETE_OK:Code.DELETE_ERR);
    }

    @PutMapping
    public Result update(@RequestBody AddJobRecord addJobRecord) {
        return new Result(addJobRecordService.update(addJobRecord) > 0 ?Code.UPDATE_OK:Code.UPDATE_ERR);
    }
    
    @GetMapping("/{id}")
    public Result findOne(@PathVariable int id) {
        AddJobRecord addJobRecord = addJobRecordService.findOne(id);
        int code = addJobRecord!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = addJobRecord!=null ? "":"查询失败，请重试";
        return new Result(code,addJobRecord,msg);
    } 

}
