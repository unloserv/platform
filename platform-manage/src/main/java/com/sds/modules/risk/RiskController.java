package com.sds.modules.risk;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.sds.dto.NewRiskDto;
import com.sds.entity.Risk;
import com.sds.entity.RiskLog;
import com.sds.redis.CachedUser;
import com.sds.redis.CurrentUser;
import com.sds.service.IRiskLogImageService;
import com.sds.service.IRiskLogService;
import com.sds.service.IRiskService;
import com.sds.vo.RiskStatusCountVo;
import com.sds.vo.RiskTypeCountVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/risk")
@RequiredArgsConstructor
@Api(tags = "安全隐患")
@PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
@Slf4j
public class RiskController {

  private final CurrentUser currentUser;
  private final CachedUser cachedUser;
  private final IRiskService riskService;
  private final IRiskLogService riskLogService;
  private final IRiskLogImageService riskLogImageService;

  @ApiOperation("新增安全隐患")
  @PostMapping()
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity create(@Validated @RequestBody NewRiskDto risk){
    Risk r = risk.trans(currentUser.getCurrentUser());
    riskService.save(r);
    RiskLog rl = risk.getNewRiskLogDto().trans(r.getId(), currentUser.getCurrentUser());
    riskLogService.save(rl);
    if (risk.getNewRiskLogDto().getNewRiskLogImageDto().getRiskImages().length > 0){
      riskLogImageService.saveBatch(risk.getNewRiskLogDto().getNewRiskLogImageDto().trans(rl.getId(), currentUser.getCurrentUser()));
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation("安全隐患状态字典")
  @GetMapping("/statusDict")
  public ResponseEntity<List<RiskStatusCountVo>> statusDict(){
    return ResponseEntity.ok(riskService.getStatusCount(currentUser.getCurrentUser().getCompanyId()));
  }

  @ApiOperation("安全隐患类型字典")
  @GetMapping("/typeDict")
  public ResponseEntity<List<RiskTypeCountVo>> typeDict(
      @RequestParam(value = "status", required = false) Integer status
  ){
    return ResponseEntity.ok(riskService.getTypeCount(currentUser.getCurrentUser().getCompanyId(), status));
  }

  @ApiOperation("安全隐患列表")
  @GetMapping("/list")
  public ResponseEntity<List<Risk>> riskList(
      @RequestParam("status") Integer status,
      @RequestParam(value = "type", required = false) Integer type){
    LambdaQueryChainWrapper<Risk> lqcw = riskService.lambdaQuery();
    lqcw.eq(Risk::getCompanyId, currentUser.getCurrentUser().getCompanyId());
    if (status != null) {
      lqcw.eq(Risk::getStatus, status);
    }
    if (type != null) {
      lqcw.eq(Risk::getType, type);
    }
    lqcw.orderByDesc(Risk::getId);
    return ResponseEntity.ok(lqcw.list());
  }

}
