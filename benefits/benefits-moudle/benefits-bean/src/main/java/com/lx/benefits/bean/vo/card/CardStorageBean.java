package com.lx.benefits.bean.vo.card;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CardStorageBean {
	private String cardNumber;// 会员卡号
	private Integer amount;// 会员卡面值
	private String verifyNumber;// 会员卡校验码
	private Long enterprId;// 绑定的企业ID
	private String enterprName;// 绑定的企业名称
	private Long employeeId;// 会员卡所属员工ID（使用者）
	private Long customerId;// 客户ID
	private String customerName;// 客户名称
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;// 会员卡生成时间
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date storeTime;// 会员卡入库时间
	private String storeUser;// 会员卡入库人
	private Date deliverTime;// 会员卡发卡时间
	private String mobile;// 绑定的手机号
	private String employeeLoginName;// 绑定的账号
	private Date usedTime;// 激活时间
	private Integer status;// 激活状态
}
