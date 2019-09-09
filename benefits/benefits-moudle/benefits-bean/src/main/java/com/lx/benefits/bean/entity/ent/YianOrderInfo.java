package com.lx.benefits.bean.entity.ent;



import com.lx.benefits.bean.dto.jd.annotation.Id;
import com.lx.benefits.bean.dto.jd.base.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
  * @author szy
  * 
  */
public class YianOrderInfo extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1542442000221L;

	/** 数据类型bigint(20)*/
	@Id
	private Long id;
	
	/** 数据类型varchar(128)*/
	private String orderCode;
	
	/** 数据类型varchar(128)*/
	private String orgCode;
	
	/** 数据类型varchar(128)*/
	private String eeNo;
	
	/** 数据类型varchar(128)*/
	private String eeName;
	
	/** 数据类型varchar(128)*/
	private String receiverName;
	
	/** 数据类型varchar(256)*/
	private String receiverAddress;
	
	/** 数据类型varchar(64)*/
	private String receiverMobile;
	
	/** 数据类型varchar(0)*/
	private String postCode;
	
	/** 数据类型double(12,2)*/
	private Double total;
	
	/** 数据类型tinyint(4)*/
	private Integer orderType;
	
	/** 数据类型tinyint(4)*/
	private Integer orderStatus;
	
	/** 数据类型varchar(128)*/
	private String deliveryCompany;
	
	/** 数据类型varchar(128)*/
	private String deliveryCompanyCode;
	
	/** 数据类型varchar(128)*/
	private String deliveryCode;
	
	/** 数据类型varchar(256)*/
	private String virtualCardNo;
	
	/** 数据类型varchar(256)*/
	private String virtualPassword;

	private String virtualIntro;
	
	/** 数据类型datetime*/
	private Date createTime;
	
	/** 数据类型datetime*/
	private Date updateTime;
	
	
	public Long getId(){
		return id;
	}
	public String getOrderCode(){
		return orderCode;
	}
	public String getOrgCode(){
		return orgCode;
	}
	public String getEeNo(){
		return eeNo;
	}
	public String getEeName(){
		return eeName;
	}
	public String getReceiverName(){
		return receiverName;
	}
	public String getReceiverAddress(){
		return receiverAddress;
	}
	public String getReceiverMobile(){
		return receiverMobile;
	}
	public String getPostCode(){
		return postCode;
	}
	public Double getTotal(){
		return total;
	}
	public Integer getOrderType(){
		return orderType;
	}
	public Integer getOrderStatus(){
		return orderStatus;
	}
	public String getDeliveryCompany(){
		return deliveryCompany;
	}
	public String getDeliveryCompanyCode(){
		return deliveryCompanyCode;
	}
	public String getDeliveryCode(){
		return deliveryCode;
	}
	public String getVirtualCardNo(){
		return virtualCardNo;
	}
	public String getVirtualPassword(){
		return virtualPassword;
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
	public void setOrderCode(String orderCode){
		this.orderCode=orderCode;
	}
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	public void setEeNo(String eeNo){
		this.eeNo=eeNo;
	}
	public void setEeName(String eeName){
		this.eeName=eeName;
	}
	public void setReceiverName(String receiverName){
		this.receiverName=receiverName;
	}
	public void setReceiverAddress(String receiverAddress){
		this.receiverAddress=receiverAddress;
	}
	public void setReceiverMobile(String receiverMobile){
		this.receiverMobile=receiverMobile;
	}
	public void setPostCode(String postCode){
		this.postCode=postCode;
	}
	public void setTotal(Double total){
		this.total=total;
	}
	public void setOrderType(Integer orderType){
		this.orderType=orderType;
	}
	public void setOrderStatus(Integer orderStatus){
		this.orderStatus=orderStatus;
	}
	public void setDeliveryCompany(String deliveryCompany){
		this.deliveryCompany=deliveryCompany;
	}
	public void setDeliveryCompanyCode(String deliveryCompanyCode){
		this.deliveryCompanyCode=deliveryCompanyCode;
	}
	public void setDeliveryCode(String deliveryCode){
		this.deliveryCode=deliveryCode;
	}
	public void setVirtualCardNo(String virtualCardNo){
		this.virtualCardNo=virtualCardNo;
	}
	public void setVirtualPassword(String virtualPassword){
		this.virtualPassword=virtualPassword;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public String getVirtualIntro() {
		return virtualIntro;
	}

	public void setVirtualIntro(String virtualIntro) {
		this.virtualIntro = virtualIntro;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof YianOrderInfo)) return false;
		return this.orderCode.equals(((YianOrderInfo)obj).getOrderCode());
	}
}
