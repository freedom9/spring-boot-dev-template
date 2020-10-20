package com.freedom.springbootdevtemplate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freedom.springbootdevtemplate.entity.UserEntity;

/**
 * @author freedom
 * @date 2020/10/19 17:26
 */
public interface UserService {

    IPage<UserEntity> queryList(Page page);
}
