package com.heasy.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.heasy.system.model.RoleAuthorities;

public interface RoleAuthoritiesMapper extends BaseMapper<RoleAuthorities> {

    int deleteTrash();
}
