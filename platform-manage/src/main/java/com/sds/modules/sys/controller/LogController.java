package com.sds.modules.sys.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/sys/log")
public class LogController {

  @GetMapping("/test")
  public ResponseEntity test (){
    return ResponseEntity.ok("test");
  }
}
