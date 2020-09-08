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
package com.sds.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sds.entity.User;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Data
@Accessors(chain = true)
public class UserDto implements Serializable {

    private Long id;

    private Long deptId;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    @JsonIgnore
    private String password;

    private Boolean enableFlag;

    @JsonIgnore
    private Boolean adminFlag;

    public static UserDto wrap(User user) {
        return new UserDto()
            .setId(user.getId())
            .setDeptId(user.getDeptId())
            .setUsername(user.getUsername())
            .setNickName(user.getNickName())
            .setEmail(user.getEmail())
            .setPhone(user.getPhone())
            .setGender(user.getGender())
            .setAvatarName(user.getAvatarName())
            .setAvatarPath(user.getAvatarPath())
            .setPassword(user.getPassword())
            .setEnableFlag(user.getEnableFlag())
            .setAdminFlag(user.getAdminFlag());
    }
}
