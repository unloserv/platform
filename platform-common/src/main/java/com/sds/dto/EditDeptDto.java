package com.sds.dto;

import com.sds.entity.Dept;
import com.sds.redis.dto.OnlineUser;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author cs
 * @date 2020/9/3
 * @description
 */
@Data
public class EditDeptDto extends NewDeptDto {

  @NotEmpty()
  private Long id;

  @Override
  public Dept trans(OnlineUser onlineUser) {
    Dept dept = new Dept();
    dept.setId(this.id);
    dept.setPid(super.getPid());
    dept.setName(super.getName());
    dept.setSort(super.getSort());
    dept.setEnableFlag(super.getEnableFlag());
    dept.setUpdateBy(onlineUser.getNickName());
    return dept;
  }

}
