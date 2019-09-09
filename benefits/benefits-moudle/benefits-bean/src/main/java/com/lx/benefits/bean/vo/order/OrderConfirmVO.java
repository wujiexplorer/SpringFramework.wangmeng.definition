package com.lx.benefits.bean.vo.order;

import com.lx.benefits.bean.entity.voucher.Voucher;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderConfirmVO {
    /** 购买方式 0：购物车，1：立即购买 */
    private int source;
    /** 积分余额 */
    private BigDecimal pointAmount;
    /** 可用积分 */
    private BigDecimal canUsePointAmount;
    /**商品总金额*/
    private BigDecimal totalGoodsPrice;
    /**运费总金额*/
    private BigDecimal totalFreightPrice;
    /**需实付总金额*/
    private BigDecimal totalRealPrice;
    /** 商家级订单列表 */
    private List<SellerOrderVO> sellerOrderList;
    //购物车是否有员工未领的优惠卷
    private Boolean flag;
    @Data
    public static class SellerOrderVO{
        /**
         * 商家ID
         */
        private Long sellerId;
        /** 商家名称 */
        private String sellerName;
        /**商家级订单运费*/
        private BigDecimal totalFreightPrice;
        /**商家级订单商品总价*/
        private BigDecimal totalGoodsPrice;
        /**买家留言*/
        private String buyerComment;
        /** 配送方式 */
        private int deliverType;
        /** 配送方式描述 */
        private String deliverTypeName;
        /**商家商品集合*/
        private List<ItemOrderVO> itemList;
    }

    @Data
    public static class ItemOrderVO{
    	/** id */
    	private Long id;
        /** skuID */
        private Long skuId;
        /** skuCode */
        private String skuCode;
        /** spuCode */
        private Long spuCode;
        /** 商品名称 */
        private String title;
        /** 商品英文名称 */
        private String titleEn;
        /** 商品主图 */
        private String goodsImage;
        /** 商品规格 */
        private String goodsSpec;
        /** 销售价 */
        private BigDecimal goodsPrice;
        /** 数量 */
        private Integer quantity;
        /** 商品级售价 小计 */
        private BigDecimal totalGoodsPrice;
        /**商品级运费 小计*/
        private BigDecimal totalFreightPrice;
        /** 商品级运费 费 0为免运费 */
        private BigDecimal freight;
        //voucher list
        private List<Voucher> vouchers;
    }
}
