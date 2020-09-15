package com.sds.service.impl;

import com.sds.entity.WorkLog;
import com.sds.mapper.WorkLogMapper;
import com.sds.service.IWorkLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sds.vo.WorkLogVo;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-10
 */
@Service
public class WorkLogServiceImpl extends ServiceImpl<WorkLogMapper, WorkLog> implements IWorkLogService {

  @Override
  public List<WorkLogVo> getWorkLogVoList(Long companyId, Long workId) {
    return baseMapper.selectWorkLogVo(companyId, workId);
  }
}
