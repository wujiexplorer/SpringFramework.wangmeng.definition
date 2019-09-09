package com.lx.benefits.bean.entity.pay;

import lombok.Data;

import java.io.Serializable;

/**
* @ClassName: PayAccount
* @Description:
* @author wind
* @date 2019-2-10
*/
@Data
public class PayAccount  implements Serializable{
	
    /**
     *自增主键
     */
	private Integer id;
    /**
     *支付渠道：1：支付宝，2：微信
     */
	private Integer channel;
    /**
     *支付账号
     */
	private String payAccount;
    /**
     *
     */
	private String key;


}
