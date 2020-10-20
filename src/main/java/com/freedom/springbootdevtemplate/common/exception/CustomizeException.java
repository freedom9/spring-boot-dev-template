package com.freedom.springbootdevtemplate.common.exception;

import lombok.Data;

/**
 * @author freedom
 * @date 2020/2/3 14:27
 * @description 自定义异常
 */
@Data
public class CustomizeException extends RuntimeException {

    private static final long serialVersionUID = 7519460823416942094L;

    private int code;

    private String msg;


    public CustomizeException(String msg) {
        super(msg);
    }

    public CustomizeException(String msg, Throwable e) {
        super(msg, e);
    }

    public CustomizeException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public CustomizeException(int code, Throwable e) {
        super(e);
        this.code = code;
    }

    public CustomizeException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }
}

