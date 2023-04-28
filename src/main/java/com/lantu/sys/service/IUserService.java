package com.lantu.sys.service;

import com.lantu.sys.entity.User;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户信息表 服务类
 * </p>
 *
 * @author lina
 * @since 2023-04-23
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

}
