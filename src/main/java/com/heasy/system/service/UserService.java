package com.heasy.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.heasy.system.model.User;

public interface UserService extends IService<User> {

    User getByUsername(String username);

}
