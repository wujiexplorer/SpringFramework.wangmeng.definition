package com.lx.benefits.bean.entity.memconsigneeaddress;


import java.util.Date;

public class ConsigneeAdress {
    private Long id;
    private Long userid;
    private String name;//收货人姓名
    private String tel;//收货人联系手机号
    private Long provinceid;
    private String province;
    private Long cityid;
    private String city;
    private Long districtid;
    private String district;
    private Long streetid;
    private String street;

    private String info;//具体的地址信息
    private String zipCode;
    private String fullinfo;
    private String isdefault;//是否默认收货地址 0否1是

    private String identityCard;//身份证号
    private String frontimg;//正面
    private String backimg;//反面
    private String iscertificated;//是否已认证 0 否 1是


    private Date createTime;

    private Date updateTime;


    public String getIscertificated() {
        return iscertificated;
    }

    public void setIscertificated(String iscertificated) {
        this.iscertificated = iscertificated;
    }

    public String getFrontimg() {
        return frontimg;
    }

    public void setFrontimg(String frontimg) {
        this.frontimg = frontimg;
    }

    public String getBackimg() {
        return backimg;
    }

    public void setBackimg(String backimg) {
        this.backimg = backimg;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public Long getProvinceid() {
        return provinceid;
    }
    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public Long getCityid() {
        return cityid;
    }
    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Long getDistrictid() {
        return districtid;
    }
    public void setDistrictid(Long districtid) {
        this.districtid = districtid;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public Long getStreetid() {
        return streetid;
    }
    public void setStreetid(Long streetid) {
        this.streetid = streetid;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFullinfo() {
        return fullinfo;
    }
    public void setFullinfo(String fullinfo) {
        this.fullinfo = fullinfo;
    }
    public String getIsdefault() {
        return isdefault;
    }
    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }
    public String getIdentityCard() {
        return identityCard;
    }
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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