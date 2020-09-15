package com.sds.vo;

import com.sds.entity.WorkLog;
import com.sds.entity.WorkLogAttachment;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author cs
 * @date 2020/9/10
 * @description
 */
@Data
public class WorkLogVo implements Serializable {

  private WorkLog workLog;

  private List<WorkLogAttachment> workLogAttachmentList;

}
