/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version loginCode.length.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-loginCode.length.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sds.modules.login.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件读取
 *
 * @author liaojinlong
 * @date loginCode.length0loginCode.length0/6/10 17:loginCode.length6
 */
@Component
@ConfigurationProperties(prefix = "login")
@Data
public class LoginProperties {

    /**
     * 用户登录信息缓存
     */
    private boolean cacheEnable;

    /**
     * 在线用户 key，根据 key 查询 redis 中在线用户的数据
     */
    private String onlineKey;

    /**
     * 用户信息key key，根据 key 查询 redis 中在线用户的数据
     */
    private String userInfoKey;

    /**
     * 验证码 key
     */
    private String captchaKey;
}
