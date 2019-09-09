package com.lx.benefits.bean.dto.jdOrder;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 16:00
 */
@Data
public class JdMerchantOrderDto {

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

    private Date createTime;

    private Date updateTime;

    private List<JdMerchantOrderItemDto> item;

}
