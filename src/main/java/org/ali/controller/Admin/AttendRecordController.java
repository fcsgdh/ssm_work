package org.ali.controller.Admin;

import org.ali.controller.Code;
import org.ali.controller.Result;
import org.ali.entity.AttendRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpAttendVo;
import org.ali.service.AttendRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/attend_job")
public class AttendRecordController {
    
    @Resource
    private AttendRecordService attendRecordService;

    @GetMapping("/{currentPage}/{limit}")
    public Result list(@PathVariable int currentPage, @PathVariable int limit) {
        PageParam<AttendRecord> page = attendRecordService.listByPage(currentPage, limit);
        int code = page!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = page!=null ? "":"查询失败，请重试";
        return new Result(code,page,msg);
    }

    @PostMapping
    public Result save(@RequestBody AttendRecord addJobRecord) {
        boolean flag = attendRecordService.save(addJobRecord);
        int code = flag ? Code.SAVE_OK:Code.SAVE_ERR;
        String msg = flag ? "":"查询失败，请重试";
        return new Result(code,flag,msg);
    }


    @GetMapping("/nested/{currentPage}/{limit}")
    public Result listNested(@PathVariable int currentPage,
                             @PathVariable int limit,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
        PageParam<EmpAttendVo> page = attendRecordService.listNestedByPage(currentPage,limit,begin,end);
        attendRecordService.listNestedByPage(currentPage,limit,begin,end);
        int code = page!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = page!=null ? "":"查询失败，请重试";
        return new Result(code,page,msg);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return new Result(attendRecordService.delete(id) > 0 ? Code.DELETE_OK:Code.DELETE_ERR);
    }

    @PutMapping
    public Result update(@RequestBody AttendRecord addJobRecord) {
        return new Result(attendRecordService.update(addJobRecord) > 0 ?Code.UPDATE_OK:Code.UPDATE_ERR);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable int id) {
        AttendRecord addJobRecord = attendRecordService.findOne(id);
        int code = addJobRecord!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = addJobRecord!=null ? "":"查询失败，请重试";
        return new Result(code,addJobRecord,msg);
    }
}
