package com.lantu.xadmin;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.service.IUserService;

@SpringBootTest
class XAdminApplicationTests {

	@Resource
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		List<User> users=userMapper.selectList(null);
	  	for(int i=0;i<users.size();i++){
	 		System.out.print(users);
		}

	}

	@Resource
	private IUserService userService;

	@Test
	void contextLoad() {
		List<User> users=userService.list(null);
	  	for(int i=0;i<users.size();i++){
	 		System.out.print(users);
		}

	}

}
