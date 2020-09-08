package com.sds.dto;

import com.sds.entity.Company;
import com.sds.redis.dto.OnlineUser;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * @author cs
 * @date 2020/9/3
 * @description
 */
@Data
public class NewCompanyDto {

  @ApiModelProperty(value = "名称")
  @Length(min = 6, max = 40)
  private String name;

  @ApiModelProperty(value = "排序")
  @Range(min = 1, max = 999)
  private Integer sort;

  public Company trans(OnlineUser onlineUser) {
    Company company = new Company();
    company.setName(this.name);
    company.setSort(this.sort);
    company.setCreateBy(onlineUser.getNickName());
    company.setCreateTime(LocalDateTime.now());
    return company;
  }
}
