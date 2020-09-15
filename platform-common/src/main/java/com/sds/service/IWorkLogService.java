package com.sds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sds.entity.WorkLog;
import com.sds.vo.WorkLogVo;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-10
 */
public interface IWorkLogService extends IService<WorkLog> {
  /**
   *
   * @param companyId
   * @param workId
   * @return
   */
  List<WorkLogVo> getWorkLogVoList(Long companyId, Long workId);

}
