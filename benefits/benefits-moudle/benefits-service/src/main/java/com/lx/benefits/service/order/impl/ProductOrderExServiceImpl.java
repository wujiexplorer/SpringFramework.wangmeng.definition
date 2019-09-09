package com.lx.benefits.service.order.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import com.lx.benefits.bean.vo.order.ItemOrderVO;
import com.lx.benefits.mapper.order.ProductOrderExMapper;
import com.lx.benefits.service.order.ProductOrderExService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @ClassName: ProductOrderExServiceImpl
* @Description:
* @author wind
* @date 2019-2-10
*/
@Service("productOrderExService")
public class ProductOrderExServiceImpl implements ProductOrderExService {
	
	@Resource(name="productOrderExMapper")
    private ProductOrderExMapper productOrderExMapper;


    @Override
    public Long doAddRecord(ProductOrderEx record) {
        return productOrderExMapper.insertSelective(record);
    }

    @Override
    public int doAddBatchRecord(List<ProductOrderEx> list) throws BusinessException {
        return productOrderExMapper.insertBatch(list);
    }



    @Override
    public int doModRecord(ProductOrderEx record) {
        return productOrderExMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ProductOrderEx getById(Integer id) {
        return productOrderExMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemOrderVO> selectUserItemOrderList(Long userId,List<Long> sellerOrderNumberList,List<Long> listItemOrderNumbers) {
        return productOrderExMapper.selectUserItemOrderList(userId,sellerOrderNumberList,listItemOrderNumbers);
    }

    @Override
    public List<ProductOrderEx> listItemOrder(List<Long> itemOrderNumberList) {
        return productOrderExMapper.selectItemOrderList(itemOrderNumberList);
    }

	@Override
	public ProductOrderEx selectByOrderNumber(Long orderNumber) {
		return productOrderExMapper.selectByOrderNumber(orderNumber);
	}
}

