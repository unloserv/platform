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
 * 岗位
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_job")
@ApiModel(value="Job对象", description="岗位")
public class Job extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "job_id", type = IdType.AUTO)
    private Long jobId;

    @ApiModelProperty(value = "岗位名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "岗位状态")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "排序")
    @TableField("job_sort")
    private Integer jobSort;


}
