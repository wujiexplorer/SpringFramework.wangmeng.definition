package com.lx.benefits.bean.dto.jdOrder;

import lombok.Data;

import java.util.Date;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 16:02
 */
@Data
public class JdMerchantOrderItemDto {

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

    private Date updateTime;

}
