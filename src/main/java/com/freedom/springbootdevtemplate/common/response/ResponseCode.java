package com.freedom.springbootdevtemplate.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author freedom
 * @date 2020/10/19 15:46
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS(200, "success"),

    BAD_REQUEST(400, "bad request"),

    UNAUTHORIZED(401, "unauthorized"),

    FORBIDDEN(403, "forbidden"),

    INTERNAL_SERVER_ERROR(500, "internal server error"),

    /**
     * 自定义错误码 = 错误码来源 + 三位数字编号
     * 1000~1999：1表示错误来源用户，比如参数错误等
     * 2000~2999：2表示错误来源当前系统，比如业务逻辑问题等
     * 3000~3999：3表示错误来源第三方访问，比如cdn服务错误
     */
    ACCOUNT_OR_PASSWORD_ERROR(1000, "account or password error"),

    FAILED_TO_READ_FILE(2000, "failed to read file"),

    ERROR_CALLING_THIRD_PARTY_SERVICE(3000, "error calling third-party service");

    private int code;

    private String message;
}
