package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import java.math.BigDecimal;

/**
 * 积分操作记录
 *
 * @author luojie
 */
public class CreditOperateInfoDto {
    /**
     * 积分操作id,自增主键ID
     */
    private Long optId;

    /**
     * 积分接收操作id,如当前积分是那一次操作过来的
     */
    private Long parentOptId;

    /**
     * 积分所有者用户id，企业OR员工userId
     */
    private Long ownerUserId;

    /**
     * 积分类型{1: 普通积分; 2: 节日礼金; 3: 认可激励积分}
     */
    private Integer creditType;

    /**
     * 活动id
     */
    private Long campaignId;

    /**
     * 积分数额
     */
    private BigDecimal credit;

    /**
     * 操作者userId
     */
    private Long optUserId;

    /**
     * 操作人
     */
    private String optUserName;

    /**
     * 操作时间
     */
    private Integer optTime;

    /**
     * 审核者userId
     */
    private Long auditUserId;

    /**
     * 审核时间
     */
    private Integer auditTime;

    /**
     * 操作类型{1: 运营积分充值; 2: 运营积分退款; 3: HR积分分配扣减企业积分; 4: HR积分回收增加企业积分; 5: HR积分分配员工增加积分; 6: HR积分回收员工扣减积分; 7: 员工下单扣积分; 8: 员工退货退款时退积分; }
     */
    private Integer optType;

    /**
     * 积分审核状态,目前optType=1or2才需要审核,其他操作默认为已审核{1: 待审核; 2: 已审核; 3: 已拒绝}
     */
    private Integer auditStatus;

    /**
     * 操作备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 更新时间
     */
    private Long updated;

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Long getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Long optUserId) {
        this.optUserId = optUserId;
    }

    public Integer getOptTime() {
        return optTime;
    }

    public void setOptTime(Integer optTime) {
        this.optTime = optTime;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Integer getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Integer auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getParentOptId() {
        return parentOptId;
    }

    public void setParentOptId(Long parentOptId) {
        this.parentOptId = parentOptId;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }
}