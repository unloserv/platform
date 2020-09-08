package com.sds.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author caoshuai
 */
@Data
public class AuthUserDto {

    @Size(min = 5, max = 16, message = "用户名长度为6-16位")
    private String username;

    private String password;

    @NotBlank(message = "验证码不能为空")
    private String code;

    @NotBlank(message = "captchaKey can not be null")
    private String captchaKey = "";

}
