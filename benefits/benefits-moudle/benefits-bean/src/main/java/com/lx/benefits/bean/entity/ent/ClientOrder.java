package com.lx.benefits.bean.entity.ent;



import com.lx.benefits.bean.dto.jd.annotation.Id;
import com.lx.benefits.bean.dto.jd.base.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author szy
 */
public class ClientOrder extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1543759246837L;

	/** 数据类型bigint(20)*/
	@Id
	private Long id;
	
	/**我方单号 数据类型bigint(20)*/
	private Long code;
	
	/**我方用户id 数据类型bigint(20)*/
	private Long memberId;
	
	/**	99：未支付	1：已支付	 2：处理中	4：已发货	 5：已完成	6：已退货	 7：已撤销 	8：已部分发货
o	1：已支付
o	2：处理中
o	4：已发货
o	5：已完成
o	6：已退货
o	7：已撤销
o	8：已部分发货
 数据类型tinyint(4)*/
	private Integer status;
	
	/**第三方订单号 数据类型varchar(128)*/
	private String oid;
	
	/**第三方公司号 目前为怡安用户org_code 数据类型varchar(128)*/
	private String orgCode;
	
	/**第三方员工号 目前为怡安用户ee_no 数据类型varchar(128)*/
	private String eeNo;
	
	/**运费 数据类型double*/
	private Double shippingFee;
	
	/**支付渠道  数据类型int(11)*/
	private Integer payChannels;
	
	/**外部订单价格 数据类型double*/
	private Double price;
	
	/**真实订单价格 数据类型double*/
	private Double realPrice;
	
	/**备注 数据类型varchar(512)*/
	private String remark;
	
	/** 数据类型datetime*/
	private Date createTime;
	
	/** 数据类型datetime*/
	private Date updateTime;
	
	
	public Long getId(){
		return id;
	}
	public Long getCode(){
		return code;
	}
	public Long getMemberId(){
		return memberId;
	}
	public Integer getStatus(){
		return status;
	}
	public String getOid(){
		return oid;
	}
	public String getOrgCode(){
		return orgCode;
	}
	public String getEeNo(){
		return eeNo;
	}
	public Double getShippingFee(){
		return shippingFee;
	}
	public Integer getPayChannels(){
		return payChannels;
	}
	public Double getPrice(){
		return price;
	}
	public Double getRealPrice(){
		return realPrice;
	}
	public String getRemark(){
		return remark;
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
	public void setCode(Long code){
		this.code=code;
	}
	public void setMemberId(Long memberId){
		this.memberId=memberId;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public void setOid(String oid){
		this.oid=oid;
	}
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	public void setEeNo(String eeNo){
		this.eeNo=eeNo;
	}
	public void setShippingFee(Double shippingFee){
		this.shippingFee=shippingFee;
	}
	public void setPayChannels(Integer payChannels){
		this.payChannels=payChannels;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	public void setRealPrice(Double realPrice){
		this.realPrice=realPrice;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

}
