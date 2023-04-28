package com.lantu.sys.mapper;

import com.lantu.sys.entity.User;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统用户信息表 Mapper 接口
 * </p>
 *
 * @author lina
 * @since 2023-04-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
