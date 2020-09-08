package com.sds.dto;

import com.sds.entity.AreaAdmin;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author cs
 * @date 2020/9/3
 * @description
 */
@Data
public class NewAreaAdminDto {

  @ApiModelProperty(value = "区域id")
  @NotNull
  private Long areaId;

  @ApiModelProperty(value = "用户id")
  @NotNull
  private Long userId;

  @ApiModelProperty(value = "用户名称")
  @NotNull
  private String userName;

  public AreaAdmin trans(OnlineUser onlineUser) {
    AreaAdmin areaAdmin = new AreaAdmin();
    areaAdmin.setAreaId(this.areaId);
    areaAdmin.setUserId(this.userId);
    areaAdmin.setUserName(userName);
    areaAdmin.setCompanyId(onlineUser.getCompanyId());
    areaAdmin.setCreateBy(onlineUser.getNickName());
    areaAdmin.setCreateTime(LocalDateTime.now());
    return areaAdmin;
  }
}
