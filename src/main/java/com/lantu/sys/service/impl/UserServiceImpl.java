package com.lantu.sys.service.impl;

import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.service.IUserService;

import ch.qos.logback.core.subst.Token;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户信息表 服务实现类
 * </p>
 *
 * @author lina
 * @since 2023-04-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, Object> login(User user) {

        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        
        User loginUser=this.baseMapper.selectOne(wrapper);

        if(loginUser != null&&passwordEncoder.matches(user.getPassword(), loginUser.getPassword())){

            String key="user:"+UUID.randomUUID();

            
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key, loginUser,30,TimeUnit.MINUTES);

            Map<String,Object> data= new HashMap<>();
            data.put("token", key);
            return data;
        }


        // LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        // wrapper.eq(User::getUsername, user.getUsername());
        // wrapper.eq(User::getPassword, user.getPassword());

        // User loginUser=this.baseMapper.selectOne(wrapper);

        // if(loginUser != null){
        //     String key="user:"+UUID.randomUUID();

            
        //     loginUser.setPassword(null);
        //     redisTemplate.opsForValue().set(key, loginUser,30,TimeUnit.MINUTES);

        //     Map<String,Object> data= new HashMap<>();
        //     data.put("token", key);
        //     return data;
        // }



        return null;

    }
    @Override
    public Map<String, Object> getUserInfo(String token) {

        Object obj= redisTemplate.opsForValue().get(token);
        if(obj!=null){
            User loginUser=JSON.parseObject(JSON.toJSONString(obj),User.class);
            Map<String, Object> data=new HashMap<>();
            data.put("name", loginUser.getUsername());
            data.put("avatar", loginUser.getEmail());
            
            return data;
        }


        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void logout(String token) {
        redisTemplate.delete(token);

    }

}
