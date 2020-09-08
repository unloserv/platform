package com.sds.entity;

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
 * 
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("risk")
@ApiModel(value="Risk对象", description="")
public class Risk extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位id")
    @TableField("company_id")
    private Long companyId;

    @ApiModelProperty(value = "区域id")
    @TableField("area_id")
    private Long areaId;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "区域管理员用户id")
    @TableField("admin_user_id")
    private Long adminUserId;

    @ApiModelProperty(value = "区域管理员用户名称")
    @TableField("admin_user_name")
    private String adminUserName;

    @ApiModelProperty(value = "类型(1-自主发现,2-举报发现)")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "状态(0-待确认,1-处理中,2-已完成)")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "重复举报flag")
    @TableField("duplicate_flag")
    private Boolean duplicateFlag;

    @ApiModelProperty(value = "重复举报隐患id")
    @TableField("duplicate_risk_id")
    private Long duplicateRiskId;


}
