package com.lx.benefits.bean.entity.enterpruserinfo;

import java.io.Serializable;
import java.util.Date;

public class EnterprUserInfo implements Serializable {
    private Long enterprId;

    private String loginName;

    private String password;

    private String secret;

    private String enterprName;

    private Integer accountStatus;

    private Integer employeeNum;

    private String country;

    private String province;

    private String city;

    private String county;

    private String street;

    private String address;

    private String enterprIdentityId;

    private String enterprRegisterAddress;

    private String certifyImageUrl;

    private Integer leaveCredit;

    private String contactUser;

    private String mobile;

    private String email;

    private Integer agentId;

    private Long grainId;

    private Date bindTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public String getEnterprName() {
        return enterprName;
    }

    public void setEnterprName(String enterprName) {
        this.enterprName = enterprName == null ? null : enterprName.trim();
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
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

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
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

    public Long getGrainId() {
        return grainId;
    }

    public void setGrainId(Long grainId) {
        this.grainId = grainId;
    }
}