package com.lx.benefits.bean.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-04 03:11.
 */
@ApiModel("企业登录Session信息")
public class SessionEnterpriseInfo implements SessionInfo{

    @ApiModelProperty(value = "企业id")
    private Long enterprId;
    @ApiModelProperty(value = "企业登录用户名")
    private String loginName;
    @ApiModelProperty(value = "企业名称")
    private String enterprName;
    
    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEnterprName() {
        return enterprName;
    }

    public void setEnterprName(String enterprName) {
        this.enterprName = enterprName;
    }

    @Override
    public String toString() {
        return "SessionEnterpriseInfo{" +
                "enterprId=" + enterprId +
                ", loginName='" + loginName + '\'' +
                ", enterprName='" + enterprName + '\'' +
                '}';
    }
}