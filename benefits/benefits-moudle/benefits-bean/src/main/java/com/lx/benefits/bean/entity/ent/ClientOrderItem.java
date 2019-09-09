package com.lx.benefits.bean.entity.ent;



import com.lx.benefits.bean.dto.jd.annotation.Id;
import com.lx.benefits.bean.dto.jd.base.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
  * @author szy
  * 
  */
public class ClientOrderItem extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1543759246843L;

	/** 数据类型bigint(20)*/
	@Id
	private Long id;
	
	/** 数据类型bigint(20)*/
	private Long clientOrderId;
	
	/** 数据类型bigint(20)*/
	private Long clientOrderCode;
	
	/** 数据类型varchar(128)*/
	private String oid;
	
	/** 数据类型varchar(128)*/
	private String partnerSku;
	
	/** 数据类型int(11)*/
	private Integer quantity;
	
	/** 数据类型double(20,2)*/
	private Double unitPrice;
	
	/** 数据类型datetime*/
	private Date createTime;
	
	/** 数据类型datetime*/
	private Date updateTime;
	
	
	public Long getId(){
		return id;
	}
	public Long getClientOrderId(){
		return clientOrderId;
	}
	public Long getClientOrderCode(){
		return clientOrderCode;
	}
	public String getOid(){
		return oid;
	}
	public String getPartnerSku(){
		return partnerSku;
	}
	public Integer getQuantity(){
		return quantity;
	}
	public Double getUnitPrice(){
		return unitPrice;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setId(Long id){
		this.id=id;
	}
	public void setClientOrderId(Long clientOrderId){
		this.clientOrderId=clientOrderId;
	}
	public void setClientOrderCode(Long clientOrderCode){
		this.clientOrderCode=clientOrderCode;
	}
	public void setOid(String oid){
		this.oid=oid;
	}
	public void setPartnerSku(String partnerSku){
		this.partnerSku=partnerSku;
	}
	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}
	public void setUnitPrice(Double unitPrice){
		this.unitPrice=unitPrice;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
}
