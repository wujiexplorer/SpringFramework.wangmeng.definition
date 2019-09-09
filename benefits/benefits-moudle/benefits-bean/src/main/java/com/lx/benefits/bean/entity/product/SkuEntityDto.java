package com.lx.benefits.bean.entity.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: fan
 * Date: 2019/03/06
 * Time: 03:15
 */
@Data
public class SkuEntityDto {

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
    private Object goodsSpec;
    /**
     * 销售价
     */
    private BigDecimal goodsPrice;
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
    private String status;
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
     * 介绍
     */
    private String introduction;
    /**
     * 单位
     */
    private String saleUnit;

    /**
     * 商品参数
     */
    private String param;
    private String goodsImages;
    private Long brandId;
    private Long placeOriginId;
    private Long categoryId;
    private String goodsNameEn;
    private String categoryName;
    private String categoryName2;
    private String categoryName3;
    private Integer sex;
    private Integer isCross;
    private Integer goodsType;
    private BigDecimal goodsFreight;
    private BigDecimal goodsCostprice;
    private BigDecimal goodsMarkprice;
    private String topicName;

    private Integer goodsState;

}
