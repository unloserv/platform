package com.sds.file;

import lombok.Data;

/**
 * @author cs
 * @date 2020/5/29
 * @description
 */
@Data
public class FileUploadResult {

  private String filename;

  private int resultCode;

  private String result;

  private String filepath;
}
