package com.sds.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

/**
 * @author cs
 * @date 2020/8/11
 * @description
 */
@Data
public class BaseEntity {

  @ApiModelProperty(value = "创建者")
  @TableField("create_by")
  @CreatedBy
  private String createBy;

  @ApiModelProperty(value = "更新者")
  @TableField("update_by")
  private String updateBy;

  @ApiModelProperty(value = "创建日期")
  @TableField("create_time")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "更新时间")
  @TableField("update_time")
  private LocalDateTime updateTime;
}
