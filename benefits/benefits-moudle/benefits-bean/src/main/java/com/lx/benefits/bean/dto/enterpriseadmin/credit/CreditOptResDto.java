package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-05 22:47.
 */
@ApiModel("企业积分分配AND回收操作记录信息")
public class CreditOptResDto {
    @ApiModelProperty(value = "分配OR回收操作id")
    private Long optId;
    @ApiModelProperty(value = "操作类型 {DISTRIBUTION RECOVERY, HR_DISTRIBUTION_REDUCE, HR_RECOVERY_ADD, HR_DISTRIBUTION_USER_ADD, HR_RECOVERY_USER_REDUCE, USER_ORDER_REDUCE, USER_REFUND_ADD, DISTRIBUTION_TO_EMPLOYEE, ADMIN_DISTRIBUTION_USER_ADD}")
    private String optType;
    @ApiModelProperty(value = "操作人id")
    private Long optUserId;
    @ApiModelProperty(value = "操作人")
    private String optUserName;
    @ApiModelProperty(value = "企业积分分配OR回收备注")
    private String optRemarks;
    @ApiModelProperty(value = "企业积分分配OR回收明细文件")
    private String optFilePath;
    @ApiModelProperty(value = "操作时间 格式：yyyy-MM-dd HH:mm:ss")
    private String optTime;

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public Long getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(Long optUserId) {
        this.optUserId = optUserId;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }

    public String getOptRemarks() {
        return optRemarks;
    }

    public void setOptRemarks(String optRemarks) {
        this.optRemarks = optRemarks;
    }

    public String getOptFilePath() {
        return optFilePath;
    }

    public void setOptFilePath(String optFilePath) {
        this.optFilePath = optFilePath;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }

    @Override
    public String toString() {
        return "CreditOptResDto{" +
                "optId=" + optId +
                ", optType='" + optType + '\'' +
                ", optUserId='" + optUserId + '\'' +
                ", optUserName='" + optUserName + '\'' +
                ", optRemarks='" + optRemarks + '\'' +
                ", optFilePath='" + optFilePath + '\'' +
                ", optTime='" + optTime + '\'' +
                '}';
    }
}
