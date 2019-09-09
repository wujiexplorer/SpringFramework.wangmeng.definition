package com.lx.benefits.bean.entity.orderimportitem;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderImportItem {

    private Long detailId;
    /**
     * 导入记录id,自增主键ID
     */
    private Long importId;
    /**
     * 所属员工id
     */
    private Long userid;


    private Long tid;

    private Long sku;

    private Long count;

    private Long campaignId;

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * 是否删除 {1:已删除; 0:未删除}
     */
    private Integer isDeleted;

    private Integer status;

    private String orderMsg;
    /**
     * 创建时间
     */
    private Integer created;
    /**
     * 更新时间
     */
    private Integer updated;

    private String employeeName;

    private BigDecimal goodsPrice;

}