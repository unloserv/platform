package com.sds.captcha.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 登录验证码配置信息
 *
 * @author: caoshuai
 * @date: 2020/6/10 18:53
 */
@Component
@ConfigurationProperties(prefix = "captcha")
@Data
public class CaptchaProperties {

  /**
   * 验证码配置
   */
  private CaptchaType codeType;
  /**
   * 验证码有效期 分钟
   */
  private Long expiration = 2L;
  /**
   * 验证码内容长度
   */
  private int length = 2;
  /**
   * 验证码宽度
   */
  private int width = 111;
  /**
   * 验证码高度
   */
  private int height = 36;
  /**
   * 验证码字体
   */
  private String fontName;
  /**
   * 字体大小
   */
  private int fontSize = 25;

  public CaptchaType getCodeType() {
    return codeType;
  }
}
