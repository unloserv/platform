package com.sds.modules.risk;


import com.sds.file.FileUploadResult;
import com.sds.file.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/riskImage")
@RequiredArgsConstructor
@Api(tags = "安全隐患记录:图片")
@PreAuthorize("hasAnyRole('ADMIN','NORMAL')")
@Slf4j
public class RiskImageController {

  private final FileUploadUtil fileUploadUtil;

  @PostMapping("/attachment")
  @ApiOperation("上传文件")
  public ResponseEntity<FileUploadResult> attachmentAdd(
      @RequestParam("file") MultipartFile file
  ){
    FileUploadResult fileUploadResult = fileUploadUtil.uploadFile("risk_image", file);
    return ResponseEntity.ok(fileUploadResult);
  }

  @DeleteMapping("/attachment")
  @ApiOperation("删除文件")
  public ResponseEntity<FileUploadResult> attachmentAdd(
      @RequestParam("filepath") String filepath
  ){
    fileUploadUtil.deleteFile(filepath);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

}
