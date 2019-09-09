package com.lx.benefits.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.dto.admin.product.SkuQueryParam;
import com.lx.benefits.bean.entity.product.SkuEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import com.lx.benefits.bean.vo.cardkey.CardKeyInfo;

/**
 * 商品 【sku】商品 数据层
 * 
 * @author ruoyi
 * @date 2019-02-12
 */
public interface SkuMapper {

	/**
     * 查询商品 【sku】商品信息
     * 
     * @param id 商品 【sku】商品ID
     * @return 商品 【sku】商品信息
     */
	SkuEntity selectById(Long id);

	SkuEntity queryBySkuId(Long id);

	/**
	 * 根据skucode查询商品信息
	 * @param skuCode
	 * @return
	 */
	SkuEntity selectBySkuCode(@Param("skuCode")String skuCode);

	/**
	 * 查询商品 【sku】商品信息
	 * @param sku
	 * @return
	 */
	SkuEntity selectBySku(@Param("sku") String sku,@Param("supplierName") String supplierName);

	String getSpuId(@Param("sku") List<String> sku);

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

	 int updateSkus(SkuEntity sku);

	 int updateBatch(List<SkuEntity> skuList);
	
	int deleteBySpuCodes(String[] spuCodes);

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

	/**
	 * 商品减库存（未支付占用）
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	int reduceStock(@Param("skuId") Long skuId,@Param("buyCount")int buyCount);

	/**
	 * 订单支付状态更新商品待支付库存
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	int reducePayStock(@Param("skuId") Long skuId,@Param("buyCount")int buyCount);


	/**
	 * 未支付订单取消+库存（未支付占用）
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	int unPayReturnStock(@Param("skuId") Long skuId,@Param("buyCount")int buyCount);
	/**
	 * 已支付订单取消+库存
	 * @param skuId
	 * @param buyCount
	 * @return
	 */
	int payReturnStock(@Param("skuId") Long skuId,@Param("buyCount")int buyCount);


	List<SkuEntity> selectByIdList(@Param("idList") List<Integer> idList);

	SkuEntity selectBySkuId(@Param("id") Long id);

	List<SkuEntity> selectByVoucherId(Map<String,Object> params);

	Integer countByVoucherId(Map<String,Object> params);

	List<SkuEntity> selectSkuByseckillId(Map<String,Object> params);

	Integer countSkuByseckillId(Map<String,Object> params);

	List<SkuEntity> selectGjPriceList(@Param("skuIds") List<Long> skuIds);

	List<SkuEntity> selectSkuBySpuCode(Map<String, Object> params);
	
	/**
	 * 获取商品的所有规格列表
	 * 
	 * @param spuCode
	 *            商品ID
	 * @param status
	 *            规格状态，null表示所有，1表示销售中，0表示暂停销售
	 * @return 商品规格列表
	 */
	List<SkuEntity> getSkuBySpuCode(@Param("spuCode") Long spuCode, @Param("status") Integer status);

	int countByQueryParam(SkuQueryParam skuQueryParam);

	List<SkuEntity> selectByQueryParam(@Param("skuQueryParam")SkuQueryParam skuQueryParam, @Param("pageBean")PageBean pageBean);

	SkuEntity selectSkuInfoById(@Param("id")Long id);

	int updateSkuStatusById(SkuEntity skuRecorder);

	int updateSkuStock(@Param("id")Long id, @Param("goodsStorge")Integer goodsStorge,  @Param("updateUserInfo") SkuEntity updateUserInfo);

	String selectByCode(String skuCode);

	List<SkuEntity> getThirdSkuCodeWithPrice(@Param("supplierId") Integer supplierId, @Param("pageBean")PageBean pageBean);
	
	List<SkuEntity> getSupplierSkuCodes(@Param("supplierId") Integer supplierId, @Param("skuCodes") List<String> skuCodes);

	int updateSkuStatus(@Param("skuIds")List<Long> skuIds, @Param("updateUser")String updateUser, @Param("status")int status);
	
	//卡密入库增加库存
	int addStock(@Param("skuId") Long skuId,@Param("stockCount")int stockCount);
	//作废卡密减少库存
	int reduceVirtualStock(@Param("skuId") Long skuId,@Param("stockCount")int stockCount);
	//过期卡自动作废
	int bathReduceVirtualStock(@Param("skuList") List<CardKeyInfo> skuList);
}
