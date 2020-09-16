package com.sds.service.dto;

import com.sds.redis.dto.OnlineUser;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author cs
 * @date 2020/8/31
 * @description
 */
public class LoginUserDto extends User {

  @Setter
  @Getter
  private String token;

  @Getter
  @Setter
  private OnlineUser onlineUser;

  public LoginUserDto(String username, String password,
      Collection<? extends GrantedAuthority> authorities,
      String token, OnlineUser onlineUser) {
    super(username, password, authorities);
    this.token = token;
    this.onlineUser = onlineUser;
  }
}
