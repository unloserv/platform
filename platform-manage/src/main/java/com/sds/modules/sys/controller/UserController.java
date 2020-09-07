package com.sds.modules.sys.controller;


import com.sds.modules.sys.service.IUserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
@Api(tags = "系统：用户管理")
public class UserController {

  private final IUserService iUserService;

}