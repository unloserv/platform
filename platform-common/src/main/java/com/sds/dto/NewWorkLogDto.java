package com.sds.dto;

import com.sds.entity.WorkLog;
import com.sds.entity.WorkLogAttachment;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class NewWorkLogDto {

    @ApiModelProperty(value = "描述")
    @Length(min = 6, max = 600)
    private String remark;

    @ApiModelProperty(value = "附件")
    private List<WorkLogAttachment> attachmentList;

    @ApiModelProperty(value = "指派给id")
    private Long toUserId;

    @ApiModelProperty(value = "指派给name")
    private String toUserName;

    @ApiModelProperty(value = "是否结束")
    private Boolean endFlag;

    public WorkLog trans(Long workId, OnlineUser onlineUser) {
        WorkLog workLog = new WorkLog();
        workLog.setWorkId(workId);
        workLog.setRemark(this.remark);
        workLog.setOperateUserId(onlineUser.getId());
        workLog.setOperateUserName(onlineUser.getNickName());
        workLog.setCompanyId(onlineUser.getCompanyId());
        workLog.setCreateBy(onlineUser.getNickName());
        workLog.setCreateTime(LocalDateTime.now());
        if (endFlag == null || !endFlag) {
            workLog.setToUserId(toUserId);
            workLog.setToUserName(toUserName);
        }
        return workLog;
    }

    public List<WorkLogAttachment> getAttachmentList(Long workLogId, OnlineUser onlineUser) {
        this.attachmentList.stream().forEach(attachment -> {
            attachment.setWorkLogId(workLogId);
            attachment.setCompanyId(onlineUser.getCompanyId());
            attachment.setCreateBy(onlineUser.getNickName());
            attachment.setCreateTime(LocalDateTime.now());
        });
        return this.attachmentList;
    }
}
