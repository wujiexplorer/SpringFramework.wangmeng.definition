package com.lx.benefits.bean.dto.admin.address;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;

/**
 * Created by softw on 2019/1/15.
 */
@ApiModel("代下单基础信息")
public class AddressInfoDto extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String name;//登录名

    @ExcelProperty(index = 1)
    private String username;//收件人

    @ExcelProperty(index = 2)
    private String tel; //收货人手机号

    @ExcelProperty(index = 3)
    private String province;//省份姓名

    @ExcelProperty(index = 4)
    private String city;//城市姓名

    @ExcelProperty(index = 5)
    private String county;//地区姓名

    @ExcelProperty(index = 6)
    private String street;//街道姓名

    @ExcelProperty(index = 7)
    private String info;//具体地址

    @ExcelProperty(index = 8)
    private String identityCard;//身份证

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

    public String getProvince() {
        return province;
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
