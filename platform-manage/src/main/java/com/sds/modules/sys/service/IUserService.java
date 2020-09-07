package com.sds.modules.sys.service;

import com.sds.modules.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
public interface IUserService extends IService<User> {

  User findByName(String username);

}
