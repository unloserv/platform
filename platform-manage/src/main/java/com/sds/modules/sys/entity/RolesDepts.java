package com.sds.modules.sys.entity;

import com.sds.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色部门关联
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_roles_depts")
@ApiModel(value="RolesDepts对象", description="角色部门关联")
public class RolesDepts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
    private Long roleId;

    @TableField("dept_id")
    private Long deptId;


}
