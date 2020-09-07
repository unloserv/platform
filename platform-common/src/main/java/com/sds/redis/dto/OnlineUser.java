package com.sds.redis.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在线用户
 * @author caoshuai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUser implements Serializable {

  /**
   * id
   */
  private Long id;

  /**
   * 昵称
   */
  private String nickName;

  /**
   * 昵称
   */
  private String gender;

  /**
   * 手机号码
   */
  private String phone;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 头像地址
   */
  private String avatarName;

  /**
   * 头像真实路径
   */
  private String avatarPath;

  /**
   * 单位id
   */
  private Long companyId;

  /**
   * 部门id
   */
  private Long deptId;

  /**
   * 登录时间
   */
  private LocalDateTime loginTime;

  /**
   * 浏览器
   */
  private String browser;

  /**
   * IP
   */
  private String ip;

  /**
   * 地址
   */
  private String address;

}
