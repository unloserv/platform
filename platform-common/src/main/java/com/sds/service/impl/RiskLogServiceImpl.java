package com.sds.service.impl;

import com.sds.entity.RiskLog;
import com.sds.mapper.RiskLogMapper;
import com.sds.service.IRiskLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sds.vo.RiskLogVo;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@Service
public class RiskLogServiceImpl extends ServiceImpl<RiskLogMapper, RiskLog> implements IRiskLogService {

  @Override
  public List<RiskLogVo> getRiskLogVoList(Long companyId, Long riskId) {
    return baseMapper.selectRiskLogVo(companyId, riskId);
  }
}
