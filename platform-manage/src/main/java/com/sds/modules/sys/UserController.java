package com.sds.modules.sys;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sds.entity.User;
import com.sds.entity.Work;
import com.sds.redis.CurrentUser;
import com.sds.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class UserController {

  private final CurrentUser currentUser;
  private final IUserService userService;

  @ApiOperation("人员列表")
  @GetMapping("/page/{pageNo}/{pageSize}")
  public ResponseEntity<Page<User>> page(
      @PathVariable("pageNo") Integer pageNo,
      @PathVariable("pageSize") Integer pageSize){
    Page<User> page = new Page<>(pageNo, pageSize);
    return ResponseEntity.ok(
        userService.lambdaQuery()
            .select(User::getId, User::getNickName, User::getGender)
            .eq(User::getCompanyId, currentUser.getCurrentUser().getCompanyId())
            .page(page)
    );
  }


}
