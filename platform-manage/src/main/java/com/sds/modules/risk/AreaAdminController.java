package com.sds.modules.risk;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sds.dto.NewAreaAdminDto;
import com.sds.dto.NewAreaDto;
import com.sds.entity.Area;
import com.sds.entity.AreaAdmin;
import com.sds.entity.User;
import com.sds.redis.CurrentUser;
import com.sds.service.IAreaAdminService;
import com.sds.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/area/admin")
@RequiredArgsConstructor
@Api(tags = "区域：区域管理员管理")
@Slf4j
public class AreaAdminController {

    private final CurrentUser currentUser;
    private final IAreaAdminService areaAdminService;
    private final IUserService userService;

    @ApiOperation("新增区域管理员")
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody NewAreaAdminDto areaAdmin){
        areaAdminService.save(areaAdmin.trans(currentUser.getCurrentUser()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("删除区域管理员")
    @DeleteMapping("/{adminAreaId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> remove(@PathVariable("adminAreaId") Long adminAreaId){
        areaAdminService.removeById(adminAreaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("区域管理员列表")
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
    public ResponseEntity list(
            @RequestParam("areaId") String areaId) {
        LambdaQueryWrapper<AreaAdmin> qw = Wrappers.lambdaQuery();
        qw.eq(AreaAdmin::getCompanyId, currentUser.getCurrentUser().getCompanyId()).eq(AreaAdmin::getAreaId, areaId);
        return ResponseEntity.ok(areaAdminService.list(qw));
    }

    @ApiOperation("人员列表")
    @GetMapping("/userList")
    @PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
    public ResponseEntity<List<User>> userList() {
        return ResponseEntity.ok(
                userService.lambdaQuery()
                        .select(User::getId, User::getNickName, User::getGender)
                        .eq(User::getCompanyId, currentUser.getCurrentUser().getCompanyId())
                        .list()
        );
    }
}
