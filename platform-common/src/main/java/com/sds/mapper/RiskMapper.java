package com.sds.mapper;

import com.sds.entity.Risk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
public interface RiskMapper extends BaseMapper<Risk> {

    @Select("SELECT `status`, COUNT(1) `count` FROM `risk` WHERE `company_id` = #{companyId} GROUP BY `status`")
    List<Map<String, Integer>> selectStatusCount(@Param("companyId") Long companyId);

}
