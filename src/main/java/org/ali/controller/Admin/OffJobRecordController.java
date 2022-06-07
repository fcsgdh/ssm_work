package org.ali.controller.Admin;

import org.ali.controller.Code;
import org.ali.controller.Result;
import org.ali.entity.OffJobRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpOffVo;
import org.ali.service.OffJobRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/off_job")
public class OffJobRecordController {
    @Resource
    private OffJobRecordService offJobRecordService;

    @GetMapping("/{currentPage}/{limit}")
    public Result list(@PathVariable int currentPage, @PathVariable int limit) {
        PageParam<OffJobRecord> page = offJobRecordService.listByPage(currentPage, limit);
        int code = page!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = page!=null ? "":"查询失败，请重试";
        return new Result(code,page,msg);
    }

    @PostMapping
    public Result save(@RequestBody OffJobRecord addJobRecord) {
        boolean flag = offJobRecordService.save(addJobRecord);
        int code = flag ? Code.SAVE_OK:Code.SAVE_ERR;
        String msg = flag ? "":"查询失败，请重试";
        return new Result(code,flag,msg);
    }


    @GetMapping("/nested/{currentPage}/{limit}")
    public Result listNested(@PathVariable int currentPage,
                             @PathVariable int limit,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
        PageParam<EmpOffVo> page = offJobRecordService.listNestedByPage(currentPage,limit,begin,end);
        offJobRecordService.listNestedByPage(currentPage,limit,begin,end);
        int code = page!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = page!=null ? "":"查询失败，请重试";
        return new Result(code,page,msg);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return new Result(offJobRecordService.delete(id) > 0 ? Code.DELETE_OK:Code.DELETE_ERR);
    }

    @PutMapping
    public Result update(@RequestBody OffJobRecord addJobRecord) {
        return new Result(offJobRecordService.update(addJobRecord) > 0 ?Code.UPDATE_OK:Code.UPDATE_ERR);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable int id) {
        OffJobRecord addJobRecord = offJobRecordService.findOne(id);
        int code = addJobRecord!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = addJobRecord!=null ? "":"查询失败，请重试";
        return new Result(code,addJobRecord,msg);
    }
}
