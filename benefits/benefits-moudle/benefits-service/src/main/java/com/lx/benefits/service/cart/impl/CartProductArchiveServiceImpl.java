package com.lx.benefits.service.cart.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.cart.CartProductArchive;
import com.lx.benefits.mapper.cart.CartProductArchiveMapper;
import com.lx.benefits.service.cart.CartProductArchiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @ClassName: CartProductArchiveServiceImpl
* @Description:
* @author wind
* @date 2019-2-10
*/
@Service("cartProductArchiveService")
public class CartProductArchiveServiceImpl implements CartProductArchiveService {
	
	@Resource(name="cartProductArchiveMapper")
    private CartProductArchiveMapper cartProductArchiveMapper;


    @Override
    public Long doAddRecord(CartProductArchive record) {
        return cartProductArchiveMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(CartProductArchive record) {
        return cartProductArchiveMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CartProductArchive getById(Integer id) {
        return cartProductArchiveMapper.selectByPrimaryKey(id);
    }

    @Override
    public int doAddBatchRecord(List<CartProductArchive> list) throws BusinessException {
        return cartProductArchiveMapper.insertBatch(list);
    }
}

