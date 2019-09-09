package com.lx.benefits.bean.dto.mem;


import com.lx.benefits.bean.dto.jd.base.BaseDO;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
  * @author szy
  * 联合登录code表，用于保存用户登录到第三方时获取用户信息的code
  */
public class ClientUnionCode extends BaseDO implements Serializable {

	private static final long serialVersionUID = 1543644282691L;

	/** 数据类型bigint(20)*/
	@Id
	private Long id;
	
	/**用户id 数据类型bigint(20)*/
	private Long memberId;
	
	/**用于第三方获取用户信息的code 数据类型varchar(256)*/
	private String code;
	
	/**code的到期时间   */
	private  Long expires;
	
	/** 数据类型datetime*/
	private Date createTime;
	
	/** 数据类型datetime*/
	private Date updateTime;
	
	
	public Long getId(){
		return id;
	}
	public Long getMemberId(){
		return memberId;
	}
	public String getCode(){
		return code;
	}
	public Long getExpires(){
		return expires;
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
	public void setMemberId(Long memberId){
		this.memberId=memberId;
	}
	public void setCode(String code){
		this.code=code;
	}
	public void setExpires(Long expires){
		this.expires=expires;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
}
