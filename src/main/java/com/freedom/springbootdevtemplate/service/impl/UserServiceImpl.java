package com.freedom.springbootdevtemplate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freedom.springbootdevtemplate.common.exception.CustomizeException;
import com.freedom.springbootdevtemplate.common.response.ResponseCode;
import com.freedom.springbootdevtemplate.entity.UserEntity;
import com.freedom.springbootdevtemplate.mapper.UserMapper;
import com.freedom.springbootdevtemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author freedom
 * @date 2020/10/19 17:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<UserEntity> queryList(Page page) {
        // 测试统一异常处理
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new CustomizeException(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), e);
        }
        return userMapper.selectPage(page, null);
    }
}
