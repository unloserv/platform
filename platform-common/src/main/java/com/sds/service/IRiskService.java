package com.sds.service;

import com.sds.entity.Risk;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sds.vo.RiskStatusCountVo;

import com.sds.vo.RiskTypeCountVo;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
public interface IRiskService extends IService<Risk> {

    /**
     * 获取隐患状态的数量统计
     * @param companyId
     * @return
     */
    List<RiskStatusCountVo> getStatusCount(Long companyId);


    /**
     * 获取隐患类型的数量统计
     * @param companyId
     * @return
     */
    List<RiskTypeCountVo> getTypeCount(Long companyId, Integer status);

}
