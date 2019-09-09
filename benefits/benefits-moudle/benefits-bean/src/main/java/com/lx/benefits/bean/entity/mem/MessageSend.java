package com.lx.benefits.bean.entity.mem;

import com.lx.benefits.bean.dto.jd.base.BaseDO;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
  * @author szy
  * 
  */
public class MessageSend extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1502941141658L;

	/** 数据类型bigint(20)*/
	@Id
	private Long id;
	
	/** 数据类型int(2)*/
	private Integer platform;
	
	/** 数据类型varchar(255)*/
	private String messageId;
	
	/**发送状态（1成功，2失败，0未回调） 数据类型int(2)*/
	private Integer state;
	
	/**阿里短信模板code 数据类型varchar(255)*/
	private String templateCode;
	
	/**短信条数 数据类型int(11)*/
	private Integer smsCount;
	
	/**短信接收号码 数据类型varchar(255)*/
	private String receiver;
	
	/** 数据类型datetime*/
	private Date receiveTime;
	
	/**错误码 数据类型varchar(255)*/
	private String errCode;
	
	/** 数据类型varchar(255)*/
	private String errDetail;
	
	/** 数据类型varchar(1000)*/
	private String remark;
	
	/**创建时间 数据类型datetime*/
	private Date createTime;
	
	/**修改时间 数据类型datetime*/
	private Date updateTime;
	
	/** 短信内容 数据类型varchar(1000)*/
	private String content;
	
	/**发送时间 数据类型datetime*/
	private Date sendTime;
	
	/**发送回执ID,可根据该ID查询具体的发送状态*/
	private String bizId;
	
	
	
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getId(){
		return id;
	}
	public Integer getPlatform(){
		return platform;
	}
	public String getMessageId(){
		return messageId;
	}
	public Integer getState(){
		return state;
	}
	public String getTemplateCode(){
		return templateCode;
	}
	public Integer getSmsCount(){
		return smsCount;
	}
	public String getReceiver(){
		return receiver;
	}
	public Date getReceiveTime(){
		return receiveTime;
	}
	public String getErrCode(){
		return errCode;
	}
	public String getErrDetail(){
		return errDetail;
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
	public void setPlatform(Integer platform){
		this.platform=platform;
	}
	public void setMessageId(String messageId){
		this.messageId=messageId;
	}
	public void setState(Integer state){
		this.state=state;
	}
	public void setTemplateCode(String templateCode){
		this.templateCode=templateCode;
	}
	public void setSmsCount(Integer smsCount){
		this.smsCount=smsCount;
	}
	public void setReceiver(String receiver){
		this.receiver=receiver;
	}
	public void setReceiveTime(Date receiveTime){
		this.receiveTime=receiveTime;
	}
	public void setErrCode(String errCode){
		this.errCode=errCode;
	}
	public void setErrDetail(String errDetail){
		this.errDetail=errDetail;
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
