package com.sds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sds.entity.User;
import com.sds.mapper.UserMapper;
import com.sds.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  @Override
  public User findByName(String username) {
    LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(User::getUsername, username);
    return baseMapper.selectOne(queryWrapper);
  }

}
