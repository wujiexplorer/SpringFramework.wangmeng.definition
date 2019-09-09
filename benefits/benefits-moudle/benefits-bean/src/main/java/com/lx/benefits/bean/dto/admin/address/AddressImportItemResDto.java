package com.lx.benefits.bean.dto.admin.address;

import io.swagger.annotations.ApiModel;

/**
 * @author unknow on 2018-12-23 02:19.
 */
@ApiModel("代下单导入记录信息")
public class AddressImportItemResDto {

    private Long detailId;
    /**
     * 导入记录id,自增主键ID
     */
    private Long importId;

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
    private String created;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getImportMsg() {
        return importMsg;
    }

    public void setImportMsg(String importMsg) {
        this.importMsg = importMsg;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
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

    @Override
    public String toString() {
        return "AddressImportItemResDto{" +
                "importId=" + importId +
                "detailId=" + detailId +
                "name=" + name +
                "tel=" + tel +
                "province=" + province +
                "city=" + city +
                "county=" + county +
                "street=" + street +
                ", info='" + info + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", status=" + status +
                ", importMsg=" + importMsg +
                ", created=" + created +
                ", updated='" + updated +
                '}';
    }

}
