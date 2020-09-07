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
 * 角色菜单关联
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_roles_menus")
@ApiModel(value="RolesMenus对象", description="角色菜单关联")
public class RolesMenus extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    @TableId("menu_id")
    private Long menuId;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;


}
