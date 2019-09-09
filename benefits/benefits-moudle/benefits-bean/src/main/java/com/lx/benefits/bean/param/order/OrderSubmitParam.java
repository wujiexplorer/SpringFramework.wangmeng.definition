package com.lx.benefits.bean.param.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 提交订单参数
 */
@Data
public class OrderSubmitParam {

    @JsonIgnore
    private Long accountId;

    /** 下单来源平台，0:全终端(未知平台下单),1:IOS下单,2:Android下单,3:H5下单 */
    @JsonIgnore
    private int platform;

    /**
     * 购买方式 0：购物车，1：立即购买，2：代下单，3：预售
     */
    private int source;

    /**
     * 收货地址ID
     */
    @NotNull(message = "收货地址不能为空")
    private Long userReceiveAddrId;
    /**
     * 节日礼金活动ID
     */
    private long campaginId;
    /**
     * 专题ID
     */
    private Long topicId;
    /**
     * skuId集合
     */
    @NotEmpty(message = "商品不能为空")
    private List<SellerOrderParam> sellerOrderList;

    @Data
    public static class SellerOrderParam{

        private Long sellerId;
        /**买家留言*/
        private String buyerComment;
        /** 配送方式 */
        private int deliverType;
        /**商家商品集合*/
        private List<SkuParam> itemList;
    }


    @Data
    public static class SkuParam{
        /**
         * skuID
         */
        private Integer skuId;
        /**
         * 数量
         */
        private Integer quantity;
    }

    /**
     * 是否只能积分支付
     */
    @JsonIgnore
    private boolean isOnlyPointPay;
    @JsonIgnore
    private Long enterprId;
    //优惠卷IDs
    private String voucherIds;
    //@JsonIgnore
    private String voucherSkus;
    //@JsonIgnore  加了此注解，传的值会被忽略
    private String seckillNum;
}
