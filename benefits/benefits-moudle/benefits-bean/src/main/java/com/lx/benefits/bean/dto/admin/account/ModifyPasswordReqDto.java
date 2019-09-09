package com.lx.benefits.bean.dto.admin.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-06 00:45.
 */
@ApiModel("修改管理员/福粒商城登录密码参数")
public class ModifyPasswordReqDto {
    @ApiModelProperty(value = "老密码")
    private String oldPassword;
    @ApiModelProperty(value = "新密码")
    private String password;
    @ApiModelProperty(value = "重复再输一次密码")
    private String repeatPassword;

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return this.repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "ModifyPasswordReqDto{" +
                "oldPassword='" + oldPassword + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
