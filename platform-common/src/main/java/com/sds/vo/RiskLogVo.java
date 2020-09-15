package com.sds.vo;

import com.sds.entity.RiskLog;
import com.sds.entity.RiskLogImage;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author cs
 * @date 2020/9/10
 * @description
 */
@Data
public class RiskLogVo implements Serializable {

  private RiskLog riskLog;

  private List<RiskLogImage> riskLogImageList;

}
