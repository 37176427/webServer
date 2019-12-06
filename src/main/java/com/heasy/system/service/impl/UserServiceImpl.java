package com.heasy.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.heasy.system.dao.UserMapper;
import com.heasy.system.model.User;
import com.heasy.system.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

}
