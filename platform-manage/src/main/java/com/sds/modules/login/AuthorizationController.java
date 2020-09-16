package com.sds.modules.login;

import com.sds.annotation.AnonymousAccess;
import com.sds.captcha.CaptchaProvider;
import com.sds.redis.CachedUser;
import com.sds.rsa.RsaProperties;
import com.sds.exception.BadRequestException;
import com.sds.jwt.JWTProperties;
import com.sds.security.LoginProperties;
import com.sds.vo.CaptchaVo;
import com.sds.service.dto.AuthUserDto;
import com.sds.service.dto.LoginUserDto;
import com.sds.redis.RedisUtils;
import com.sds.utils.RsaUtils;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cs
 * @date 2020/8/11
 * @description
 */
@Slf4j
@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

  private final RedisUtils redisUtils;
  private final CaptchaProvider captchaProvider;

  private final JWTProperties jwtProperties;
  private final LoginProperties loginProperties;

  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final CachedUser cachedUser;


  @ApiOperation("获取验证码")
  @AnonymousAccess()
  @GetMapping(value = "/code")
  public ResponseEntity<CaptchaVo> getCode() {
    // 获取运算的结果
    Captcha captcha = captchaProvider.getCaptcha();
    String uuid = loginProperties.getCaptchaKey() + ":" + UUID.randomUUID().toString();
    // 保存
    redisUtils.set(uuid, captcha.text(), captchaProvider.expiration());
    // 验证码vo封装
    CaptchaVo captchaVo = new CaptchaVo();
    captchaVo.setKey(uuid);
    captchaVo.setImgBase64(captcha.toBase64());
    return ResponseEntity.ok(captchaVo);
  }

  @ApiOperation("登录授权")
  @AnonymousAccess()
  @PostMapping(value = "/authorize")
  public ResponseEntity<Object> authorize(@Validated @RequestBody AuthUserDto authUser) throws Exception {
    // 密码解密
    String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
    // 查询验证码
    String code = (String) redisUtils.get(authUser.getCaptchaKey());
    if (StringUtils.isEmpty(code)) {
      throw new BadRequestException("验证码不存在或已过期");
    }
    // 清除验证码
    redisUtils.del(authUser.getCaptchaKey());
    if (StringUtils.isEmpty(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
      throw new BadRequestException("验证码错误");
    }
    // 1 创建UsernamePasswordAuthenticationToken
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
    // 2 认证
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    // 3 保存认证信息
    // 不保存 用token+redis处理
    // SecurityContextHolder.getContext().setAuthentication(authentication);
    // 4 加载UserDetails
    final LoginUserDto loginUserDto = (LoginUserDto) authentication.getPrincipal();
    // 返回 token 与 用户信息
    Map<String, Object> userInfo = new HashMap<String, Object>(2) {{
      put("token", jwtProperties.getTokenStartWith() + loginUserDto.getToken());
      put("auth", loginUserDto.getAuthorities());
      put("onlineUser", loginUserDto.getOnlineUser());
    }};
    return ResponseEntity.ok(userInfo);
  }

}
