package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: RefundPackage
* @Description:
* @author wind
* @date 2019-2-11
*/
@Data
public class RefundPackage  implements Serializable{
	
    /**
     *主键
     */
	private Long id;
    /**
     *退款申请编号
     */
	private Long refundApplyNumber;
    /**
     *商品级订单编号
     */
	private Long productOrderNumber;
    /**
     *商家id
     */
	private Long sellerId;
    /**
     *包裹编号
     */
	private String packageCode;
    /**
     *物流公司
     */
	private String logisticsChannel;
    /**
     *物流编号
     */
	private String logisticsNumber;
    /**
     *1初始 2首次揽件 3已签收
     */
	private Integer status;
    /**
     *首次物流更新时间
     */
	private Date firstTime;
    /**
     *揽件时间
     */
	private Date takingTime;
    /**
     *确认收货时间
     */
	private Date confirmTime;
    /**
     *扩展信息字段
     */
	private String extra;
    /**
     *0未删除 1已删除
     */
	private Integer isDelete;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *更新时间
     */
	private Date updateTime;


}
