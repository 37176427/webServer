package com.heasy.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.heasy.system.service.RoleAuthoritiesService;
import com.heasy.system.dao.RoleAuthoritiesMapper;
import com.heasy.system.model.RoleAuthorities;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018-12-19 下午 4:39.
 */
@Service
public class RoleAuthoritiesServiceImpl extends ServiceImpl<RoleAuthoritiesMapper, RoleAuthorities> implements RoleAuthoritiesService {

    @Override
    public void deleteTrash() {
        baseMapper.deleteTrash();
    }

}
