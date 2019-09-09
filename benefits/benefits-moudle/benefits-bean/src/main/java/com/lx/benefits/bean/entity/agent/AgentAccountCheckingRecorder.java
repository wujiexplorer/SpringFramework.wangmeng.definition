package com.lx.benefits.bean.entity.agent;

import java.io.Serializable;
import java.util.Date;

public class AgentAccountCheckingRecorder implements Serializable {
    private Integer id;

    private Integer agentType;

    private String agentName;

    private String agentIdentityId;

    private String agentRegisterAddress;

    private String certifyImageUrls;

    private String contactUser;

    private String contactPhone;

    private String contactEmail;

    private String addUser;

    private Date addTime;

    private Integer checkStatus;

    private String checkUser;

    private Date checkTime;

    private String loginName;

    private String checkRemark;

    private String contractNumber;

    private Integer agentLevel;

    private Integer parentId;

    private Date bindTime;

    private String registeredType;

    private Integer deleted;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser == null ? null : addUser.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser == null ? null : checkUser.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark == null ? null : checkRemark.trim();
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "AgentAccountCheckingRecorder [id=" + id + ", agentType=" + agentType + ", agentName=" + agentName
				+ ", agentIdentityId=" + agentIdentityId + ", agentRegisterAddress=" + agentRegisterAddress
				+ ", certifyImageUrls=" + certifyImageUrls + ", contactUser=" + contactUser + ", contactPhone="
				+ contactPhone + ", contactEmail=" + contactEmail + ", addUser=" + addUser + ", addTime=" + addTime
				+ ", checkStatus=" + checkStatus + ", checkUser=" + checkUser + ", checkTime=" + checkTime
				+ ", loginName=" + loginName + ", checkRemark=" + checkRemark + ", contractNumber=" + contractNumber
				+ ", agentLevel=" + agentLevel + ", parentId=" + parentId + ", bindTime=" + bindTime
				+ ", registeredType=" + registeredType + ", deleted=" + deleted + ", updateTime=" + updateTime + "]";
	}
    
}