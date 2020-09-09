package com.sds.dto;

import com.sds.entity.RiskLog;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewRiskLogHandleDto extends NewRiskLogDto {

    @ApiModelProperty(value = "重复举报flag")
    private Boolean duplicateFlag;

    @ApiModelProperty(value = "重复举报隐患id")
    private Long duplicateRiskId;

    @ApiModelProperty(value = "重复举报隐患标题")
    private String duplicateRiskTitle;

    @Override
    public RiskLog trans(Long riskId, OnlineUser onlineUser) {
        return super.trans(riskId, onlineUser);
    }
}
