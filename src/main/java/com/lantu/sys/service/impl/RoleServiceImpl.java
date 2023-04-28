package com.lantu.sys.service.impl;

import com.lantu.sys.entity.Role;
import com.lantu.sys.mapper.RoleMapper;
import com.lantu.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色信息表 服务实现类
 * </p>
 *
 * @author lina
 * @since 2023-04-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
