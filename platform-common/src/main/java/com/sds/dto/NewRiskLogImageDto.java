package com.sds.dto;

import com.sds.entity.RiskLog;
import com.sds.entity.RiskLogImage;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class NewRiskLogImageDto {

    @ApiModelProperty(value = "图片地址集合")
    private String[] riskImages;

    public List<RiskLogImage> trans(Long riskLogId, OnlineUser onlineUser) {
        if (riskImages != null && riskImages.length > 0) {
            List<RiskLogImage> imageList = new ArrayList<>(riskImages.length);
            Arrays.stream(riskImages).forEach(riskImage -> {
                RiskLogImage riskLogImage = new RiskLogImage();
                riskLogImage.setPath(riskImage);
                riskLogImage.setRiskLogId(riskLogId);
                riskLogImage.setCompanyId(onlineUser.getCompanyId());
                riskLogImage.setCreateBy(onlineUser.getNickName());
                riskLogImage.setCreateTime(LocalDateTime.now());
                imageList.add(riskLogImage);
            });
            return imageList;
        }
        return null;
    }
}
