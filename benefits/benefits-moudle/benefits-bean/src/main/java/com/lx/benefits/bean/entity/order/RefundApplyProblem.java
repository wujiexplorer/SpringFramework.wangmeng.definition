package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: RefundApplyProblem
* @Description:
* @author wind
* @date 2019-3-1
*/
@Data
public class RefundApplyProblem implements Serializable{
	
    /**
     *主键
     */
	private Integer id;
    /**
     *退款原因
     */
	private String name;
    /**
     *0：未知 1：退款 2：退货
     */
	private Integer type;
    /**
     *是否删除 0：未删除 1：删除
     */
	private Integer isDetele;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *更新时间
     */
	private Date updateTime;


}
