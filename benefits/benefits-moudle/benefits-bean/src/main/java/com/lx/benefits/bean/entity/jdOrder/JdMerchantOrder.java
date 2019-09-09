package com.lx.benefits.bean.entity.jdOrder;

import lombok.Data;

import java.util.Date;

@Data
public class JdMerchantOrder {
    private Long id;

    private Long subOrderCode;

    private String merchantOrderId;

    private Double freight;

    private Double orderPrice;

    private Double orderNakedPrice;

    private Double orderTaxPrice;

    private Long memberId;

    private Integer state;

    private Integer type;

    private String merchantParentOrderId;

    private Integer orderState;

    private Integer submitState;

    private Double baseFreight;

    private Boolean isDelete;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}