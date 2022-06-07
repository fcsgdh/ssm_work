package org.ali.exception;

import lombok.Data;

// 自定义的业务异常
@Data
public class BusinessException extends RuntimeException{
    private Integer code;

    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
    }


    public BusinessException(Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
