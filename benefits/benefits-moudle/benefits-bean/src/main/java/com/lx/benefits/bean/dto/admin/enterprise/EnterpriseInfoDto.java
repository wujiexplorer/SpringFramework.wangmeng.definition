package com.lx.benefits.bean.dto.admin.enterprise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 01:22.
 */
@ApiModel("企业客户信息")
public class EnterpriseInfoDto {
    @ApiModelProperty(value = "企业id")
    private Long enterprId;
    @ApiModelProperty(value = "企业登录用户名")
    private String loginName;
    @ApiModelProperty(value = "企业登录密码")
    private String password;
    /**
     * 企业登录secret码
     */
    private String secret;
    @ApiModelProperty(value = "企业名称")
    private String enterprName;
    @ApiModelProperty(value = "企业员工总数")
    private Integer employeeNum;
    @ApiModelProperty(value = "企业所在地国家")
    private String country;
    @ApiModelProperty(value = "企业所在地省份")
    private String province;
    @ApiModelProperty(value = "企业所在地城市")
    private String city;
    @ApiModelProperty(value = "企业所在地区县")
    private String county;
    @ApiModelProperty(value = "企业所在地街道")
    private String street;
    @ApiModelProperty(value = "企业所在地详细地址【不含省市区街道部分】")
    private String address;

    @ApiModelProperty(value = "DB负责人")
    private String contactDB;
    @ApiModelProperty(value = "员工离职积分是否回收{Y, N}")
    private String leaveCreditRecovery;
    @ApiModelProperty(value = "企业联系人")
    private String contactUser;
    @ApiModelProperty(value = "企业联系人手机号")
    private String mobile;
    @ApiModelProperty(value = "企业联系人Email")
    private String email;

    @ApiModelProperty(value = "企业开票信息-名称")
    private String entInvoiceName;
    @ApiModelProperty(value = "企业开票信息-税号")
    private String entInvoiceNo;
    @ApiModelProperty(value = "企业开票信息-地址")
    private String entInvoiceAddress;
    @ApiModelProperty(value = "企业开票信息-电话")
    private String entInvoicePhone;
    @ApiModelProperty(value = "企业开票信息-开户行")
    private String entInvoiceBankName;
    @ApiModelProperty(value = "企业开票信息-银行卡号")
    private String entInvoiceBankCardNo;

    @ApiModelProperty(value = "企业是否已删除{Y:是; N:否}")
    private String isDeleted;
    
    @ApiModelProperty(value = "添加企业客户时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String created;
    @ApiModelProperty(value = "修改企业客户时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String updated;

    public Long getEnterprId() {
        return this.enterprId;
    }

    public void setEnterprId(Long enterprId) {
        this.enterprId = enterprId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEnterprName() {
        return this.enterprName;
    }

    public void setEnterprName(String enterprName) {
        this.enterprName = enterprName;
    }

    public Integer getEmployeeNum() {
        return this.employeeNum;
    }

    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return this.county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLeaveCreditRecovery() {
        return this.leaveCreditRecovery;
    }

    public void setLeaveCreditRecovery(String leaveCreditRecovery) {
        this.leaveCreditRecovery = leaveCreditRecovery;
    }

    public String getContactUser() {
        return this.contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEntInvoiceName() {
        return this.entInvoiceName;
    }

    public void setEntInvoiceName(String entInvoiceName) {
        this.entInvoiceName = entInvoiceName;
    }

    public String getEntInvoiceNo() {
        return this.entInvoiceNo;
    }

    public void setEntInvoiceNo(String entInvoiceNo) {
        this.entInvoiceNo = entInvoiceNo;
    }

    public String getEntInvoiceAddress() {
        return this.entInvoiceAddress;
    }

    public void setEntInvoiceAddress(String entInvoiceAddress) {
        this.entInvoiceAddress = entInvoiceAddress;
    }

    public String getEntInvoicePhone() {
        return this.entInvoicePhone;
    }

    public void setEntInvoicePhone(String entInvoicePhone) {
        this.entInvoicePhone = entInvoicePhone;
    }

    public String getEntInvoiceBankName() {
        return this.entInvoiceBankName;
    }

    public void setEntInvoiceBankName(String entInvoiceBankName) {
        this.entInvoiceBankName = entInvoiceBankName;
    }

    public String getEntInvoiceBankCardNo() {
        return this.entInvoiceBankCardNo;
    }

    public void setEntInvoiceBankCardNo(String entInvoiceBankCardNo) {
        this.entInvoiceBankCardNo = entInvoiceBankCardNo;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "EnterpriseInfoDto{" +
                "enterprId=" + enterprId +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", secret='" + secret + '\'' +
                ", enterprName='" + enterprName + '\'' +
                ", employeeNum=" + employeeNum +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", street='" + street + '\'' +
                ", address='" + address + '\'' +
                ", leaveCreditRecovery='" + leaveCreditRecovery + '\'' +
                ", contactUser='" + contactUser + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", entInvoiceName='" + entInvoiceName + '\'' +
                ", entInvoiceNo='" + entInvoiceNo + '\'' +
                ", entInvoiceAddress='" + entInvoiceAddress + '\'' +
                ", entInvoicePhone='" + entInvoicePhone + '\'' +
                ", entInvoiceBankName='" + entInvoiceBankName + '\'' +
                ", entInvoiceBankCardNo='" + entInvoiceBankCardNo + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", contactDB='" + contactDB + '\'' +
                '}';
    }

    public String getContactDB() {
        return contactDB;
    }

    public void setContactDB(String contactDB) {
        this.contactDB = contactDB;
    }
}