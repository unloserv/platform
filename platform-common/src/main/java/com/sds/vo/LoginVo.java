package com.sds.vo;

import com.sds.service.dto.LoginUserDto;
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
