package com.sds.modules.sys;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sds.dto.NewCompanyDto;
import com.sds.entity.Company;
import com.sds.service.ICompanyService;
import com.sds.redis.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/sys/company")
@RequiredArgsConstructor
@Api(tags = "系统：单位管理")
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class CompanyController {

  private final CurrentUser currentUser;
  private final ICompanyService companyService;

  @ApiOperation("新增单位")
  @PostMapping()
  public ResponseEntity create(@Validated @RequestBody NewCompanyDto company){
    companyService.save(company.trans(currentUser.getCurrentUser()));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation("删除单位")
  @DeleteMapping("/{companyId}")
  public ResponseEntity<Object> remove(@PathVariable("companyId") Long companyId){
    companyService.removeById(companyId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ApiOperation("单位分页列表")
  @GetMapping("/page/{pageNo}/{pageSize}")
  public ResponseEntity page(
          @PathVariable("pageNo") Integer pageNo,
          @PathVariable("pageSize") Integer pageSize,
          @RequestParam(value = "keyword", required = false) String keyword) {
    Page<Company> page = new Page<>(pageNo, pageSize);
    LambdaQueryWrapper<Company> qw = Wrappers.lambdaQuery();
    qw.like(Company::getName, keyword).orderByAsc(Company::getSort);
    return ResponseEntity.ok(companyService.page(page, qw));
  }

}
