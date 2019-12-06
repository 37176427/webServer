package com.heasy.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.heasy.system.model.User;

public interface UserMapper extends BaseMapper<User> {

    User getByUsername(String username);
}
