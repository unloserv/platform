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
 * 系统日志
 * </p>
 *
 * @author caoshuai
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value="Log对象", description="系统日志")
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @TableField("description")
    private String description;

    @TableField("log_type")
    private String logType;

    @TableField("method")
    private String method;

    @TableField("params")
    private String params;

    @TableField("request_ip")
    private String requestIp;

    @TableField("time")
    private Long time;

    @TableField("username")
    private String username;

    @TableField("address")
    private String address;

    @TableField("browser")
    private String browser;

    @TableField("exception_detail")
    private String exceptionDetail;


}
