package com.sds.modules.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sds.exception.BadRequestException;
import com.sds.modules.sys.entity.Dict;
import com.sds.modules.sys.service.IDictService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-10
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/dict")
@PreAuthorize("hasRole('a')")
public class DictController {

  private final IDictService iDictService;
  private final static String ENTITY_NAME = "dict";

  @GetMapping("/dictPage")
  public ResponseEntity<Page<Dict>> dictPage(){
    return new ResponseEntity<>(iDictService.page(new Page<>()), HttpStatus.OK);
  }

  @ApiOperation("新增字典")
  @PostMapping
  public ResponseEntity<Object> create(@Validated @RequestBody Dict resources){
    if (resources.getId() != null) {
      throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
    }
    iDictService.save(resources);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
