package com.sds.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cs
 * @date 2020/8/26
 * @description
 */
@Data
@ApiModel(value="Dept对象", description="部门")
public class CaptchaVo {

  @ApiModelProperty("验证码key")
  private String key;

  @ApiModelProperty("验证码图形（base64格式）")
  private String imgBase64;

}
