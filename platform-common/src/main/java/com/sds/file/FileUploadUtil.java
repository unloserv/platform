package com.sds.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cs
 * @date 2020/5/29
 * @description
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FileUploadUtil {

  private final FileProperties fileProperties;

  public FileUploadResult uploadFile(String moduleName, MultipartFile file){
    FileUploadResult fileUploadResult = new FileUploadResult();
    fileUploadResult.setFilename(file.getOriginalFilename());
    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
    String dateString = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    String fileName = UUID.randomUUID() + suffix;
    String filePath = fileProperties.getPath().getPath() + File.separator + moduleName + File.separator + dateString + File.separator + fileName;
    try {
      File dest = new File(filePath).getCanonicalFile();
      // 检测是否存在目录
      if (!dest.getParentFile().exists()) {
        if (!dest.getParentFile().mkdirs()) {
          log.debug("创建文件路径失败");
        }
      }
      file.transferTo(dest);
      fileUploadResult.setResultCode(1);
      fileUploadResult.setResult("上传成功");
      fileUploadResult.setFilepath(moduleName + File.separator + dateString + File.separator + fileName);
    } catch (IOException e) {
      log.debug("上传文件时发生错误", e.getMessage());
      fileUploadResult.setResultCode(-3);
      fileUploadResult.setResult("上传文件时发生错误，请联系管理员");
    }
    return fileUploadResult;
  }

  public boolean deleteFile(String filepath){
    File attachment = new File(fileProperties.getPath().getPath() + filepath);
    try {
      if (!attachment.getCanonicalFile().exists()) {
        return false;
      }
    } catch (IOException e) {
      log.debug("删除文件时发生错误", e.getMessage());
    }
    return attachment.delete();
  }

  public File getFile(String filepath){
    File attachment = new File(fileProperties.getPath().getPath() + filepath);
    if (!attachment.exists()) {
      return null;
    }
    return attachment;
  }

  public void downloadFile(HttpServletRequest request, HttpServletResponse response, File file, boolean deleteOnExit) {
    response.setCharacterEncoding(request.getCharacterEncoding());
    response.setContentType("application/octet-stream");
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
      response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
      IOUtils.copy(fis, response.getOutputStream());
      response.flushBuffer();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    } finally {
      if (fis != null) {
        try {
          fis.close();
          if (deleteOnExit) {
            file.deleteOnExit();
          }
        } catch (IOException e) {
          log.error(e.getMessage(), e);
        }
      }
    }
  }


}
