package com.sds.service.impl;

import com.sds.entity.DictDetail;
import com.sds.entity.Risk;
import com.sds.mapper.RiskMapper;
import com.sds.service.IDictDetailService;
import com.sds.service.IRiskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sds.vo.RiskStatusCountVo;
import com.sds.vo.RiskTypeCountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
@Service
@RequiredArgsConstructor
public class RiskServiceImpl extends ServiceImpl<RiskMapper, Risk> implements IRiskService {

    private final RiskMapper riskMapper;

    private final IDictDetailService dictDetailService;

    @Override
    public List<RiskStatusCountVo> getStatusCount(Long companyId, Long adminUserId) {
        List<RiskStatusCountVo> statusCount = riskMapper.selectStatusCount(companyId, adminUserId);
        List<DictDetail> dictDetailList = dictDetailService.getDictDetailByDictName("risk_status");
        statusCount.stream()
            .forEach(statusCountVo -> statusCountVo.setStatusName(
                dictDetailList.stream()
                    .filter(dictDetail -> Integer.valueOf(dictDetail.getValue()).equals(statusCountVo.getStatus()))
                    .findFirst().get().getLabel()));
            return statusCount;
    }

    @Override
    public List<RiskTypeCountVo> getTypeCount(Long companyId, Long adminUserId, Integer status) {
        List<RiskTypeCountVo> typeCount = riskMapper.selectTypeCount(companyId, adminUserId, status);
        List<DictDetail> dictDetailList = dictDetailService.getDictDetailByDictName("risk_type");
        typeCount.stream()
            .forEach(typeCountVo -> typeCountVo.setTypeName(
                dictDetailList.stream()
                    .filter(dictDetail -> Integer.valueOf(dictDetail.getValue()).equals(typeCountVo.getType()))
                    .findFirst().get().getLabel()));
        return typeCount;
    }

}
