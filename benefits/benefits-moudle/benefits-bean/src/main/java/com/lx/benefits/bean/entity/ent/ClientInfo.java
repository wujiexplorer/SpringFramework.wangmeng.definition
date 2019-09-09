package com.lx.benefits.bean.entity.ent;


import com.lx.benefits.bean.dto.jd.annotation.Id;
import com.lx.benefits.bean.dto.jd.base.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
  * @author szy
  * 第三方客户信息表，用于福利用户登录到第三方系统，目前用于怡安用户登录到其他平台
  */
public class ClientInfo extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1543644282689L;

	/** 数据类型bigint(20)*/
	@Id
	private Long id;
	
	/**第三方商户名称 数据类型varchar(128)*/
	private String clientName;
	
	/**第三方商户id 数据类型varchar(256)*/
	private String clientId;
	
	/**第三方商户secret 数据类型varchar(256)*/
	private String clientSecret;
	
	/**token 数据类型varchar(256)*/
	private String token;
	
	/**token 到期时间 long类型*/
	private Long expires;
	
	/**token有效时间 单位秒 数据类型int(11)*/
	private Integer expiresIn;
	
	/**第三方商户额外信息 数据类型varchar(256)*/
	private String clientExtra;

	private String url;
	
	/** 数据类型datetime*/
	private Date createTime;
	
	/** 数据类型datetime*/
	private Date updateTime;

	/**
	 * 平台对应怡安用户公司编码
	 */
	private String orgCode;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpires() {
		return expires;
	}

	public void setExpires(Long expires) {
		this.expires = expires;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getClientExtra() {
		return clientExtra;
	}

	public void setClientExtra(String clientExtra) {
		this.clientExtra = clientExtra;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
