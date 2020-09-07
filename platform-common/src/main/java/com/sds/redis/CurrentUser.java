package com.sds.redis;

import com.sds.exception.BadRequestException;
import com.sds.redis.dto.OnlineUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 获取当前登录的用户
 * @author caoshuai
 * @date 2019-01-17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CurrentUser {

    private final CachedUser cachedUser;

    /**
     * 获取当前登录的用户
     * @return UserDetails
     */
    public OnlineUser getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "当前登录状态过期");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            User user = (User) authentication.getPrincipal();
            return cachedUser.getLoginUserCache(user.getUsername());
        }
        throw new BadRequestException(HttpStatus.UNAUTHORIZED, "找不到当前登录的信息");
    }

}
