package com.sds.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sds.entity.Dict;
import com.sds.entity.DictDetail;
import com.sds.mapper.DictDetailMapper;
import com.sds.service.IDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sds.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据字典详情 服务实现类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
@Service
public class DictDetailServiceImpl extends ServiceImpl<DictDetailMapper, DictDetail> implements IDictDetailService {

    @Autowired
    private IDictService dictService;

    @Override
    public List<DictDetail> getDictDetailByDictName(String dictName) {
        Dict dict = dictService.getOne(Wrappers.<Dict>lambdaQuery().eq(Dict::getName, dictName));
        if (dict == null) {
            return null;
        }
        return baseMapper.selectList(Wrappers.<DictDetail>lambdaQuery().eq(DictDetail::getDictId, dict.getId()));
    }
}
