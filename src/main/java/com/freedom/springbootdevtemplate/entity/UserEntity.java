package com.freedom.springbootdevtemplate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author freedom
 * @date 2020/10/19 17:12
 */
@Data
@TableName("user")
@ApiModel(value = "UserEntity", description = "用户实体类")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 8151663093017625061L;

    @ApiModelProperty(value = "Id")
    private Integer id;

    @ApiModelProperty(value = "名字")
    private String name;
}
