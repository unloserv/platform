/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.sds.security;

import com.sds.jwt.JWTProvider;
import com.sds.jwt.JWTProperties;
import com.sds.security.TokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author caoshuai
 */
@RequiredArgsConstructor
public class TokenConfigurer extends
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final JWTProvider jwtProvider;
  private final JWTProperties jwtProperties;

  @Override
  public void configure(HttpSecurity http) {
    TokenFilter customFilter = new TokenFilter(jwtProvider, jwtProperties);
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
