package com.lx.benefits.bean.param.order;

import com.apollo.common.bean.bo.BasePageQueryBO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ItemOrderListQueryParam extends BasePageQueryBO {
    /**订单状态*/
    private Integer status;
    /**订单支付渠道*/
    private Integer payChannel;
    /**商家级订单编号*/
    private Long sellerOrderNumber;
    /**商品级订单编号*/
    private Long number;
    /**购买账号*/
    private String loginName;
    /**联系电话*/
    private String shipToMobile;
    /**订单创建起始时间*/
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date orderCreateTimeStart;
    /**订单创建截止时间*/
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date orderCreateTimeEnd;
    /**一级类目*/
    private Integer categoryId;
    /**二级类目*/
    private Integer categoryId2;
    /**三级类目*/
    private Integer categoryId3;
    /**商家ID*/
    private Long sellerId;
    /**品牌id*/
    private Long productBrandId;
    /**企业id*/
    private Long enterprId;
    /**商品名称*/
    private String title;
}
