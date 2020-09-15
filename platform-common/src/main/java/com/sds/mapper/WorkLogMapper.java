package com.sds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sds.entity.WorkLog;
import com.sds.vo.WorkLogVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-10
 */
public interface WorkLogMapper extends BaseMapper<WorkLog> {

  /**
   *
   * @param companyId
   * @param workId
   * @return
   */
  List<WorkLogVo> selectWorkLogVo(@Param("companyId")Long companyId, @Param("workId")Long workId);
}
