package org.ali.controller;

import lombok.Data;

// 统一结果处理类
@Data
public class Result {
    private Object data;
    private int code;
    private String msg;

    public Result(int code,Object data) {
        this.data = data;
        this.code = code;
    }

    public Result() {
    }

    public Result(int code,Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code) {
        this.code = code;
    }
}
