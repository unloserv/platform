package com.sds.mapper;

import com.sds.entity.Role;
import com.sds.entity.UsersRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户角色关联 Mapper 接口
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-14
 */
public interface UsersRolesMapper extends BaseMapper<UsersRoles> {

  @Select("SELECT * FROM sys_role r "
      + "JOIN sys_users_roles ur on ur.role_id = r.id AND ur.user_id = #{userId}")
  List<Role> selectRolesByUserId(@Param("userId") Long userId);
}
