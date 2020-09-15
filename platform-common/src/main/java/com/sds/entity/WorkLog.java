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
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("work_log")
@ApiModel(value="WorkLog对象", description="")
public class WorkLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("work_id")
    private Long workId;

    @TableField("company_id")
    private Long companyId;

    @TableField("remark")
    private String remark;

    @TableField("operate_user_id")
    private Long operateUserId;

    @TableField("operate_user_name")
    private String operateUserName;

    @TableField("to_user_id")
    private Long toUserId;

    @TableField("to_user_name")
    private String toUserName;

}
