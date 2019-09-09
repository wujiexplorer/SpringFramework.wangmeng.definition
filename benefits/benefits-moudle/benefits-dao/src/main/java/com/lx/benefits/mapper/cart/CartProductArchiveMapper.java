package com.lx.benefits.mapper.cart;
import com.lx.benefits.bean.entity.cart.CartProductArchive;
import com.lx.benefits.mapper.base.IBaseMapper;

import java.util.List;

/**
* @ClassName: CartProductArchiveMapper
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface CartProductArchiveMapper extends IBaseMapper<CartProductArchive>{

    /**
     * 批量保存
     * @param list
     * @return
     */
    int insertBatch(List<CartProductArchive> list);
}