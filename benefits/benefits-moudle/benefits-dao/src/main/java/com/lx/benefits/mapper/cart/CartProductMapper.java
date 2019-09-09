package com.lx.benefits.mapper.cart;
import com.lx.benefits.bean.entity.cart.CartProduct;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @ClassName: CartProductMapper
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface CartProductMapper extends IBaseMapper<CartProduct>{

    /**
     * 购物车列表
     * @return
     */
    List<CartProduct> selectCartListByAccountId(@Param("accountId") Long accountId, @Param("checked")Integer checked, @Param("isDelete")Integer isDelete);


    /**
     * 购物车记录数
     * @return
     */
    int selectCartCountByAccountId(@Param("accountId") Long accountId, @Param("checked")Integer checked, @Param("isDelete")Integer isDelete);

    /**
     * 根据accountId idList查询
     * @param accountId
     * @param idList
     * @param isDelete
     * @return
     */
    List<CartProduct> selectListByIdList(@Param("accountId") Long accountId,@Param("idList") List<Long> idList,@Param("isDelete")Integer isDelete);

    /**
     * 删除购物车商品
     * @param accountId
     * @param idList
     * @return
     */
    int deleteByIdList(@Param("accountId") Long accountId,@Param("idList") List<Long> idList);

    List<CartProduct> selectUserCartListBySkuId(@Param("accountId") Long accountId,@Param("skuId") Long skuId,@Param("isDelete")Integer isDelete);

    int updateCountById(@Param("accountId") Long accountId,@Param("id") Long id,@Param("count") Integer count);

    int updateCheckedByIdList(@Param("accountId") Long accountId,@Param("idList") List<Long> idList,@Param("checked") Integer checked);
    
    long queryCartId(Map<String,Long> cartProductId);
}