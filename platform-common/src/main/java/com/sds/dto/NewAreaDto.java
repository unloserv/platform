package com.sds.dto;

import com.sds.entity.Area;
import com.sds.entity.Company;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

/**
 * @author cs
 * @date 2020/9/3
 * @description
 */
@Data
public class NewAreaDto {

  @ApiModelProperty(value = "名称")
  @Length(min = 6, max = 40)
  private String name;

  @ApiModelProperty(value = "排序")
  @Range(min = 1, max = 999)
  private Integer sort;

  public Area trans(OnlineUser onlineUser) {
    Area area = new Area();
    area.setName(this.name);
    area.setSort(this.sort);
    area.setCompanyId(onlineUser.getCompanyId());
    area.setCreateBy(onlineUser.getNickName());
    area.setCreateTime(LocalDateTime.now());
    return area;
  }

}
