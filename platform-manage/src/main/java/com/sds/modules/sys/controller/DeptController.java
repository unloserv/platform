package com.sds.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sds.modules.sys.controller.dto.EditDeptDto;
import com.sds.modules.sys.controller.dto.NewDeptDto;
import com.sds.modules.sys.entity.Dept;
import com.sds.modules.sys.service.IDeptService;
import com.sds.redis.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/sys/dept")
@RequiredArgsConstructor
@Api(tags = "系统：部门管理")
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class DeptController {

  private final CurrentUser currentUser;
  private final IDeptService deptService;

  @ApiOperation("新增部门")
  @PostMapping()
  public ResponseEntity create(
      @Validated @RequestBody NewDeptDto dept) {
    deptService.save(dept.trans(currentUser.getCurrentUser()));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation("删除部门")
  @DeleteMapping("/{deptId}")
  public ResponseEntity remove(@PathVariable("deptId") Long deptId) {
    deptService.removeById(deptId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @ApiOperation("修改部门")
  @PutMapping
  public ResponseEntity update(@RequestBody EditDeptDto dept){
    deptService.updateById(dept.trans(currentUser.getCurrentUser()));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @ApiOperation("部门列表")
  @GetMapping("/list")
  public ResponseEntity list(
      @RequestParam(value = "pid", required = false) Long pid,
      @RequestParam(value = "keyword", required = false) String keyword) {
    LambdaQueryWrapper<Dept> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(Dept::getCompanyId, currentUser.getCurrentUser().getCompanyId());
    if (!StringUtils.isEmpty(keyword)) {
      queryWrapper.like(Dept::getName, keyword);
    }
    if (pid != null) {
      queryWrapper.eq(Dept::getPid, pid);
    }
    queryWrapper.orderByAsc(Dept::getSort);
    return ResponseEntity.ok(deptService.list(queryWrapper));
  }
}
