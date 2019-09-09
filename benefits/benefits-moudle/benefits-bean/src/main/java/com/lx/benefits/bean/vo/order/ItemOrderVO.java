package com.lx.benefits.bean.vo.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemOrderVO {

    /** 商品级订单编号 */
    private Long itemOrderNumber;
    /** 商家级订单编号 */
    private Long sellerOrderNumber;
    /** skuID */
    private Integer skuId;
    /** spu编码 */
    private Long spuCode;
    /** 商品类型*/
    private Integer goodsType;
    /** 商品图片 */
    private String goodsImg;
    /** 商品名称 */
    private String title;
    /** 商品英文名称 */
    private String titleEn;
    /** 商品规格 */
    private String goodsSpec;
    /** 销售价 */
    private BigDecimal goodsPrice;
    /** 数量 */
    private Integer quantity;
    /** 积分抵扣商品金额 */
    @JsonIgnore
    private BigDecimal pointAmount;
    /** 积分抵扣商品运费 */
    @JsonIgnore
    private BigDecimal shipExpensePointAmount;
    /** 小计 */
    private BigDecimal totalGoodsPrice;
    /** 运费小计 0为免运费 */
    private BigDecimal totalGoodsFreight;
    /** 逆向状态 */
    private Integer reverseStatus;
    /** 逆向状态描述 */
    private String reverseStatusDesc;
    /** 物流信息 */
    private LogisticsVO logistics;
    
    
}
