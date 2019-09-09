package com.lx.benefits.service.order;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import com.lx.benefits.bean.vo.order.ItemOrderVO;

import java.util.List;

/**
* @ClassName: ProductOrderExService
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface ProductOrderExService{
	
	
	
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
	Long doAddRecord(ProductOrderEx record) throws BusinessException;

	/**
	 * 保存订单商品信息
	 * @param list
	 * @return
	 * @throws BusinessException
	 */
	int doAddBatchRecord(List<ProductOrderEx> list) throws BusinessException;
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    int doModRecord(ProductOrderEx record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    ProductOrderEx getById(Integer id) throws BusinessException;

	/**
	 * 用户订单商品列表
	 * @param userId
	 * @param sellerOrderNumberList
	 * @return
	 */
	List<ItemOrderVO> selectUserItemOrderList(Long userId,List<Long> sellerOrderNumberList,List<Long> listItemOrderNumbers);

	/**
	 * 查询订单商品
	 * @param itemOrderNumberList
	 * @return
	 */
	List<ProductOrderEx> listItemOrder(List<Long> itemOrderNumberList);

	ProductOrderEx selectByOrderNumber(Long orderNumber);


 }

