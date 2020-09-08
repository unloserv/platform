package com.sds.dto;

import com.sds.entity.Dept;
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
public class NewDeptDto {

  @ApiModelProperty(value = "名称")
  @Length(min = 6, max = 40)
  private String name;

  @ApiModelProperty(value = "排序")
  @Range(min = 1, max = 999)
  private Integer sort;

  @ApiModelProperty(value = "上级部门")
  private Long pid;

  @ApiModelProperty(value = "状态")
  private Boolean enableFlag;

  public Dept trans(OnlineUser onlineUser) {
    Dept dept = new Dept();
    dept.setName(this.name);
    dept.setPid(this.pid);
    dept.setSort(this.sort);
    dept.setEnableFlag(this.enableFlag);
    dept.setCompanyId(onlineUser.getCompanyId());
    dept.setCreateBy(onlineUser.getNickName());
    dept.setCreateTime(LocalDateTime.now());
    return dept;
  }
}
