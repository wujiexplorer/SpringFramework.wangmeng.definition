package com.lx.benefits.bean.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-03 22:10.
 */
@ApiModel("通用登录参数")
@Data
public class LoginReqDto {
    @ApiModelProperty(value = "登录用户名")
    private String loginName;
    @ApiModelProperty(value = "登录密码")
    private String password;
    @ApiModelProperty(value = "小程序code")
    private String code;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "短信验证码")
    private String smsCode;
    @ApiModelProperty(value = "确认密码")
    private String confirmPwd;
    @ApiModelProperty(value = "发送短信类型")
    private Integer sendType;
}