package com.heasy.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.heasy.system.service.MenuService;
import com.heasy.system.dao.MenuMapper;
import com.heasy.system.model.Menu;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
