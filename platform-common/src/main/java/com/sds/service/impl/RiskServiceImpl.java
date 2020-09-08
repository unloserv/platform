package com.sds.service.impl;

import com.sds.entity.DictDetail;
import com.sds.entity.Risk;
import com.sds.mapper.RiskMapper;
import com.sds.service.IDictDetailService;
import com.sds.service.IRiskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sds.vo.RiskStatusCountVo;
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
    public List<RiskStatusCountVo> getStatusCount(Long companyId) {
        List<Map<String, Integer>> statusCount = riskMapper.selectStatusCount(companyId);
        List<DictDetail> dictDetailList = dictDetailService.getDictDetailByDictName("risk_status");
        List<RiskStatusCountVo> voList = new ArrayList<>(dictDetailList.size());
        dictDetailList.stream().forEach(dictDetail -> {
            RiskStatusCountVo countVo = new RiskStatusCountVo();
            countVo.setStatus(Integer.valueOf(dictDetail.getValue()));
            countVo.setStatusName(dictDetail.getLabel());
            countVo.setCount(statusCount.stream()
                    .filter(map -> dictDetail.getLabel().equals(map.get("status")))
                    .findFirst().get().get("count"));
            voList.add(countVo);
        });
        return voList;
    }
}
