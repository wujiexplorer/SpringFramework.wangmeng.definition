package com.lx.benefits.bean.entity.message;

import com.lx.benefits.bean.dto.jd.annotation.Virtual;
import lombok.Data;

import java.util.Date;

@Data
public class EmailSendRecord {

	/**
     * id [数据类型INTEGER]
     */
    private Integer id;

    /**
     * 短信接收手机号 [数据类型VARCHAR]
     */
    private String email;

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
    
}