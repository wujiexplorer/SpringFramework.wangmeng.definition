package com.lx.benefits.bean.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author unknow on 2018-12-10 23:55.
 */
@ApiModel("企业商城Session信息")
@Data
public class SessionShopInfo implements SessionInfo{
    @ApiModelProperty(value = "员工id")
    private Long employeeId;
    @ApiModelProperty(value = "企业id")
    private Long enterprId;
    @ApiModelProperty(value = "员工登录用户名")
    private String loginName;
    @ApiModelProperty(value = "员工工号")
    private String employeeNo;
    @ApiModelProperty(value = "员工姓名")
    private String employeeName;
    /** 登录ip */
    private String ip;
    /** 是否web页面登录进来 */
    private boolean fromWeb =true;
    /** 下单来源平台，0:全终端(未知平台下单),1:IOS下单,2:Android下单,3:H5下单 */
    private int platform;
    private String mobile;
    
    private String wxOpenId;//微信登录的OpenID

}
