package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import java.util.List;

/**
 * @author luojie
 */
public class CreditDistributionDto {

    /**
     * 操作者id
     */
    private Long optUserId;

    /**
     * 操作备注
     */
    private String remark;

    /**
     * 企业客户id
     */
    private Long enterprId;

    /**
     * 积分类型
     */
    private Integer creditType;

    /**
     * 分配具体情况
     */
    private List<CreditDistributionDetailDto> detailDtoList;

    public Long getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Long optUserId) {
        this.optUserId = optUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public List<CreditDistributionDetailDto> getDetailDtoList() {
        return detailDtoList;
    }

    public void setDetailDtoList(List<CreditDistributionDetailDto> detailDtoList) {
        this.detailDtoList = detailDtoList;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }
}
