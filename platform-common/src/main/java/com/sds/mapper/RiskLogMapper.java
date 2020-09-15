package com.sds.mapper;

import com.sds.entity.RiskLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sds.vo.RiskLogVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
public interface RiskLogMapper extends BaseMapper<RiskLog> {

  /**
   *
   * @param companyId
   * @param riskId
   * @return
   */
  List<RiskLogVo> selectRiskLogVo(@Param("companyId")Long companyId, @Param("riskId")Long riskId);

}
