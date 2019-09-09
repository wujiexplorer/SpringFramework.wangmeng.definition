package com.lx.benefits.bean.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderListVO {
    /**供应商ID*/
    private Long sellerId;
    /** 供应商名称 */
    private String sellerName;
    /** 商家级订单编号 */
    private Long sellerOrderNumber;
    /** 支付级订单编号 */
    private Long parentOrderNumber;
    /** 商家级订单价格小计 (包含积分)*/
    private BigDecimal totalPrice;
    /** 商家级订单运费小计 (包含积分) */
    private BigDecimal totalFreight;
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
    /** 创建时间，创建时间为下单时间 */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;
    /** 取消时间 */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date cancelTime;
    /** 商家级商品级订单列表 */
    private List<ItemOrderVO> itemOrderVOList;

}
