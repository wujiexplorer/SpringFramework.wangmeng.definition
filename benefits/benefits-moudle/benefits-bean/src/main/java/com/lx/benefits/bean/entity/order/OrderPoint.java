package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: OrderPoint
* @Description:
* @author wind
* @date 2019-2-23
*/
@Data
public class OrderPoint implements Serializable{
	
    /**
     *主键ID
     */
	private Long id;
    /**
     *积分账户ID
     */
	private Long creditId;
    /**
     *用户ID
     */
	private Long userId;
    /**
     *支付级订单编号
     */
	private Long payOrderNumber;
    /**
     *使用积分
     */
	private Long usedPoint;
    /**
     *删除状态 0：未删除 1：已删除
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
