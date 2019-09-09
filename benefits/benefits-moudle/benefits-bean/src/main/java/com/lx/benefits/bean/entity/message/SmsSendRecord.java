package com.lx.benefits.bean.entity.message;


import com.lx.benefits.bean.dto.jd.annotation.Virtual;
import java.util.Date;

public class SmsSendRecord {

	/**
     * id [数据类型INTEGER]
     */
    private Integer id;

    /**
     * 短信接收手机号 [数据类型VARCHAR]
     */
    private String mobile;

    /**
     * 短信内容 [数据类型VARCHAR]
     */
    private String content;

    /**
     * 发送时间，普通短信该值等于创建时间，定时短信等于设置的发送时间 [数据类型TIMESTAMP]
     */
    private Date sendTime;

    /**
     * 发送返回码 [数据类型VARCHAR]
     */
    private String rspCode;

    /**
     * 发送返回信息 [数据类型VARCHAR]
     */
    private String rspInfo;

    /**
     * 创建时间 [数据类型TIMESTAMP]
     */
    private Date createTime;

    /**
     * 创建者 [数据类型VARCHAR]
     */
    private String createUser;

    /**
     * 更新时间 [数据类型TIMESTAMP]
     */
    private Date updateTime;

    /**
     * 更新者 [数据类型VARCHAR]
     */
    private String updateUser;
    
    /**开始时间*/
	@Virtual
	private Date createBeginTime;
	/**结束时间*/
	@Virtual
	private Date createEndTime;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 短信接收手机号
     * @return mobile 短信接收手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 短信接收手机号
     * @param mobile 短信接收手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 短信内容
     * @return content 短信内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 短信内容
     * @param content 短信内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 发送时间，普通短信该值等于创建时间，定时短信等于设置的发送时间
     * @return send_time 发送时间，普通短信该值等于创建时间，定时短信等于设置的发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 发送时间，普通短信该值等于创建时间，定时短信等于设置的发送时间
     * @param sendTime 发送时间，普通短信该值等于创建时间，定时短信等于设置的发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 发送返回码
     * @return rsp_code 发送返回码
     */
    public String getRspCode() {
        return rspCode;
    }

    /**
     * 发送返回码
     * @param rspCode 发送返回码
     */
    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    /**
     * 发送返回信息
     * @return rsp_info 发送返回信息
     */
    public String getRspInfo() {
        return rspInfo;
    }

    /**
     * 发送返回信息
     * @param rspInfo 发送返回信息
     */
    public void setRspInfo(String rspInfo) {
        this.rspInfo = rspInfo;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建者
     * @return create_user 创建者
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建者
     * @param createUser 创建者
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新者
     * @return update_user 更新者
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新者
     * @param updateUser 更新者
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

	public Date getCreateBeginTime() {
		return createBeginTime;
	}

	public void setCreateBeginTime(Date createBeginTime) {
		this.createBeginTime = createBeginTime;
	}

	public Date getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}
    
}