package com.freedom.springbootdevtemplate.common.log;

import lombok.Data;

import java.io.Serializable;

/**
 * @author baijianliang
 * @date 2021/1/13 10:12
 */
@Data
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 8855271766420094916L;

    private String ip;

    private String url;

    private String httpMethod;

    private Object requestParams;

    private Object result;

    private Long timeCost;
}
