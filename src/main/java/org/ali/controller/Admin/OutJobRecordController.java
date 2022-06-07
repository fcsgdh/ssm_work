package org.ali.controller.Admin;

import org.ali.controller.Code;
import org.ali.controller.Result;
import org.ali.entity.OutJobRecord;
import org.ali.entity.PageParam;
import org.ali.entity.vo.EmpOutVo;
import org.ali.service.OutJobRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/out_job")
public class OutJobRecordController {
    @Resource
    private OutJobRecordService outJobRecordService;

    @GetMapping("/{currentPage}/{limit}")
    public Result list(@PathVariable int currentPage, @PathVariable int limit) {
        PageParam<OutJobRecord> page = outJobRecordService.listByPage(currentPage, limit);
        int code = page!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = page!=null ? "":"查询失败，请重试";
        return new Result(code,page,msg);
    }

    @PostMapping
    public Result save(@RequestBody OutJobRecord outJobRecord) {
        boolean flag = outJobRecordService.save(outJobRecord);
        int code = flag ? Code.SAVE_OK:Code.SAVE_ERR;
        String msg = flag ? "":"查询失败，请重试";
        return new Result(code,flag,msg);
    }


    @GetMapping("/nested/{currentPage}/{limit}")
    public Result listNested(@PathVariable int currentPage,
                             @PathVariable int limit,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
        PageParam<EmpOutVo> page = outJobRecordService.listNestedByPage(currentPage,limit,begin,end);
        outJobRecordService.listNestedByPage(currentPage,limit,begin,end);
        int code = page!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = page!=null ? "":"查询失败，请重试";
        return new Result(code,page,msg);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return new Result(outJobRecordService.delete(id) > 0 ? Code.DELETE_OK:Code.DELETE_ERR);
    }

    @PutMapping
    public Result update(@RequestBody OutJobRecord outJobRecord) {
        return new Result(outJobRecordService.update(outJobRecord) > 0 ?Code.UPDATE_OK:Code.UPDATE_ERR);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable int id) {
        OutJobRecord outJobRecord = outJobRecordService.findOne(id);
        int code = outJobRecord!=null ? Code.GET_OK:Code.GET_ERR;
        String msg = outJobRecord!=null ? "":"查询失败，请重试";
        return new Result(code,outJobRecord,msg);
    }
}
