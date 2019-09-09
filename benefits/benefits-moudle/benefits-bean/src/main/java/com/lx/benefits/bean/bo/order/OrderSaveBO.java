package com.lx.benefits.bean.bo.order;

import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderShip;
import com.lx.benefits.bean.entity.order.ProductOrderEx;
import lombok.Data;

import java.util.List;

@Data
public class OrderSaveBO {

    private int orderStatus;
    /** 支付级订单编号 */
    private Long payOrderNumber;
    /** 用户ID */
    private Long accountId;
    /** 活动id */
    private Long campaignId;
    /** 扣除积分 */
    private Long deductionPointAmount;
    /** 购物车ID集合 */
    private List<Long> cartProductIdList;
    /** 订单记录集合 */
    private List<Order> orderList;
    /** 订单商品记录集合 */
    private List<ProductOrderEx> itemOrderExList;
    /** 收货地址集合 */
    private List<OrderShip> orderShipList;
    /**用户收货地址 -主要为了拿到省市区ID*/
    private ConsigneeAdress userReceiveAddress;
    //优惠卷的Sku
    private String voucherSkus;
    //秒杀数量信息
    private String seckillNum;
}
