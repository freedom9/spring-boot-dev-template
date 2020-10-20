package com.freedom.springbootdevtemplate.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author freedom
 * @date 2020/2/27 11:12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = 4598682035977911928L;

    /**
     * 响应码
     */
    private int code;
    /**
     * 内容
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestResponse success() {
        return new RestResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }
}
