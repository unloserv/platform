package com.sds.modules.sys.entity;

import com.sds.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="系统菜单")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "上级菜单ID")
    @TableField("pid")
    private Long pid;

    @ApiModelProperty(value = "子菜单数目")
    @TableField("sub_count")
    private Integer subCount;

    @ApiModelProperty(value = "菜单类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "菜单标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "组件名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "组件")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "排序")
    @TableField("menu_sort")
    private Integer menuSort;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "是否外链")
    @TableField("i_frame")
    private Boolean iFrame;

    @ApiModelProperty(value = "缓存")
    @TableField("cache")
    private Boolean cache;

    @ApiModelProperty(value = "隐藏")
    @TableField("hidden")
    private Boolean hidden;

    @ApiModelProperty(value = "权限")
    @TableField("permission")
    private String permission;


}
