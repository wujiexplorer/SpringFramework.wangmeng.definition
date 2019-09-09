package com.lx.benefits.service.product;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.bo.product.EsProductBean;
import com.lx.benefits.bean.dto.admin.product.ProductQueryParam;
import com.lx.benefits.bean.dto.base.ImportReqDto;
import com.lx.benefits.bean.dto.product.ProductSearchBean;
import com.lx.benefits.bean.dto.spec.ProductBean;
import com.lx.benefits.bean.dto.spec.ProductRequestBean;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品基本 【spu】 服务层
 * 
 * @author ruoyi
 * @date 2019-02-12
 */
public interface ProductService {
	/**
	 * 查询商品基本 【spu】信息
	 * 
	 * @param id
	 *            商品基本 【spu】ID
	 * @return 商品基本 【spu】信息
	 */
	ProductEntity selectById(Long id);

	ProductEntity getProductBySkuCode(String skuCode, Integer supplierId);

	/**
	 * 新增商品基本 【spu】
	 * 
	 * @param product
	 *            商品基本 【spu】信息
	 * @return 结果
	 */
	int insert(ProductEntity product);

	int insertBatch(ProductEntity product, List<SkuEntity> list);

	/**
	 * 修改商品基本 【spu】
	 * 
	 * @param product
	 *            商品基本 【spu】信息
	 * @return 结果
	 */
	int update(ProductEntity product);

	int updateBatch(ProductEntity product, List<SkuEntity> list);

	/**
	 * 删除商品基本 【spu】信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	int deleteByIds(String ids);

	int updateBatch(Map<String, Object> params);

	int updateProduct(ProductEntity product);

	/**
	 * 条件查询
	 * 
	 * @param params
	 * @return
	 */
	List<ProductEntity> queryByParam(Map<String, Object> params);

	List<ProductEntity> selectFilterCategory();

	List<ProductEntity> selectFilterYxCategory();

	/**
	 * 校验商品是否属于专题
	 * 
	 * @param campaignId
	 * @param topicId
	 * @param spuCodeList（spuCode）
	 * @return
	 */
	Boolean checkProductMix(Long campaignId, Long topicId, List<Long> spuCodeList);

	/**
	 * 根据专题id查询商品
	 * 
	 * @param params
	 * @return
	 */
	List<ProductEntity> queryProductsByTopicId(Map<String, Object> params);

	List<ProductEntity> querySkuByTopicId(Map<String, Object> params);

	Integer querySkuByTopicIdCount(Map<String, Object> params);

	List<ProductEntity> queryFeatureList(Map<String, Object> params);

	Integer queryProductsByTopicIdCount(Map<String, Object> params);

	/**
	 * 根据节日礼金id查询商品
	 * 
	 * @param params
	 * @return
	 */
	List<ProductEntity> queryProductsByCampaignId(Map<String, Object> params);

	/**
	 * 自营商品导入
	 * 
	 * @return
	 */
	JSONObject batchImport(ImportReqDto reqDto);

	JSONObject batchImport(ImportReqDto reqDto, Long supplierId);

	List<ProductEntity> listBySpuCodeList(List<Long> spuCodeList);

	List<ProductEntity> selectSkuBySpuCode(Map<String, Object> params);

	void addProduct(ProductRequestBean productRequestBean, Integer supplierId);

	ProductBean getPCProductSpecBean(Long spuCode);
	
	ProductBean getProductSpecBean(Long spuCode);

	List<SkuEntity> getSkuBySpuCode(Long spuCode, Integer status);

	/**
	 * 更新商品上架状态
	 * 
	 * @param spuCode
	 *            spuID
	 * @param goodsState
	 *            上架状态
	 * @return
	 */
	int updateProductState(Long spuCode, Integer goodsState);

	PageResultBean<ProductEntity> selectPage(ProductQueryParam queryParam, PageBean pageBean);

	ProductEntity getBasicInfo(Long spuCode);

	int setGoodsSaleRate(BigDecimal goodsRate);

	BigDecimal getGoodsSaleRate();

	int updateProductState(List<Long> spuCodeList, Integer type);

	int updateProductState(ProductQueryParam queryParam, Integer type);

	PageResultBean<ProductEntity> getProductsByPage(Long enterprId, ProductSearchBean productSearchBean, PageBean pageBean);
	
	PageResultBean<ProductEntity> getProductsByPage(Long enterprId, ProductSearchBean productSearchBean, boolean isWhitelist, PageBean pageBean);

	/**
	 * 查询企业黑名单的商品
	 * @param
	 * @return
	 */
	List<ProductEntity> getBlackProductsByPage();

	//List<ProductEntity> getProducts

	/**
	 * 获取sku的规格值
	 * @param skuIdLongList
	 * @return sku的规格map，其中多个规格值通过“/”连接
	 */
	Map<Long, String> getSkuSpecValue(List<Long> skuIdIds);

	int udpateProductInfo(Long spuCode, ProductBean productBean);

	String getProductIntruduction(Long spuCode);

	List<EsProductBean> getEsProductBeansByPage(PageBean pageBean);

	List<EsProductBean> getEsProductBeansByIds(List<Long> spuCodes);

	ProductBean getPCProductSpecBean(Long spuCode, Long campaignId);


	List<ProductEntity> selectSpusByVoucherId(Map<String,Object> params);

	Integer countSpusByVoucherId(Map<String,Object> params);

}
