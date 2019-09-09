package com.lx.benefits.bean.dto.admin.supplierinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/02/24
 * Time: 00:54
 */
@Data
@ApiModel("供应商基础信息")
public class SupplierInfoDto {

    @ApiModelProperty(value = "供应商ID")
    private Long id;

    @ApiModelProperty(value = "供应商编号")
    private String code;

    @ApiModelProperty(value = "供应商名称")
    private String name;

    @ApiModelProperty(value = "供应商英文名")
    private String alias;

    @ApiModelProperty(value = "开户银行")
    private String englishName;

    @ApiModelProperty(value = "银行账号")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "银行开户人姓名")
    private String bankAccName;

    @ApiModelProperty(value = "税点")
    private String taxPoint;

    @ApiModelProperty(value = "发票类型 1:BT/2:VAT")
    private Integer invoiceType;

    @ApiModelProperty(value = "币种 1:人民币/2:其它")
    private Integer currency;

    @ApiModelProperty(value = "结算方式 1:佣金/2:全额返佣金/3:客户直接付款")
    private Integer settlementType;

    @ApiModelProperty(value = "结算周期 1:季/2:月/3:每月15/4:半月")
    private Integer settlementCycle;

    @ApiModelProperty(value = "返佣")
    private String returnCommission;

    @ApiModelProperty(value = "折扣")
    private String discount;

    @ApiModelProperty(value = "账务编号")
    private String accountingCode;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String linkman;

    @ApiModelProperty(value = "电话号")
    private String tel;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "主页")
    private String home;

    @ApiModelProperty(value = "用户名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

}
