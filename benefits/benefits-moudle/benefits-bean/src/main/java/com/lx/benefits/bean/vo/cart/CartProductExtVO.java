package com.lx.benefits.bean.vo.cart;

import com.lx.benefits.bean.entity.voucher.Voucher;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @ClassName: CartProductExtVO
* @Description:
* @author wind
* @date 2019-2-11
*/
@Data
public class CartProductExtVO implements Serializable {

    /**
     * 购物车ID
     */
    private Long cartProductId;
    /**
     *商品id
     */
    private Long spuCode;
    /**
     *sku ID
     */
    private Long skuId;
    /**
     *卖家id
     */
    private Long sellerId;
    /**
     * sku主题
     */
    private String title;
    /**
     * sku主题en
     */
    private String titleEn;
    /**
     * spec规格
     */
    private String spec;
    /**
     * sku 图片
     */
    private String skuImg;
    /**
     * 市场价
     */
    private BigDecimal marketPrice;
    /**
     * 售价
     */
    private BigDecimal salePrice;
    /**
     * 邮费
     */
    private BigDecimal freight;
    /**
     * 节日礼金活动ID
     */
    private Long activityId;
    /**
     *购物车商品数量
     */
    private Integer count;
    /**
     *是否选中，0：未选中，1：选中
     */
    private Integer checked;

    /** 商品状态 商品状态 0下架，1正常，10违规（禁售） */
    private Integer goodsState;
    /** 商品级订单价格 小计 */
    private BigDecimal totalGoodsPrice;
    /**商品级订单运费 小计*/
    private BigDecimal totalFreightPrice;

    private Integer status;//是否暂停销售

    private Boolean flag;//是否已下架

    private Integer goodStorage;

    private List<Voucher> vouchers;//优惠卷列表
}
