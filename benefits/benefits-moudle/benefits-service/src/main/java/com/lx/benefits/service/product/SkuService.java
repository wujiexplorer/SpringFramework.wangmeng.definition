package com.lx.benefits.service.product;

import java.util.List;
import java.util.Map;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.product.SkuQueryParam;
import com.lx.benefits.bean.dto.product.InfoDetailDto;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.entity.product.SkuStorageRecorder;
import com.lx.benefits.bean.vo.product.SkuPriceBean;

/**
 * 商品 【sku】商品 服务层
 * 
 * @author ruoyi
 * @date 2019-02-12
 */
public interface SkuService
{
	/**
     * 查询商品 【sku】商品信息
     * 
     * @param id 商品 【sku】商品ID
     * @return 商品 【sku】商品信息
     */
	SkuEntity selectById(Long id);

	SkuEntity queryBySkuId(Long id);

	/**
	 * 根据skuCode查询商品信息
	 * @param skuCode
	 * @return
	 */
	SkuEntity getBySkuCode(String skuCode);

	SkuEntity selectBySku(String sku,String supplierName);

	String getSpuId(List<String> sku);
	
	/**
     * 查询商品 【sku】商品列表
     * 
     * @param sku 商品 【sku】商品信息
     * @return 商品 【sku】商品集合
     */
	List<SkuEntity> selectList(SkuEntity sku);
	
	/**
     * 新增商品 【sku】商品
     * 
     * @param sku 商品 【sku】商品信息
     * @return 结果
     */
	int insert(SkuEntity sku);

	int insertBatch(List<SkuEntity> skuList);
	
	/**
     * 修改商品 【sku】商品
     * 
     * @param sku 商品 【sku】商品信息
     * @return 结果
     */
	int update(SkuEntity sku);

	int updateBatch(List<SkuEntity> skuList);
		
    /**
    * 条件查询
	* @param params
	* @return
	*/
    List<SkuEntity> queryByParam(Map<String, Object> params);

    /**
    * 查询总记录
	* @param params
	* @return
	*/
    Integer selectCount(Map<String, Object> params);

    /**
    * 分页查询列表
	* @param params
	* @return
	*/
    List<SkuEntity> selectPageList(Map<String, Object> params);

	int updateSkus(SkuEntity sku);

	/**
	 * 下单商品减库存（未支付占用）
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	Integer reduceStock(Long skuId,int buyCount);

	/**
	 * 订单支付状态更新商品待支付库存
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	int reducePayStock(Long skuId, int buyCount);

	/**
	 * 未支付订单取消+库存（未支付占用）
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	int unPayReturnStock(Long skuId,int buyCount);
	/**
	 * 已支付订单取消+库存
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	int payReturnStock(Long skuId,int buyCount);

	/**
	 * 根据ID查询sku信息
	 * @param idList
	 * @return
	 */
	List<SkuEntity> listByIdList(List<Integer> idList);

	SkuEntity getBySkuId(Long id);

	InfoDetailDto queryItemSkuTopicInfoForAPPHaiTao(String tid,String sku);

	/**
	 * 单个企业商品价格
	 */
	Map<Long, SkuPriceBean> singleCustomPrice(List<Long> skuIds, Long enterprId);

	List<SkuEntity> selectSkuBySpuCode(Map<String, Object> params);
	
	PageResultBean<SkuEntity> selectPage(SkuQueryParam skuQueryParam, PageBean pageBean);

	/**
	 * 更新sku销售状态
	 * 
	 * @param skuId
	 * @param status
	 * @return
	 */
	int updateSkuState(Long skuId, Integer status);

	/**
	 * 更新库存
	 * 
	 * @param skuId
	 * @param goodsStorge
	 * @param  
	 * @return 
	 */
	int updateSkuStock(Long skuId, Integer goodsStorge);

	/**
	 * 获取库存更新记录
	 * 
	 * @param skuId
	 * @return
	 */
	PageResultBean<SkuStorageRecorder> getSkuStorageRecorders(Long skuId, PageBean pageBean);

	SkuEntity getSkuDetailInfo(Long skuId);

	List<SkuEntity> selectByVoucherId(Map<String,Object> params);

	Integer countByVoucherId(Map<String,Object> params);

	List<SkuEntity> selectSkuByseckillId(Map<String,Object> params);

	Integer countSkuByseckillId(Map<String,Object> params);

}
