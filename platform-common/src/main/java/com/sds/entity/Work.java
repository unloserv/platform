package com.sds.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("work")
@ApiModel(value="Work对象", description="")
public class Work extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("company_id")
    private Long companyId;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @TableField("start_user_id")
    private Long startUserId;

    @TableField("start_user_name")
    private String startUserName;

    @TableField("now_user_id")
    private Long nowUserId;

    @TableField("now_user_name")
    private String nowUserName;

    @TableField("end_flag")
    private Boolean endFlag;


}
