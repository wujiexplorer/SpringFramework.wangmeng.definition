package com.lx.benefits.bean.entity.enterprcustomgoods;


import java.math.BigDecimal;

import com.lx.benefits.bean.entity.Entity;
import lombok.Data;

@Data
public class EnterprCustomGoods extends Entity {
    /**
     * 定制商品模块id,自增主键ID
     */
    private Long customId;
    /**
     * 企业id
     */
    private Long enterprId;
    /**
     * 热门商品id集合
     */
    private String hotGoodsIdList;
    private String topicIdsList;
    private String brandIdsList;
    private String categoryIdsList;
    private String supplierIdsList;
    private BigDecimal lowestPrice;

}