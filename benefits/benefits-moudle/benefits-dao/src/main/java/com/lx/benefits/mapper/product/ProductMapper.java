package com.lx.benefits.mapper.product;

import java.util.List;
import java.util.Map;

import com.lx.benefits.bean.entity.product.SkuEntity;
import org.apache.ibatis.annotations.Param;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.bo.product.EsProductBean;
import com.lx.benefits.bean.dto.admin.product.ProductQueryParam;
import com.lx.benefits.bean.dto.product.ProductSearchBean;
import com.lx.benefits.bean.entity.product.ProductEntity;

/**
 * 商品基本 【spu】 数据层
 * 
 * @author ruoyi
 * @date 2019-02-12
 */
public interface ProductMapper 
{
	/**
     * 查询商品基本 【spu】信息
     * 
     * @param id 商品基本 【spu】ID
     * @return 商品基本 【spu】信息
     */
	ProductEntity selectById(Long id);

	ProductEntity getProductBySkuCode(@Param("skuCode") String skuCode,@Param("supplierId") Integer supplierId);
	
	/**
     * 查询商品基本 【spu】列表
     * 
     * @param product 商品基本 【spu】信息
     * @return 商品基本 【spu】集合
     */
	 List<ProductEntity> selectList(ProductEntity product);
	
	/**
     * 新增商品基本 【spu】
     * 
     * @param product 商品基本 【spu】信息
     * @return 结果
     */
	 int insert(ProductEntity product);
	
	/**
     * 修改商品基本 【spu】
     * 
     * @param product 商品基本 【spu】信息
     * @return 结果
     */
	 int update(ProductEntity product);

	 int updateBatch(Map<String,Object> params);

	 int updateProduct(ProductEntity product);
	
	/**
     * 删除商品基本 【spu】
     * 
     * @param id 商品基本 【spu】ID
     * @return 结果
     */
	 int deleteById(Integer id);
	
	/**
     * 批量删除商品基本 【spu】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteByIds(String[] ids);

    /**
    * 条件查询
	* @param params
	* @return
	*/
    List<ProductEntity> queryByParam(Map<String, Object> params);

	List<ProductEntity> selectSpusByVoucherId(Map<String,Object> params);

	Integer countSpusByVoucherId(Map<String,Object> params);

	int reduceStock(Map<String, Object> params);

	List<ProductEntity> selectBySpuCodeList(@Param("spuCodeList") List<Long> spuCodeList);

	List<ProductEntity> queryProductsByTopicId(Map<String, Object> params);

	List<ProductEntity> querySkuByTopicId(Map<String, Object> params);

	Integer querySkuByTopicIdCount(Map<String, Object> params);

	List<ProductEntity> queryFeatureList(Map<String, Object> params);

	Integer queryProductsByTopicIdCount(Map<String, Object> params);

	List<ProductEntity> selectBrandByCategoryId(Map<String, Object> params);

	List<ProductEntity> selectPlaceOrginByCategoryId(Map<String, Object> params);

	List<ProductEntity> selectFilterCategory();

	List<ProductEntity> selectFilterYxCategory();

	List<ProductEntity> selectContion(Map<String,Object> params);

	List<ProductEntity> selectContionCategory(Map<String,Object> params);

	List<ProductEntity> selectSkuBySpuCode(Map<String, Object> params);
	
	int selectCount(@Param("queryParam")ProductQueryParam queryParam);

	List<ProductEntity> selectPage(@Param("queryParam")ProductQueryParam queryParam, @Param("pageBean")PageBean pageBean);

	ProductEntity getBasicInfo(@Param("spuCode")Long spuCode);

	int batchUpdateProductState(@Param("spuCodeList")List<Long> spuCodeList, @Param("product")ProductEntity product);

	int countProducts(@Param("searchBean")ProductSearchBean productSearchBean);

	List<ProductEntity> getProductsByPage(@Param("searchBean") ProductSearchBean productSearchBean, @Param("pageBean") PageBean pageBean);

	List<ProductEntity> getBlackProducts(@Param("searchBean") ProductSearchBean productSearchBean);

	int updateProductStateByQuery(@Param("queryParam")ProductQueryParam queryParam, @Param("product")ProductEntity product);

	String getProductIntruduction(Long spuCode);
	
	List<Integer> selectGoodsStatus(@Param("queryParam")ProductQueryParam queryParam);

	List<EsProductBean> selectEsProductBeansByPage(@Param("pageBean")PageBean pageBean);
	
	List<EsProductBean> selectEsProductBeansByIds(@Param("spuCodeList")List<Long> spuCodeList);
}
