package com.sds.modules.risk;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sds.dto.NewAreaDto;
import com.sds.entity.Area;
import com.sds.redis.CurrentUser;
import com.sds.service.IAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/area")
@RequiredArgsConstructor
@Api(tags = "区域：区域管理")
@Slf4j
public class AreaController {

    private final CurrentUser currentUser;
    private final IAreaService areaService;

    @ApiOperation("新增区域")
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody NewAreaDto area){
        areaService.save(area.trans(currentUser.getCurrentUser()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("删除区域")
    @DeleteMapping("/{areaId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity remove(@PathVariable("areaId") Long areaId){
        areaService.removeById(areaId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation("区域列表")
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
    public ResponseEntity list(
            @RequestParam(value = "keyword", required = false) String keyword) {
        LambdaQueryChainWrapper<Area> qcw = areaService.lambdaQuery();
        qcw.eq(Area::getCompanyId, currentUser.getCurrentUser().getCompanyId());
        if (!StringUtils.isEmpty(keyword)) {
            qcw.like(Area::getName, keyword);
        }
        qcw.orderByAsc(Area::getSort);
        return ResponseEntity.ok(qcw.list());
    }
}
