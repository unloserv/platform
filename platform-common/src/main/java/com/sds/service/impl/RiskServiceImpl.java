package com.sds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sds.entity.DictDetail;
import com.sds.entity.Risk;
import com.sds.mapper.RiskMapper;
import com.sds.service.IDictDetailService;
import com.sds.service.IRiskService;
import com.sds.vo.RiskStatusCountVo;
import com.sds.vo.RiskTypeCountVo;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RiskServiceImpl extends ServiceImpl<RiskMapper, Risk> implements IRiskService {

    private final RiskMapper riskMapper;

    private final IDictDetailService dictDetailService;

    @Override
    public List<RiskStatusCountVo> getStatusCount(Long companyId) {
        List<RiskStatusCountVo> statusCount = riskMapper.selectStatusCount(companyId);
        List<DictDetail> dictDetailList = dictDetailService.getDictDetailByDictName("risk_status");
        dictDetailList.stream().forEach(dictDetail -> {
            Optional<RiskStatusCountVo> status = statusCount.stream()
                .filter(s -> s.getStatus().equals(Integer.valueOf(dictDetail.getValue()))).findFirst();
            RiskStatusCountVo s;
            if (!status.isPresent()) {
                s = new RiskStatusCountVo();
                s.setStatus(Integer.valueOf(dictDetail.getValue()));
                s.setCount(0);
                s.setStatusName(dictDetail.getLabel());
                statusCount.add(s);
            } else {
                s = status.get();
                s.setStatusName(dictDetail.getLabel());
            }
        });
        return statusCount;
    }

    @Override
    public List<RiskTypeCountVo> getTypeCount(Long companyId, Integer status) {
        List<RiskTypeCountVo> typeCount = riskMapper.selectTypeCount(companyId, status);
        List<DictDetail> dictDetailList = dictDetailService.getDictDetailByDictName("risk_type");
        dictDetailList.stream().forEach(dictDetail -> {
            Optional<RiskTypeCountVo> type = typeCount.stream()
                .filter(s -> s.getType().equals(Integer.valueOf(dictDetail.getValue()))).findFirst();
            RiskTypeCountVo t;
            if (!type.isPresent()) {
                t = new RiskTypeCountVo();
                t.setType(Integer.valueOf(dictDetail.getValue()));
                t.setCount(0);
                t.setTypeName(dictDetail.getLabel());
                typeCount.add(t);
            } else {
                t = type.get();
                t.setTypeName(dictDetail.getLabel());
            }
        });
        return typeCount;
    }

}
