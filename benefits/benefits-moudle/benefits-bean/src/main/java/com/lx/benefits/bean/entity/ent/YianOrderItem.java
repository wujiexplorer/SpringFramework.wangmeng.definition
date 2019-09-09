package com.lx.benefits.bean.entity.ent;



import com.lx.benefits.bean.dto.jd.annotation.Id;
import com.lx.benefits.bean.dto.jd.base.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
  * @author szy
  * 
  */
public class YianOrderItem extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1542442000228L;

	/** 数据类型bigint(20)*/
	@Id
	private Long id;
	
	/** 数据类型varchar(128)*/
	private String orderCode;
	
	/** 数据类型varchar(128)*/
	private String sku;
	
	/** 数据类型varchar(256)*/
	private String itemName;
	
	/** 数据类型int(11)*/
	private Integer count;
	
	/** 数据类型double(12,2)*/
	private Double price;
	
	/** 数据类型double(12,2)*/
	private Double total;
	
	/** 数据类型varchar(128)*/
	private String supName;
	
	/** 数据类型datetime*/
	private Date createTime;
	
	
	public Long getId(){
		return id;
	}
	public String getOrderCode(){
		return orderCode;
	}
	public String getSku(){
		return sku;
	}
	public String getItemName(){
		return itemName;
	}
	public Integer getCount(){
		return count;
	}
	public Double getPrice(){
		return price;
	}
	public Double getTotal(){
		return total;
	}
	public String getSupName(){
		return supName;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setId(Long id){
		this.id=id;
	}
	public void setOrderCode(String orderCode){
		this.orderCode=orderCode;
	}
	public void setSku(String sku){
		this.sku=sku;
	}
	public void setItemName(String itemName){
		this.itemName=itemName;
	}
	public void setCount(Integer count){
		this.count=count;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	public void setTotal(Double total){
		this.total=total;
	}
	public void setSupName(String supName){
		this.supName=supName;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
}
