package com.sds.modules.login.controller.vo;

import com.sds.modules.login.service.dto.LoginUserDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author cs
 * @date 2020/8/27
 * @description
 */
@Data
@ApiModel(value="登录状态对象", description="部门")
public class LoginVo {

  private LoginUserDto loginUserDto;

  private String jwtToken;
}
