package com.sds.modules.sys.service.impl;

import com.sds.modules.sys.entity.Job;
import com.sds.modules.sys.mapper.JobMapper;
import com.sds.modules.sys.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

}
