package com.sds.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sds.modules.sys.entity.Dept;
import com.sds.modules.sys.entity.User;
import com.sds.modules.sys.mapper.DeptMapper;
import com.sds.modules.sys.mapper.UserMapper;
import com.sds.modules.sys.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
