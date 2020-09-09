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
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("risk_log")
@ApiModel(value="RiskLog对象", description="")
public class RiskLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("company_id")
    private Long companyId;

    @TableField("risk_id")
    private Long riskId;

    @TableField("remark")
    private String remark;

    @TableField("handler_user_name")
    private String handlerUserName;

    @TableField("handler_user_id")
    private Long handlerUserId;


}
