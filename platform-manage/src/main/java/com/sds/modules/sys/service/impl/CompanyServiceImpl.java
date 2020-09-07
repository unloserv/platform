package com.sds.modules.sys.service.impl;

import com.sds.modules.sys.entity.Company;
import com.sds.modules.sys.mapper.CompanyMapper;
import com.sds.modules.sys.service.ICompanyService;
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
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
