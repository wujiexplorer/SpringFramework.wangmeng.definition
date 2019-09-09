package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import lombok.Data;

import java.util.List;

/**
 * @author luojie
 */
@Data
public class CreditRecoveryDto {
    /**
     * 操作者id
     */
    private Long optUserId;
    /**
     * 企业客户id
     */
    private Long enterprId;
    /**
     * 节日礼金id
     */
    private Long campaignId;
    /**
     * 回收类型
     */
    private String recoveryRange;
    /**
     * 积分类型
     */
    private Integer creditType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据列表 回收类型为部分回收时使用
     */
    private List<CreditRecoveryDetailDto> detailDtoList;
}
