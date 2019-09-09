package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: OrderShipLogistics
* @Description:
* @author wind
* @date 2019-3-3
*/
@Data
public class OrderShipLogistics implements Serializable{
	
    /**
     *主键ID
     */
	private Long id;
    /**
     *订单编号,取值商家级订单编号
     */
	private Long sellerOrderNumber;
    /**
     *订单编号,取值商品级订单编号
     */
	private Long itemOrderNumber;
    /**
     *发货时间
     */
	private Date shipTime;
    /**
     *快递公司，发货承运快递公司
     */
	private String shipExpress;
    /**
     *快递单号，发货承运快递单号
     */
	private String shipExpressNumber;
	/**
	 *备注信息
	 */
	private String remark;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *修改时间
     */
	private Date updateTime;


}
