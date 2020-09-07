package com.sds.modules.sys.service.impl;

import com.sds.modules.sys.entity.Role;
import com.sds.modules.sys.mapper.RoleMapper;
import com.sds.modules.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
