package com.lx.benefits.bean.entity.jdOrder;

import lombok.Data;

import java.util.Date;

@Data
public class JdMerchantOrderItem {
    private Long id;

    private String orderId;

    private String sku;

    private Integer num;

    private Long category;

    private Double price;

    private String name;

    private Double tax;

    private Double taxPrice;

    private Double nakedPrice;

    private Integer type;

    private Long oid;

    private Boolean isDelete;

    private Double remoteRegionFreight;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}