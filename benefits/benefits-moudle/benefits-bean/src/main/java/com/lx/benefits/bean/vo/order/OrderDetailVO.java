package com.lx.benefits.bean.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lx.benefits.bean.entity.order.OrderShip;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDetailVO {
    /**供应商ID*/
    private Long sellerId;
    /** 供应商名称 */
    private String sellerName;
    /** 商家级订单编号 */
    private Long sellerOrderNumber;
    /** 支付级订单编号 */
    private Long parentOrderNumber;
    /** 商家级订单价格小计 */
    private BigDecimal totalPrice;
    /** 商家级订单运费小计 */
    private BigDecimal totalFreight;
    /** 商家级订单运费小计 */
    private BigDecimal deductionPointAmount;
    /** 商家级订单实付金额 */
    private BigDecimal totalRealPrice;
    /** 订单状态 */
    private Integer status;
    /** 订单状态描述 */
    private String statusDesc;
    /** 商家级订单商品总件数 */
    private Integer count;
    /** 节日礼金ID */
    private Long campaignId;
    /**买家备注*/
    private String buyerComment;
    @JsonFormat(pattern="YYYY-MM-dd HH:mm:ss")
    private Date payTime;
    @JsonFormat(pattern="YYYY-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern="YYYY-MM-dd HH:mm:ss")
    private Date cancelTime;
    /** 商家级商品级订单列表 */
    private List<ItemOrderVO> itemOrderVOList;
    /**订单收货地址信息*/
    private OrderShipVO orderShip;
    /**订单快递信息*/
    private List<LogisticsVO> logisticsList;
    //优惠卷优惠的金额
    private BigDecimal voucherBenefit;

}
