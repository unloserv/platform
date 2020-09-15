package com.sds.modules.work;


import com.sds.dto.NewWorkLogDto;
import com.sds.entity.Work;
import com.sds.entity.WorkLog;
import com.sds.redis.CurrentUser;
import com.sds.service.IWorkLogAttachmentService;
import com.sds.service.IWorkLogService;
import com.sds.service.IWorkService;
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

@RestController
@RequestMapping("/workLog")
@RequiredArgsConstructor
@Api(tags = "工作记录")
@PreAuthorize("hasAnyRole('ADMIN', 'NORMAL')")
@Slf4j
public class WorkLogController {

  private final CurrentUser currentUser;
  private final IWorkService workService;
  private final IWorkLogService workLogService;
  private final IWorkLogAttachmentService workLogAttachmentService;

  @ApiOperation("工作记录查看")
  @GetMapping("/list/{workId}")
  public ResponseEntity list(
      @PathVariable("workId") Long workId) {
    return ResponseEntity.ok(workLogService.getWorkLogVoList(currentUser.getCurrentUser().getCompanyId(), workId));
  }

  @ApiOperation("工作处理")
  @PostMapping("/handle/{workId}")
  @Transactional(rollbackFor = Exception.class)
  public ResponseEntity handle(@PathVariable("workId") Long workId,
      @Validated @RequestBody() NewWorkLogDto workLog){
    WorkLog wl = workLog.trans(workId, currentUser.getCurrentUser());
    workLogService.save(wl);
    if (!workLog.getAttachmentList().isEmpty()){
      workLogAttachmentService.saveBatch(workLog.getAttachmentList(wl.getId(), currentUser.getCurrentUser()));
    }
    Work work = new Work();
    work.setId(workId);
    work.setNowUserId(workLog.getToUserId());
    work.setNowUserName(workLog.getToUserName());
    work.setEndFlag(workLog.getEndFlag());
    workService.updateById(work);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
