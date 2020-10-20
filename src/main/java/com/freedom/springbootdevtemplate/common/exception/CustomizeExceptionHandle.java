package com.freedom.springbootdevtemplate.common.exception;

import com.freedom.springbootdevtemplate.common.response.ResponseCode;
import com.freedom.springbootdevtemplate.common.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author freedom
 * @date 2020/10/19 16:29
 * @description 自定义异常处理器
 */
@Slf4j
@RestControllerAdvice
public class CustomizeExceptionHandle {

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomizeException.class)
    public RestResponse handleCustomException(CustomizeException e) {
        log.error("", e);
        return new RestResponse(e.getCode(), e.getMessage());
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(Exception.class)
    public RestResponse handleException(Exception e) {
        log.error("", e);
        return new RestResponse(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
