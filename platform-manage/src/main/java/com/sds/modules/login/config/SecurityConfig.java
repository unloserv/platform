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
package com.sds.modules.login.config;

import com.sds.annotation.AnonymousAccess;
import com.sds.jwt.JWTProvider;
import com.sds.jwt.config.JwtProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author caoshuai
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtProperties jwtProperties;
  private final JWTProvider jwtProvider;
  private final CorsFilter corsFilter;
  private final ApplicationContext applicationContext;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @Bean
  GrantedAuthorityDefaults grantedAuthorityDefaults() {
    // 去除 ROLE_ 前缀
    return new GrantedAuthorityDefaults("ROLE_");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // 密码加密方式
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // 搜寻匿名标记 url： @AnonymousAccess
    Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = applicationContext.getBean(
        RequestMappingHandlerMapping.class).getHandlerMethods();
    // 获取匿名标记
    Map<String, Set<String>> anonymousUrls = getAnonymousUrl(handlerMethodMap);
    httpSecurity
      .authorizeRequests()
        // 白名单不需要认证
      .antMatchers(AUTH_WHITELIST).permitAll()
        // 所有请求都需要认证
      .anyRequest().authenticated()
      // 禁用 CSRF
      .and()
      .csrf().disable()
      .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
      // 授权异常
      .exceptionHandling()
      .authenticationEntryPoint(jwtAuthenticationEntryPoint)
      .accessDeniedHandler(jwtAccessDeniedHandler)
      // 防止iframe 造成跨域
      .and()
      .headers()
      .frameOptions()
      .disable()
      // 不创建会话
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
      .and()
      .apply(securityConfigurerAdapter());
  }

  private Map<String, Set<String>> getAnonymousUrl(
      Map<RequestMappingInfo, HandlerMethod> handlerMethodMap) {
    Map<String, Set<String>> anonymousUrls = new HashMap<>(6);
    Set<String> get = new HashSet<>();
    Set<String> post = new HashSet<>();
    Set<String> put = new HashSet<>();
    Set<String> patch = new HashSet<>();
    Set<String> delete = new HashSet<>();
    Set<String> all = new HashSet<>();
    for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
      HandlerMethod handlerMethod = infoEntry.getValue();
      AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
      if (null != anonymousAccess) {
        List<RequestMethod> requestMethods = new ArrayList<>(
            infoEntry.getKey().getMethodsCondition().getMethods());
        RequestMethodEnum request = RequestMethodEnum.find(
            requestMethods.size() == 0 ? RequestMethodEnum.ALL.getType()
                : requestMethods.get(0).name());
        switch (Objects.requireNonNull(request)) {
          case GET:
            get.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            break;
          case POST:
            post.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            break;
          case PUT:
            put.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            break;
          case PATCH:
            patch.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            break;
          case DELETE:
            delete.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            break;
          default:
            all.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            break;
        }
      }
    }
    anonymousUrls.put(RequestMethodEnum.GET.getType(), get);
    anonymousUrls.put(RequestMethodEnum.POST.getType(), post);
    anonymousUrls.put(RequestMethodEnum.PUT.getType(), put);
    anonymousUrls.put(RequestMethodEnum.PATCH.getType(), patch);
    anonymousUrls.put(RequestMethodEnum.DELETE.getType(), delete);
    anonymousUrls.put(RequestMethodEnum.ALL.getType(), all);
    return anonymousUrls;
  }

  private TokenConfigurer securityConfigurerAdapter() {
    return new TokenConfigurer(jwtProvider, jwtProperties);
  }

  private static final String[] AUTH_WHITELIST = {
      // -- auth 认证授权相关
      "/auth/*",
      // -- swagger ui
      "/swagger-ui.html",
      "/swagger-ui/*",
      "/swagger-resources/**",
      "/v2/api-docs",
      "/v3/api-docs",
      "/webjars/**"
  };

}
