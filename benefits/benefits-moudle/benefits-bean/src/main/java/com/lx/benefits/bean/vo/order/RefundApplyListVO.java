package com.lx.benefits.bean.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 后台退款列表
 */
@Data
public class RefundApplyListVO {

    private Long id;

    private Long refundApplyNumber;

    private String sellerName;

    private Long sellerOrderNumber;

    private Long itemOrderNumber;

    private Integer type;

    private String reason;

    private String shipToName;

    private String shipToMobile;

    private BigDecimal refundMoney;

    private Integer status;

    private String statusDesc;
    @JsonIgnore
    private String pics;

    private List<String> picList;

    /** 创建时间，创建时间为下单时间 */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;

}
