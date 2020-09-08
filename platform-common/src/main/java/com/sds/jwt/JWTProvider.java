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
package com.sds.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sds.redis.CachedUser;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @author /
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JWTProvider implements InitializingBean {

    private final JWTProperties jwtProperties;
    private final CachedUser cachedUser;
    public static final String onlineUserinfoKey = "onlineUserInfo";
    private Algorithm algorithm;

    @Override
    public void afterPropertiesSet() {
        this.algorithm = Algorithm.HMAC512(jwtProperties.getBase64Secret());
    }

    /**
     * 创建Token 设置永不过期，
     * Token 的时间有效性转到Redis 维护
     *
     * @param onlineUserKey
     * @return token
     */
    public String createToken(String onlineUserKey) {
        return JWT.create()
            // 加入ID确保生成的 Token 都不一致
            .withJWTId(UUID.randomUUID().toString())
            .withClaim(onlineUserinfoKey, onlineUserKey)
            .sign(algorithm);
    }

    /**
     * 依据Token 获取鉴权信息
     *
     * @param token /
     * @return /
     */
    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String onlineKey = decodedJWT.getClaim(onlineUserinfoKey).asString();
        List<SimpleGrantedAuthority> authorities =
            cachedUser.getAuthorityCache(onlineKey).stream().collect(Collectors.toList());
        User principal = new User(onlineKey, "******", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

/**
     * @param token 需要检查的token
     *//*
    public void checkRenewal(String token) {
        // 判断是否续期token,计算token的过期时间
        long leftSeconds = redisUtils.getExpire(properties.getOnlineKey() + token);
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(leftSeconds);
        long time = expireTime.getSecond();
        // 判断当前时间与过期时间的时间差
        long diff = time - LocalDateTime.now().getSecond();
        // 如果在续期检查的范围内，则续期
        if (diff <= properties.getDetect()) {
            long renew = time + properties.getRenew();
            redisUtils.expire(properties.getOnlineKey() + token, renew);
        }
    }

    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(properties.getTokenStartWith().length());
        }
        return null;
    }*/
}
