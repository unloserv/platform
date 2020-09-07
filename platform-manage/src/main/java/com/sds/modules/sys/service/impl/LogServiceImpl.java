package com.sds.modules.sys.service.impl;

import com.sds.modules.sys.entity.Log;
import com.sds.modules.sys.mapper.LogMapper;
import com.sds.modules.sys.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
