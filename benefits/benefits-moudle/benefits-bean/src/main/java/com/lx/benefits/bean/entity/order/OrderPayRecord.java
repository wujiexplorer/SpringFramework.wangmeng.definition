package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: OrderPayRecord
* @Description:
* @author wind
* @date 2019-3-1
*/
@Data
public class OrderPayRecord implements Serializable{
	
    /**
     *订单支付id
     */
	private Long id;
    /**
     *支付级订单编号
     */
	private Long payOrderNumber;
    /**
     *付款渠道；1：支付宝，2：微信
     */
	private Integer payChannel;
    /**
     *支付标识
     */
	private String payMark;
    /**
     *财付通订单号
     */
	private String payTid;
    /**
     *是否支付；0：否，1：是
     */
	private Integer isPay;
    /**
     *商家收款账户
     */
	private Integer sellerPayId;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *更新时间
     */
	private Date updateTime;


}
