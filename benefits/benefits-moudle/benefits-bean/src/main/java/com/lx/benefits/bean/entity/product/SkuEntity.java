package com.lx.benefits.bean.entity.product;

import com.lx.benefits.bean.entity.seckill.Seckill;
import com.lx.benefits.bean.entity.voucher.Voucher;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lx.benefits.bean.entity.operation.TopicEntity;

/**
 * 商品 【sku】商品表 sku
 *
 * @author ruoyi
 * @date 2019-02-12
 */
@Data
public class SkuEntity {

	/**
	 * id
	 */
	private Long id;
	/**
	 * spu编码
	 */
	private Long spuCode;
	/**
	 * sku编码
	 */
	private String skuCode;
	/**
	 * 商品规格
	 */
	private String goodsSpec;

	/**
	 * 销售价
	 */
	private BigDecimal goodsPrice;
	/** 成本价 */
	private BigDecimal goodsCostprice;
	/** 市场价 */
	private BigDecimal goodsMarkprice;
	/** 毛利率 */
	private BigDecimal goodsRate;
	/**
	 * 商品库存
	 * <p>
	 *   京东商品没有库存
	 * 33 有货 现货-下单立即发货
	 * 39 有货 在途-正在内部配货，预计2~6天到达本仓库
	 * 40 有货 可配货-下单后从有货仓库配货
	 * 36 预订
	 * 34 无货
	 * <p/>
	 */
	private Integer goodsStorge;
	/**
	 * 待支付库存
	 */
	private Integer payStorge;
	/**
	 * 商品主图
	 */
	private String goodsImage;

	/**
	 * 商品主图
	 */
	private String goodsImages;
	/**
	 * 创建人
	 */
	private String createdUser;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 更新人
	 */
	private String updatedUser;
	/**
	 * 更新时间
	 */
	private Date updatedTime;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 原厂货号
	 */
	private String itemNumber;
	/**
	 * 季节
	 */
	private String season;
	private String brandName;
	private String placeOrigin;
	/**
	 * 长度
	 */
	private BigDecimal length;
	/**
	 * 宽度
	 */
	private BigDecimal width;
	/**
	 * 高度
	 */
	private BigDecimal height;
	private String color;

	/**
	 * 单位
	 */
	private String saleUnit;

	private String countrySize;

	private String voucherIds;

	private List<Voucher> vouchers;

	/**
	 * 商品参数
	 */
	private String param;
	private Long brandId;
	private Long placeOriginId;
	private Long categoryId;
	private Long categoryId2;
	private Long categoryId3;
	private String goodsNameEn;
	private String categoryName;
	private String categoryName2;
	private String categoryName3;
	private Integer sex;
	private Integer isCross;
	private Integer goodsType;
	private BigDecimal goodsFreight;
	private String topicName;
	private Integer goodsState;
	private String introduction;
	private Integer status;

	/** 折扣 */
	private BigDecimal goodsDiscount;

	private List<Long> skus;

	private Integer IsEffect;
	/** 改价生效开始时间*/
	private Date gjValidStarttime;
	/** 改价生效结束时间*/
	private Date gjValidEndtime;
	/**改价折扣*/
	private BigDecimal gjDiscount;

	private Integer sortNum;

	private Long supplierId;

	private List<Long> spuCodeList;
	
	private List<TopicEntity> topicInfos;
	//是否暂停销售
	private Boolean flag;
	//秒杀ID
	private Long seckillId;
	//秒杀信息
	private Seckill seckill;

	private String seckillIds;

}
