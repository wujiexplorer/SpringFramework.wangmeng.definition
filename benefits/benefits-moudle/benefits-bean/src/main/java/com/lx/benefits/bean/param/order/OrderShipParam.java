package com.lx.benefits.bean.param.order;

import lombok.Data;

@Data
public class OrderShipParam {

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
}
