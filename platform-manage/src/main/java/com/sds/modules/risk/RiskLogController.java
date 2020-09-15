package com.sds.modules.risk;


import com.sds.dto.NewRiskLogConfirmHandleDto;
import com.sds.dto.NewRiskLogDto;
import com.sds.dto.NewRiskLogHandleDto;
import com.sds.entity.Risk;
import com.sds.entity.RiskLog;
import com.sds.redis.CurrentUser;
import com.sds.service.IRiskLogImageService;
import com.sds.service.IRiskLogService;
import com.sds.service.IRiskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/riskLog")
@RequiredArgsConstructor
@Api(tags = "安全隐患记录")
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class RiskLogController {

  private final CurrentUser currentUser;
  private final IRiskLogService riskLogService;
  private final IRiskLogImageService riskLogImageService;
  private final IRiskService riskService;

  @ApiOperation("安全隐患记录查看")
  @GetMapping("/list/{riskId}")
  public ResponseEntity list(
      @PathVariable("riskId") Long riskId){
    return ResponseEntity.ok(riskLogService.getRiskLogVoList(currentUser.getCurrentUser().getCompanyId(), riskId));
  }

  @ApiOperation("安全隐患-自主发现->[>主任直接处理<]")
  @PostMapping("/handle/{riskId}")
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity handle(@PathVariable("riskId") Long riskId,
      @Validated @RequestBody()NewRiskLogDto riskLog){
    RiskLog rl = riskLog.trans(riskId, currentUser.getCurrentUser());
    riskLogService.save(rl);
    if (riskLog.getNewRiskLogImageDto().getRiskImages().length > 0){
      riskLogImageService.saveBatch(riskLog.getNewRiskLogImageDto().trans(rl.getId(), currentUser.getCurrentUser()));
    }
    Risk risk = new Risk();
    risk.setId(riskId);
    risk.setDuplicateFlag(false);
    risk.setStatus(2);
    riskService.updateById(risk);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation("安全隐患-举报发现->[>负责人确认<]->主任处理")
  @PostMapping("/confirm/{riskId}")
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity confirm(@PathVariable("riskId") Long riskId,
      @Validated @RequestBody() NewRiskLogHandleDto riskLog){
    RiskLog rl = riskLog.trans(riskId, currentUser.getCurrentUser());
    riskLogService.save(rl);
    if (riskLog.getNewRiskLogImageDto().getRiskImages().length > 0){
      riskLogImageService.saveBatch(riskLog.getNewRiskLogImageDto().trans(rl.getId(), currentUser.getCurrentUser()));
    }
    Risk risk = new Risk();
    risk.setId(riskId);
    risk.setDuplicateFlag(riskLog.getDuplicateFlag());
    risk.setDuplicateRiskId(riskLog.getDuplicateRiskId());
    risk.setDuplicateRiskTitle(riskLog.getDuplicateRiskTitle());
    risk.setStatus(1);
    riskService.updateById(risk);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation("安全隐患-举报发现->负责人确认->[>主任处理<]")
  @PostMapping("/confirmHandle/{riskId}")
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity confirmHandle(@PathVariable("riskId") Long riskId,
      @Validated @RequestBody() NewRiskLogConfirmHandleDto riskLog){
    RiskLog rl = riskLog.trans(riskId, currentUser.getCurrentUser());
    riskLogService.save(rl);
    if (riskLog.getNewRiskLogImageDto().getRiskImages().length > 0){
      riskLogImageService.saveBatch(riskLog.getNewRiskLogImageDto().trans(rl.getId(), currentUser.getCurrentUser()));
    }
    Risk risk = new Risk();
    risk.setId(riskId);
    risk.setStatus(2);
    risk.setDuplicateFlag(riskLog.getDuplicateFlag());
    riskService.updateById(risk);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
