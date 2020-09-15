package com.sds.service;

import com.sds.entity.RiskLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sds.vo.RiskLogVo;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
public interface IRiskLogService extends IService<RiskLog> {

  /**
   *
   * @param companyId
   * @param riskId
   * @return
   */
  List<RiskLogVo> getRiskLogVoList(Long companyId, Long riskId);

}
