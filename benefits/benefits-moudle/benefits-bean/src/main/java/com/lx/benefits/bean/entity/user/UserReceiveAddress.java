package com.lx.benefits.bean.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: UserReceiveAddress
* @Description:
* @author wind
* @date 2019-2-23
*/
@Data
public class UserReceiveAddress implements Serializable{
	
    /**
     *主键ID
     */
	private Long id;
    /**
     *买家用户ID
     */
	private Long userId;
    /**
     *收货人姓名
     */
	private String shipToName;
    /**
     *收货方手机号
     */
	private String shipToMobile;
    /**
     *收货方地址邮编
     */
	private String shipToZip;
    /**
     *收货方地址省份，直辖市省市相同
     */
	private String shipToProvince;
    /**
     *收货方地址市，直辖市省市相同
     */
	private String shipToCity;
    /**
     *收货方地址行政区，市/县级行政区
     */
	private String shipToDistrict;
    /**
     *收货方地址镇，市级行政区对应街道/县级行政区对应城镇
     */
	private String shipToTown;
    /**
     *收货方详细收货地址，街道、小区、门牌号
     */
	private String shipToAddress;
    /**
     *用户真实姓名
     */
	private String userRealName;
    /**
     *身份证号码
     */
	private String idCardNo;
    /**
     *扩展字段，扩展存储实名认证、发票信息等
     */
	private String extra;
    /**
     *是否默认收货地址 0：否 ，1：是
     */
	private Integer isDefault;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *修改时间
     */
	private Date updateTime;


}
