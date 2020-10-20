package com.freedom.springbootdevtemplate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freedom.springbootdevtemplate.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author freedom
 * @date 2020/10/19 17:17
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
