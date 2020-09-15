package com.sds.service.impl;

import com.sds.entity.Work;
import com.sds.mapper.WorkMapper;
import com.sds.service.IWorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements IWorkService {

}
