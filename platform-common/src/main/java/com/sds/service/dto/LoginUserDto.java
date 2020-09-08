package com.sds.service.dto;

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

  public LoginUserDto(String username, String password,
      Collection<? extends GrantedAuthority> authorities, String token) {
    super(username, password, authorities);
    this.token = token;
  }
}
