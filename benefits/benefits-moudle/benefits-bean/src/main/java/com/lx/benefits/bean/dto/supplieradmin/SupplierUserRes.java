package com.lx.benefits.bean.dto.supplieradmin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author by yingcai on 2018/12/3.
 */
@ApiModel("供应商返回类")
public class SupplierUserRes {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "supplier_id")
    private Long supplier_id;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String login_name;

    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "名字")
    private String user_name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Long supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
