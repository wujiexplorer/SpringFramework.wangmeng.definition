package com.lx.benefits.mapper.order;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import com.lx.benefits.bean.vo.order.ItemOrderVO;
import com.lx.benefits.mapper.base.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @ClassName: ProductOrderExMapper
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface ProductOrderExMapper extends IBaseMapper<ProductOrderEx>{

    int insertBatch(List<ProductOrderEx> list);

    List<ItemOrderVO> selectUserItemOrderList(
            @Param("userId") Long userId,
            @Param("sellerOrderNumberList")List<Long> sellerOrderNumberList,
            @Param("listItemOrderNumbers")List<Long> listItemOrderNumbers);

    /**
     * 查询订单商品
     * @param itemOrderNumberList
     * @return
     */
    List<ProductOrderEx> selectItemOrderList(@Param("itemOrderNumberList")List<Long> itemOrderNumberList);
    
    ProductOrderEx selectByOrderNumber(@Param("orderNumber")Long orderNumber);
}