package com.lx.benefits.bean.entity.agent;

import java.io.Serializable;
import java.util.Date;

public class AgentAccountInfo implements Serializable {
    private Integer id;

    private String loginName;

    private String password;

    private String secret;

    private Integer agentType;

    private String agentName;

    private String agentIdentityId;

    private String agentRegisterAddress;

    private String certifyImageUrls;

    private String contactUser;

    private String contactPhone;

    private String contactEmail;

    private Integer accountStatus;

    private String contractNumber;

    private Integer agentLevel;

    private Integer parentId;

    private Date bindTime;

    private String registeredType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getAgentIdentityId() {
        return agentIdentityId;
    }

    public void setAgentIdentityId(String agentIdentityId) {
        this.agentIdentityId = agentIdentityId == null ? null : agentIdentityId.trim();
    }

    public String getAgentRegisterAddress() {
        return agentRegisterAddress;
    }

    public void setAgentRegisterAddress(String agentRegisterAddress) {
        this.agentRegisterAddress = agentRegisterAddress == null ? null : agentRegisterAddress.trim();
    }

    public String getCertifyImageUrls() {
        return certifyImageUrls;
    }

    public void setCertifyImageUrls(String certifyImageUrls) {
        this.certifyImageUrls = certifyImageUrls == null ? null : certifyImageUrls.trim();
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser == null ? null : contactUser.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber == null ? null : contractNumber.trim();
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getRegisteredType() {
        return registeredType;
    }

    public void setRegisteredType(String registeredType) {
        this.registeredType = registeredType == null ? null : registeredType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}