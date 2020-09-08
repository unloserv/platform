package com.sds.modules.risk;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sds.dto.NewAreaAdminDto;
import com.sds.dto.NewCompanyDto;
import com.sds.dto.NewRiskDto;
import com.sds.entity.Company;
import com.sds.entity.Risk;
import com.sds.entity.RiskLog;
import com.sds.redis.CurrentUser;
import com.sds.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/risk")
@RequiredArgsConstructor
@Api(tags = "安全隐患")
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class RiskController {

  private final CurrentUser currentUser;
  private final IRiskService riskService;
  private final IRiskLogService riskLogService;
  private final IRiskLogImageService riskLogImageService;
  private final IDictDetailService dictDetailService;

  @ApiOperation("新增安全隐患")
  @PostMapping()
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity create(@Validated @RequestBody NewRiskDto risk){
    Risk r = risk.trans(currentUser.getCurrentUser());
    riskService.save(r);
    RiskLog rl = risk.getNewRiskLogDto().trans(r.getId(), currentUser.getCurrentUser());
    riskLogService.save(rl);
    if (risk.getNewRiskLogImageDto().getRiskImages().length > 0){
      riskLogImageService.saveBatch(risk.getNewRiskLogImageDto().trans(rl.getId(), currentUser.getCurrentUser()));
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation("安全隐患状态字典")
  @GetMapping("/statusDict")
  public ResponseEntity statusDict(){
    return ResponseEntity.ok(riskService.getStatusCount(currentUser.getCurrentUser().getCompanyId()));
  }


}
