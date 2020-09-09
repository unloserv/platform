package com.sds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sds.entity.Risk;
import com.sds.vo.RiskStatusCountVo;
import com.sds.vo.RiskTypeCountVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-07
 */
public interface RiskMapper extends BaseMapper<Risk> {

    @Select("SELECT `status`, COUNT(1) `count` "
        + "FROM `risk` "
        + "WHERE `company_id` = #{companyId} "
        + "AND `admin_user_id` = #{adminUserId} "
        + "GROUP BY `status`")
    List<RiskStatusCountVo> selectStatusCount(@Param("companyId") Long companyId, @Param("adminUserId") Long adminUserId);

    @Select("<script>"
        + "SELECT `type`, COUNT(1) `count` "
        + "FROM `risk` "
        + "WHERE `company_id` = #{companyId} "
        + "AND `admin_user_id` = #{adminUserId} "
        + "<if test='status != null'>AND `status` = #{status} </if>"
        + "GROUP BY `type`"
        + "</script>")
    List<RiskTypeCountVo> selectTypeCount(@Param("companyId") Long companyId, @Param("adminUserId") Long adminUserId, @Param("status") Integer status);
}
