package com.sds.captcha;

import com.sds.exception.BadConfigurationException;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import java.awt.Font;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author cs
 * @date 2020/8/26
 * @description
 */
@Component
@RequiredArgsConstructor
public class CaptchaProvider {

  private final CaptchaProperties captchaProperties;

  public Long expiration() {
    return captchaProperties.getExpiration();
  }

  /**
   * 获取验证码生产类
   *
   * @return /
   */
  public Captcha getCaptcha() {
    return switchCaptcha(captchaProperties);
  }

  /**
   * 依据配置信息生产验证码
   *
   * @param captchaProperties 验证码配置信息
   * @return /
   */
  private Captcha switchCaptcha(CaptchaProperties captchaProperties) {
    Captcha captcha;
    synchronized (this) {
      switch (captchaProperties.getCodeType()) {
        case arithmetic:
          // 算术类型 https://gitee.com/whvse/EasyCaptcha
          captcha = new ArithmeticCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
          // 几位数运算，默认是两位
          captcha.setLen(captchaProperties.getLength());
          break;
        case chinese:
          captcha = new ChineseCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
          captcha.setLen(captchaProperties.getLength());
          break;
        case chinese_gif:
          captcha = new ChineseGifCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
          captcha.setLen(captchaProperties.getLength());
          break;
        case gif:
          captcha = new GifCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
          captcha.setLen(captchaProperties.getLength());
          break;
        case spec:
          captcha = new SpecCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
          captcha.setLen(captchaProperties.getLength());
          break;
        default:
          throw new BadConfigurationException("验证码配置信息错误！正确配置查看 LoginCodeEnum ");
      }
    }
    if(!StringUtils.isEmpty(captchaProperties.getFontName())){
      captcha.setFont(new Font(captchaProperties.getFontName(), Font.PLAIN, captchaProperties.getFontSize()));
    }
    return captcha;
  }
}
