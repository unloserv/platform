package com.sds.modules.work;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.sds.dto.NewRiskDto;
import com.sds.dto.NewWorkDto;
import com.sds.entity.Risk;
import com.sds.entity.RiskLog;
import com.sds.entity.Work;
import com.sds.entity.WorkLog;
import com.sds.redis.CurrentUser;
import com.sds.service.IRiskLogService;
import com.sds.service.IWorkLogAttachmentService;
import com.sds.service.IWorkLogService;
import com.sds.service.IWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 *  前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/work")
@RequiredArgsConstructor
@Api(tags = "工作管理")
@PreAuthorize("hasAnyRole('ADMIN', 'NORMAL')")
@Slf4j
public class WorkController {

  private final CurrentUser currentUser;
  private final IWorkService workService;
  private final IWorkLogService workLogService;
  private final IWorkLogAttachmentService workLogAttachmentService;

  @ApiOperation("新增工作")
  @PostMapping()
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity create(@Validated @RequestBody NewWorkDto work){
    Work w = work.trans(currentUser.getCurrentUser());
    workService.save(w);
    WorkLog wl = work.getNewWorkLogDto().trans(w.getId(), currentUser.getCurrentUser());
    workLogService.save(wl);
    if (work.getNewWorkLogDto().getAttachmentList().size() > 0){
      workLogAttachmentService.saveBatch(work.getNewWorkLogDto().getAttachmentList(wl.getId(), currentUser.getCurrentUser()));
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation("工作列表")
  @GetMapping("/list")
  public ResponseEntity<List<Work>> riskList(
      @ApiParam(required = true, value = "1-待处理，2-处理中，3-已完成")
      @RequestParam("type") Integer type){
    LambdaQueryChainWrapper<Work> lqcw = workService.lambdaQuery();
    lqcw
        .eq(Work::getCompanyId, currentUser.getCurrentUser().getCompanyId());
    switch (type) {
      case 1:
        //待处理 是当前处理人
        lqcw.nested(qcw -> qcw
            .eq(Work::getNowUserId, currentUser.getCurrentUser().getId())
            .nested(w -> w.eq(Work::getEndFlag, false).or().isNull(Work::getEndFlag)))
        ;
        break;
      case 2:
        //处理中 是发起人
        lqcw.eq(Work::getStartUserId, currentUser.getCurrentUser().getId())
            .nested(w -> w.eq(Work::getEndFlag, false).or().isNull(Work::getEndFlag)) ;
        break;
      case 3:
        //已完成 是发起人
        lqcw.eq(Work::getStartUserId, currentUser.getCurrentUser().getId())
            .eq(Work::getEndFlag, true);
        break;
    }
    return ResponseEntity.ok(lqcw.list());
  }
}
