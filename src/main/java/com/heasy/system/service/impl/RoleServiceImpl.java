package com.heasy.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.heasy.system.dao.RoleMapper;
import com.heasy.system.model.Role;
import com.heasy.system.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
