package com.sds.dto;

import com.sds.entity.RiskLog;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewRiskLogConfirmHandleDto extends NewRiskLogDto {

    @ApiModelProperty(value = "重复举报flag")
    private Boolean duplicateFlag;

    @Override
    public RiskLog trans(Long riskId, OnlineUser onlineUser) {
        return super.trans(riskId, onlineUser);
    }
}
