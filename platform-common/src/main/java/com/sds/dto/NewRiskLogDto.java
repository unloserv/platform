package com.sds.dto;

import com.sds.entity.RiskLog;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class NewRiskLogDto {

    @ApiModelProperty(value = "描述")
    @Length(min = 6, max = 600)
    private String describe;

    public RiskLog trans(Long riskId, OnlineUser onlineUser) {
        RiskLog riskLog = new RiskLog();
        riskLog.setRiskId(riskId);
        riskLog.setDescribe(this.describe);
        riskLog.setHandlerUserId(onlineUser.getId());
        riskLog.setHandlerUserName(onlineUser.getNickName());
        riskLog.setCompanyId(onlineUser.getCompanyId());
        riskLog.setCreateBy(onlineUser.getNickName());
        riskLog.setCreateTime(LocalDateTime.now());
        return riskLog;
    }
}
