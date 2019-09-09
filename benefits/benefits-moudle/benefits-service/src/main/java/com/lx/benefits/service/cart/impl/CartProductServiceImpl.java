package com.lx.benefits.service.cart.impl;

import com.apollo.common.enums.base.DelStatusEnum;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.lx.benefits.bean.entity.cart.CartProduct;
import com.lx.benefits.mapper.cart.CartProductMapper;
import com.lx.benefits.service.cart.CartProductService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @ClassName: CartProductServiceImpl
* @Description:
* @author wind
* @date 2019-2-10
*/
@Service("cartProductService")
public class CartProductServiceImpl implements CartProductService {
	
	@Resource(name="cartProductMapper")
    private CartProductMapper cartProductMapper;


    @Override
    public Long doAddRecord(CartProduct record) {
        return cartProductMapper.insertSelective(record);
    }

    @Override
    public int doModRecord(CartProduct record) {
        return cartProductMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long doSaveRecord(CartProduct record,int source) throws BusinessException {


        List<CartProduct> cartProductList = cartProductMapper.selectUserCartListBySkuId(record.getAccountId(),record.getSkuId(),DelStatusEnum.NO.getCode());
        if (CollectionUtils.isEmpty(cartProductList)) {
            if (this.selectCartCountByAccountId(record.getAccountId())>19) {
                throw new BusinessException("购物车最多加购20种商品");
            }
            cartProductMapper.insertSelective(record);
            return record.getId();
        }else {
            CartProduct cartProduct = cartProductList.get(0);
            if (cartProductList.size() > 1){
                List<Long> idList = CollectionExtUtil.getPropertyList(cartProductList.subList(1,cartProductList.size()),CartProduct::getId);
                cartProductMapper.deleteByIdList(record.getAccountId(),idList);
            }
            /**source ==0 加购场景在原有基础上累加，1：更新场景，直接更新已有数据*/
            int count = source==0?record.getCount()+cartProduct.getCount():record.getCount();
            if(count<1 || count>1000){
                throw new BusinessException("购物车加购数量1~1000件");
            }
            cartProductMapper.updateCountById(cartProduct.getAccountId(), cartProduct.getId(), count);
            return cartProduct.getId();
        }
    }

    @Override
    public CartProduct getById(Long id) {
        return cartProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CartProduct> listCartProduct(Long accountId) {

        return cartProductMapper.selectCartListByAccountId(accountId,null, DelStatusEnum.NO.getCode());
    }

    @Override
    public int selectCartCountByAccountId(Long accountId) {
        return cartProductMapper.selectCartCountByAccountId(accountId,null,DelStatusEnum.NO.getCode());
    }

    @Override
    public List<CartProduct> listCheckedByAccountId(Long accountId) throws BusinessException {
        return cartProductMapper.selectCartListByAccountId(accountId,1, DelStatusEnum.NO.getCode());
    }

    @Override
    public List<CartProduct> listByIdList(Long accountId, List<Long> idList) throws BusinessException {

        return cartProductMapper.selectListByIdList(accountId,idList,DelStatusEnum.NO.getCode());
    }

    @Override
    public int delByIdList(Long accountId, List<Long> idList) throws BusinessException {
        return cartProductMapper.deleteByIdList(accountId,idList);
    }

    @Override
    public int modCheckedByIdList(Long accountId, List<Long> idList, Integer checked) {
        return cartProductMapper.updateCheckedByIdList(accountId,idList,checked);
    }

	@Override
	public long queryCartId(Map<String, Long> cartProductId) {
		return cartProductMapper.queryCartId(cartProductId);
	}
}

