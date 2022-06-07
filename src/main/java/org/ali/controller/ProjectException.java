package org.ali.controller;

import org.ali.exception.BusinessException;
import org.ali.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectException {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e) {
        return new Result(e.getCode(),null,e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e) {
        return new Result(e.getCode(),null,e.getMessage());
    }

    // 处理非预期的异常
    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception e) {
        // 记录日志
        // 发送消息给运维
        // 发送邮件给开发人员，e对象发送给开发人员
        return new Result(Code.SYSTEM_UNKNOW_ERR,"系统繁忙，请稍后再试");
    }

}
