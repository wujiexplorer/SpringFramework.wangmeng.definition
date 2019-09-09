package com.lx.benefits.bean.dto.query;


import com.lx.benefits.bean.base.dto.BaseQuery;
import lombok.Data;

/**
 * 搜索查询条件
 * @author zhuss
 * @2016年3月2日 下午2:26:25
 */
@Data
public class QuerySearch extends BaseQuery {

	private static final long serialVersionUID = -4644362382624824840L;

	private String categoryid;//类目ID
	private String brandid;//导航品牌id
	private String key;
	private String sort;
	/**筛选**/
	private String country_id;//产地ID
	private String brand_id;//筛选条件
	private String small_category_id;//筛选条件 小类id
	private String isinstock;//是否只显示有库存的，1是，0否 默认0
	private String salespattern;//销售类型　9 线下团购
	private String couponid;//优惠券Id 用于查询优惠券可用商品

	private String suppliertag;//供应商tag 目前用于搜索京东、严选商品

	private String pricefrom;//价格区间
	private String priceto;//价格区间
	private String selfonly;//只要自营 1 是 其他否
	/**cn 中文  en:英文*/
	private String lan;
}
