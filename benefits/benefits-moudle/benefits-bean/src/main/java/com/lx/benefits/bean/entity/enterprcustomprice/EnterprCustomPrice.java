package com.lx.benefits.bean.entity.enterprcustomprice;


import com.lx.benefits.bean.entity.Entity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EnterprCustomPrice extends Entity {
    /**
     * 企业价格定制id,自增主键ID
     */
    private Long customId;

    /**
     * 所属企业客户id
     */
    private Long enterprId;

    /**
     * 商品id
     */
    private Long goodsId;

    private Long spuId;

    /**
     * 定制单价
     */
    private BigDecimal goodsPrice;

    /**
     * 原价
     */
    private BigDecimal price;

    private String createdUser;

    private String updatedUser;

    private String goodsName;

    private String supplierName;

    private Long supplierId;

}