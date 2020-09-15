package com.sds.service;

import com.sds.entity.Role;
import com.sds.entity.UsersRoles;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 用户角色关联 服务类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
public interface IUsersRolesService extends IService<UsersRoles> {

  /**
   * 根据用户获取角色
   * @param userId
   * @return
   */
  List<Role> getRoleListByUserId(Long userId);
}
