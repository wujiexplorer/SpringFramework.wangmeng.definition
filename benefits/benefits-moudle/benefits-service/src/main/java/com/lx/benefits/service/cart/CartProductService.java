package com.lx.benefits.service.cart;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.cart.CartProduct;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
* @ClassName: CartProductService
* @Description:
* @author wind
* @date 2019-2-10
*/
public interface CartProductService{



	/**
	 * 添加记录
	 * @param record
	 * @return 主键ID
	 * @author wind
	 * @date 2019-2-10
	 */
	Long doSaveRecord(CartProduct record,int source) throws BusinessException;
	/**
     * 添加记录
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
	Long doAddRecord(CartProduct record) throws BusinessException;
	
	/**
     * 根据主键编辑项目信息
     * @param record
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    int doModRecord(CartProduct record) throws BusinessException;

    /**
     * 根据主键获取项目信息
     * @param id
     * @return
     * @author wind
	 * @date 2019-2-10
     */
    CartProduct getById(Long id) throws BusinessException;

	/**
	 * 购物车列表
	 * @param accountId
	 * @return
	 */
	List<CartProduct> listCartProduct(Long accountId);

	/**
	 * 购物车记录数
	 * @param accountId
	 * @return
	 */
	int selectCartCountByAccountId(Long accountId);

	/**
	 * 查询指定集合
	 * @param accountId
	 * @param idList
	 * @return
	 * @throws BusinessException
	 */
	List<CartProduct> listByIdList(Long accountId,List<Long> idList) throws BusinessException;

	/**
	 * 查询购物车选中商品
	 * @param accountId
	 * @return
	 * @throws BusinessException
	 */
	List<CartProduct> listCheckedByAccountId(Long accountId) throws BusinessException;

	/**
	 * 删除购物车商品
	 * @param accountId
	 * @param idList
	 * @return
	 * @throws BusinessException
	 */
	int delByIdList(Long accountId,List<Long> idList) throws BusinessException;

	/**
	 * 更新购物车选中状态
	 * @param accountId
	 * @param idList
	 * @param checked
	 * @return
	 */
	int modCheckedByIdList(Long accountId, List<Long> idList, Integer checked);

	long queryCartId(Map<String,Long> cartProductId);
 }

