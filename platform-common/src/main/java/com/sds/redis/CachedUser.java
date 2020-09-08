package com.sds.redis;

import com.sds.jwt.JWTProperties;
import com.sds.redis.dto.OnlineUser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @author cs
 * @date 2020/8/17
 * @description 登录用户操作的redis缓存工具类 用于获取当前用户缓存的各种信息
 */
@Component
@RequiredArgsConstructor
public class CachedUser {

  private final JWTProperties jwtProperties;
  private final RedisUtils redisUtils;

  private static final String TOKEN = "token";
  private static final String userInfo = "user";
  private static final String deptInfo = "dept";
  private static final String AUTHORITY = "authority";
  private static final String roles = "roles";
  private static final String jobs = "jobs";
  private static final String ONLINE_USER_INFO = "onlineInfo";


  public String onlineKey(String onlineKeyPrefix, Long userId) {
    return onlineKeyPrefix + ":userId:" + userId;
  }

  /**
   * 用户登录状态缓存
   */
  public boolean hasLoginUserCache(String onlineKey) {
    return redisUtils.hasKey(onlineKey);
  }

  public boolean setLoginUserCache(String onlineKey, OnlineUser val) {
    return redisUtils.hset(onlineKey, ONLINE_USER_INFO, val);
  }

  public OnlineUser getLoginUserCache(String onlineKey) {
    return (OnlineUser)redisUtils.hget(onlineKey, ONLINE_USER_INFO);
  }
  /*
  public LoginUserDto getCachedUser(String token) {
    return (LoginUserDto) redisUtils.get(jwtProperties.getOnlineKey() + token);
  }

  public void putUserCache(String token, Object val) {
    redisUtils.hset(userInfoKey(token), userInfo, val);
  }

  public UserDto getUserCache(String token) {
    return (UserDto) redisUtils.hget(userInfoKey(token), userInfo);
  }

  public void putDeptCache(String token, Object val) {
    redisUtils.hset(userInfoKey(token), deptInfo, val);
  }

  public DeptSmallDto getDeptCache(String token) {
    return (DeptSmallDto) redisUtils.hget(userInfoKey(token), deptInfo);
  }
*/
  public void setAuthorityCache(String onlineKey, List<SimpleGrantedAuthority> val) {
    redisUtils.hset(onlineKey, AUTHORITY, val);
  }

  public List<SimpleGrantedAuthority> getAuthorityCache(String onlineKey) {
    return (List<SimpleGrantedAuthority>)redisUtils.hget(onlineKey, AUTHORITY);
  }
/*
  public void putRolesDtoCache(String token, Object val) {
    redisUtils.hset(userInfoKey(token), roles, val);
  }

  public void getRolesDtoCache(String token) {
    redisUtils.hget(userInfoKey(token), roles);
  }

  public void putJobsCache(String token, Object val) {
    redisUtils.hset(userInfoKey(token), jobs, val);
  }

  public void getJobsCache(String token) {
    redisUtils.hget(userInfoKey(token), jobs);
  }

  public void putOnlineInfo(String token, Object val) {
    redisUtils.hset(userInfoKey(token), onlineInfo, val);
  }

  public OnlineInfoDto getOnlineInfo(String token) {
    return (OnlineInfoDto) redisUtils.hget(userInfoKey(token), onlineInfo);
  }

  public void setExpireTime(String token, long seconds) {
    redisUtils.expire(userInfoKey(token), seconds);
  }*/
}
