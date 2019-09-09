package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: OrderPay
* @Description:
* @author wind
* @date 2019-2-10
*/
@Data
public class OrderPay  implements Serializable{
	
    /**
     *id
     */
	private Long id;
    /**
     *支付级别订单号
     */
	private Long payOrderNumber;
    /**
     *付款渠道；1：支付宝，2：微信
     */
	private Integer payChannel;
    /**
     *平台支付标识号
     */
	private String payMark;
    /**
     *外部回调标识号
     */
	private String payTid;
    /**
     *第三方支付公司的用户id
     */
	private String buyerId;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *更新时间
     */
	private Date updateTime;


}
