package com.lx.benefits.bean.dto.admin.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by softw on 2019/1/15.
 */
@ApiModel("导入收货地址操作参数")
public class AddressImportReqDto {

    @ApiModelProperty(value = "收货文件地址")
    private String addressListFile;

    public void setAddressListFile(String addressListFile) {
        this.addressListFile = addressListFile;
    }

    public String getAddressListFile() {
        return addressListFile;
    }

    @Override
    public String toString() {
        return "AddressImportReqDto{" +
                "addressListFile='" + addressListFile + '\'' +
                '}';
    }


}
