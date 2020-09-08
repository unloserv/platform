package com.sds.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sds.base.BaseEntity;
import com.sds.entity.AreaAdmin;
import com.sds.entity.Risk;
import com.sds.entity.RiskLog;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@Data
public class NewRiskDto {

    @ApiModelProperty(value = "区域id")
    @NotNull
    private Long areaId;

    @ApiModelProperty(value = "标题")
    @Length(min = 6, max = 40)
    private String title;

    @ApiModelProperty(value = "区域管理员用户id")
    @NotNull
    private Long adminUserId;

    @ApiModelProperty(value = "区域管理员用户名称")
    @NotNull
    private String adminUserName;

    private NewRiskLogDto newRiskLogDto;

    private NewRiskLogImageDto newRiskLogImageDto;

    public Risk trans(OnlineUser onlineUser) {
        Risk risk = new Risk();
        risk.setAreaId(this.areaId);
        risk.setAdminUserId(this.adminUserId);
        risk.setAdminUserName(this.adminUserName);
        risk.setStatus(0);
        risk.setType(adminUserId.equals(onlineUser.getId()) ? 1 : 2);
        risk.setCompanyId(onlineUser.getCompanyId());
        risk.setCreateBy(onlineUser.getNickName());
        risk.setCreateTime(LocalDateTime.now());
        return risk;
    }

}
