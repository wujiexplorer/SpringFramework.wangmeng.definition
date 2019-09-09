package com.lx.benefits.bean.entity.enterpruserinfo;

import java.io.Serializable;
import java.util.Date;

public class EnterprCheckingRecorder implements Serializable {
    private Long enterprId;

    private String enterprName;

    private String subEnterprName;

    private String enterprIdentityId;

    private String enterprRegisterAddress;

    private String certifyImageUrl;

    private String country;

    private String province;

    private String city;

    private String county;

    private String street;

    private String address;

    private Integer leaveCredit;

    private String contactUser;

    private String mobile;

    private String email;

    private String addUser;

    private Date addTime;

    private Integer agentId;

    private String checkUser;

    private Date checkTime;

    private Integer checkStatus;

    private String contractNumber;

    private String loginName;

    private String checkRemark;

    private String registeredType;

    private Integer deleted;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getEnterprId() {
        return enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public String getEnterprName() {
        return enterprName;
    }

    public void setEnterprName(String enterprName) {
        this.enterprName = enterprName == null ? null : enterprName.trim();
    }

    public String getSubEnterprName() {
        return subEnterprName;
    }

    public void setSubEnterprName(String subEnterprName) {
        this.subEnterprName = subEnterprName == null ? null : subEnterprName.trim();
    }

    public String getEnterprIdentityId() {
        return enterprIdentityId;
    }

    public void setEnterprIdentityId(String enterprIdentityId) {
        this.enterprIdentityId = enterprIdentityId == null ? null : enterprIdentityId.trim();
    }

    public String getEnterprRegisterAddress() {
        return enterprRegisterAddress;
    }

    public void setEnterprRegisterAddress(String enterprRegisterAddress) {
        this.enterprRegisterAddress = enterprRegisterAddress == null ? null : enterprRegisterAddress.trim();
    }

    public String getCertifyImageUrl() {
        return certifyImageUrl;
    }

    public void setCertifyImageUrl(String certifyImageUrl) {
        this.certifyImageUrl = certifyImageUrl == null ? null : certifyImageUrl.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getLeaveCredit() {
        return leaveCredit;
    }

    public void setLeaveCredit(Integer leaveCredit) {
        this.leaveCredit = leaveCredit;
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser == null ? null : contactUser.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
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

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber == null ? null : contractNumber.trim();
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
}