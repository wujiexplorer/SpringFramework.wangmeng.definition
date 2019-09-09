package com.lx.benefits.bean.param.order;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 立即购买订单参数
 */
@Data
public class OrderBuyNowParam {

    /**
     * 节日礼金活动ID
     */
    private long campaginId;
    /**
     * skuId集合
     */
    @NotEmpty(message = "商品不能为空")
    private List<SkuParam> skuList;
    
    private Long topicId;
    /**
     * 收货地址ID
     */
    private Long userReceiveAddrId;

    @Data
    public static class SkuParam{
        /**
         * skuID
         */
        @NotNull(message = "请选择商品")
        private Long skuId;
        /**
         * 活动ID
         */
        @NotNull(message = "请选择商品数量")
        private Integer quantity;
    }
    //秒杀ID
    private Long seckillId;
}
