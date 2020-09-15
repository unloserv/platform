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
package com.sds.service.impl;

import com.sds.entity.User;
import com.sds.exception.BadRequestException;
import com.sds.jwt.JWTProvider;
import com.sds.redis.CachedUser;
import com.sds.redis.dto.OnlineUser;
import com.sds.security.LoginProperties;
import com.sds.service.IUserService;
import com.sds.service.IUsersRolesService;
import com.sds.service.dto.LoginUserDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author caoshuai
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserService userService;
    private final CachedUser cachedUser;
    private final JWTProvider jwtProvider;
    private final LoginProperties loginProperties;
    private final IUsersRolesService usersRolesService;

    @Override
    public LoginUserDto loadUserByUsername(String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        if (!user.getEnableFlag()) {
            throw new BadRequestException("账号未激活！");
        }
        String onlineKey = cachedUser.onlineKey(loginProperties.getOnlineKey(), user.getId());
        // 生成令牌
        String token = jwtProvider.createToken(onlineKey);
        if (loginProperties.isCacheEnable()) {
            boolean hasCachedUserInfo = cachedUser.hasLoginUserCache(onlineKey);
            if (!hasCachedUserInfo){
                OnlineUser onlineUser = new OnlineUser();
                onlineUser.setId(user.getId());
                onlineUser.setCompanyId(user.getCompanyId());
                onlineUser.setNickName(user.getNickName());
                onlineUser.setGender(user.getGender());
                onlineUser.setPhone(user.getPhone());
                onlineUser.setEmail(user.getEmail());
                onlineUser.setAvatarName(user.getAvatarName());
                onlineUser.setAvatarPath(user.getAvatarPath());
                onlineUser.setLoginTime(LocalDateTime.now());
                onlineUser.setBrowser("browser");
                onlineUser.setIp("ip");
                onlineUser.setAddress("address");
                cachedUser.setLoginUserCache(onlineKey, onlineUser);
                // 注入用户信息
                cachedUser.setAuthorityCache(onlineKey, getAuthority(user.getId()));
            }
        }
        return new LoginUserDto(
            user.getUsername(),
            user.getPassword(),
            getAuthority(user.getId()),
            token);
    }

    private List<SimpleGrantedAuthority> getAuthority(Long userId){
        return usersRolesService.getRoleListByUserId(userId).stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }

}
