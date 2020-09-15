package com.sds.service.impl;

import com.sds.entity.Role;
import com.sds.entity.UsersRoles;
import com.sds.mapper.UsersRolesMapper;
import com.sds.service.IUsersRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@Service
public class UsersRolesServiceImpl extends ServiceImpl<UsersRolesMapper, UsersRoles> implements IUsersRolesService {

  @Override
  public List<Role> getRoleListByUserId(Long userId) {
    return baseMapper.selectRolesByUserId(userId);
  }
}
