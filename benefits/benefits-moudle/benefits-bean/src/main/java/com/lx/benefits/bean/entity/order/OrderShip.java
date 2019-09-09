package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: OrderShip
* @Description:
* @author wind
* @date 2019-2-23
*/
@Data
public class OrderShip implements Serializable{
	
    /**
     *主键ID
     */
	private Long id;
    /**
     *订单编号,取值商家级订单编号
     */
	private Long orderNumber;
    /**
     *买家用户ID
     */
	private Long buyerUserId;
    /**
     *发货类型，关联发货平台发货方式
     */
	private Integer shipType;
    /**
     *发货时间
     */
	private Date shipTime;
    /**
     *快递公司，发货承运快递公司
     */
	private String shipExpress;
    /**
     *快递单号，发货承运快递单号
     */
	private String shipExpressNumber;
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
     *用户身份证号
     */
	private String idCardNo;
    /**
     *扩展字段，扩展存储实名认证、发票信息等
     */
	private String extra;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *修改时间
     */
	private Date updateTime;


}
