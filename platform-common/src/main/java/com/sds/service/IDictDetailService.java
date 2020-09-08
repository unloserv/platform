package com.sds.service;

import com.sds.entity.DictDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据字典详情 服务类
 * </p>
 *
 * @author caoshuai
 * @since 2020-09-03
 */
public interface IDictDetailService extends IService<DictDetail> {

    /**
     * 获取字典详情
     * @param dictName 字典名称
     * @return
     */
    List<DictDetail> getDictDetailByDictName(@Param("dictName") String dictName);
}
