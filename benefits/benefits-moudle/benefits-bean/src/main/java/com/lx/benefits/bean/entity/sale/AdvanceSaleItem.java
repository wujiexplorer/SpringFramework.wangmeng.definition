package com.lx.benefits.bean.entity.sale;

import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdvanceSaleItem {

    private Long id;

    /**
     * 所属员工id
     */
    private Long userid;


    private Long tid;

    private Long sku;

    private Integer count;

    private Long campaignId;

    private BigDecimal goodsPrice;

    /**
     * 是否删除 {1:已删除; 0:未删除}
     */
    private Integer isDeleted;

    private Integer status;

    private String orderMsg;
    /**
     * 创建时间
     */
    private Long created;
    /**
     * 更新时间
     */
    private Long updated;

    private String employeeName;

    private String employeeNo;

    private Integer flag;

}