package com.lx.benefits.bean.entity.memaddressimportitem;

public class AddressImportItem {

    private Long detailId;
    /**
     * 导入记录id,自增主键ID
     */
    private Long importId;

    private String username;

    private String name;

    private String tel;

    private String province;

    private String city;

    private String county;

    private String street;

    private String info;

    private String identityCard;


    /**
     * 是否删除 {1:已删除; 0:未删除}
     */
    private Integer isDeleted;

    private Integer status;

    private String importMsg;
    /**
     * 创建时间
     */
    private Integer created;
    /**
     * 更新时间
     */
    private Integer updated;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }



    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImportMsg() {
        return importMsg;
    }

    public void setImportMsg(String importMsg) {
        this.importMsg = importMsg;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}