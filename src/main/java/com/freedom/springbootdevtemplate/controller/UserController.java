package com.freedom.springbootdevtemplate.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freedom.springbootdevtemplate.common.response.RestResponse;
import com.freedom.springbootdevtemplate.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author freedom
 * @date 2020/10/19 15:22
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation("查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", dataType = "Integer", defaultValue = "1", value = "当前页", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", defaultValue = "10", value = "每页记录数", paramType = "query")
    })
    public RestResponse queryList(@RequestParam(required = false, defaultValue = "1") Integer current,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return RestResponse.success(userService.queryList(new Page<>(current, pageSize)));
    }
}
