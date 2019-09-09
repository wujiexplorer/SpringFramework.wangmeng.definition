package com.lx.benefits.bean.vo.account;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.apollo.common.annotation.ExcelAnnotation;
import com.apollo.common.annotation.ExcelStringConfig;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User:wangmeng
 * Date:2019/7/16
 * Time:10:46
 * Version:2.x
 * Description:TODO
 **/
@Data
public class AccountBankInfoExcelModel extends BaseRowModel {
    //@ExcelAnnotation(header = "父级订单", cell = 0)
    @ExcelProperty(index = 0, value = "父级订单")
    private String parentNumberCode;
    private Long parentNumber;
    //@ExcelAnnotation(header = "子级订单", cell = 1)
    @ExcelProperty(index = 1, value = "子级订单")
    private String numberCode;
    private Long number;
    //@ExcelAnnotation(header = "第三方订单号", cell = 2)
    @ExcelProperty(index = 2, value = "第三方订单号")
    private String thirdOrderNumber;
    //@ExcelAnnotation(header = "订单状态", cell = 3)
    @ExcelProperty(index = 3, value = "订单状态")
    private String statusCode;
    private Integer status;
    //@ExcelAnnotation(header = "下单时间", cell = 4)
    @ExcelProperty(index = 4, value = "下单时间")
    private Date createTime;
    @ExcelProperty(index = 5, value = "供应商")
    private String supplierName;
    //@ExcelAnnotation(header = "所属企业", cell = 5)
    //@ExcelAnnotation(header = "商品名称", cell = 6)
    @ExcelProperty(index = 6, value = "商品名称")
    private String goodsName;
   // @ExcelAnnotation(header = "数量", cell = 7)
    @ExcelProperty(index = 7, value = "数量")
    private Integer quantity;
    @ExcelProperty(index = 8, value = "sku")
    private Integer skuCode;
    @ExcelProperty(index = 9, value = "类目")
    private String categoryName;
    //@ExcelAnnotation(header = "供应商", cell = 8)
    @ExcelProperty(index = 10, value = "所属企业")
    private String enterprName;
    @ExcelProperty(index = 11, value = "代理商")
    private String agentName;
    private Integer agentId;
    //@ExcelAnnotation(header = "员工名称", cell = 10)
    @ExcelProperty(index = 12, value = "员工名称")
    private String employeeName;
    //@ExcelAnnotation(header = "销售总额", cell = 11)

    //@ExcelAnnotation(header = "员工工号", cell = 30)
    @ExcelProperty(index = 13, value = "员工工号")
    private String employeeNo;
    @ExcelProperty(index = 14, value = "销售总额")
    private BigDecimal price;
    //@ExcelAnnotation(header = "类目", cell = 9)

    //@ExcelAnnotation(header = "销售单价", cell = 12)
    @ExcelProperty(index = 15, value = "销售单价")
    private BigDecimal goodsPrice;
    //@ExcelAnnotation(header = "成本单价", cell = 13)
    @ExcelProperty(index = 16, value = "成本单价")
    private BigDecimal goodsCostprice;
    //@ExcelAnnotation(header = "成本总价", cell = 14)
    @ExcelProperty(index = 17, value = "成本总价")
    private BigDecimal totalCostPrice;
    //@ExcelAnnotation(header = "sku代码", cell = 15)

    //@ExcelAnnotation(header = "运费", cell = 16)
    @ExcelProperty(index = 18, value = "运费")
    private BigDecimal shipExpense;
    //@ExcelAnnotation(header = "应付金额", cell = 17)
    @ExcelProperty(index = 19, value = "应付金额")
    private BigDecimal payableSum;
    //@ExcelAnnotation(header = "支付级订单号", cell = 18)
    @ExcelProperty(index = 20, value = "支付级订单号")
    private String payNumberCode;
    private Long payNumber;
    //@ExcelAnnotation(header = "积分抵扣金额", cell = 19)
    @ExcelProperty(index = 21, value = "积分抵扣金额")
    private BigDecimal pointAmount;
    //@ExcelAnnotation(header = "会员卡抵扣金额", cell = 20)
    @ExcelProperty(index = 22, value = "会员卡抵扣金额")
    private BigDecimal cardAmount;
    //@ExcelAnnotation(header = "第三方支付金额", cell = 21)
    @ExcelProperty(index = 23, value = "第三方支付金额")
    private BigDecimal thirdCostprice;
    //@ExcelAnnotation(header = "第三方支付方式", cell = 22)
    @ExcelProperty(index = 24, value = "第三方支付方式")
    private String channelCode;
    private Integer channel;
    //@ExcelAnnotation(header = "第三方支付流水号", cell = 23)
    @ExcelProperty(index = 25, value = "第三方支付流水号")
    private String settleNumber;
    //@ExcelAnnotation(header = "退款状态", cell = 24)
    @ExcelProperty(index = 26, value = "退款状态")
    private String reverseStatusCode;
    private Integer reverseStatus;
    //@ExcelAnnotation(header = "退款时间", cell = 25)
    @ExcelProperty(index = 27, value = "退款时间")
    private Date successTime;
    //@ExcelAnnotation(header = "第三方退款", cell = 26)
    //@ExcelAnnotation(header = "积分退款", cell = 27)
    @ExcelProperty(index = 28, value = "积分退款")
    private BigDecimal returnAccountPoint;
    @ExcelProperty(index = 29, value = "会员卡退款")
    private BigDecimal cardRefundAmount;
    @ExcelProperty(index = 30, value = "第三方退款金额")
    private BigDecimal realMoney;
    //@ExcelAnnotation(header = "退款流水号", cell = 28)
    @ExcelProperty(index = 31, value = "退款流水号")
    private String serialNumber;
    @ExcelProperty(index = 32, value = "优惠劵优惠的金额")
    private BigDecimal voucherBenefit;
    //@ExcelAnnotation(header = "代理商", cell = 29)




    public Long getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(Long parentNumber) {
        this.parentNumber = parentNumber;
        if(null != parentNumber){
            this.setParentNumberCode(parentNumber.toString());
        }
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
        if(null != number){
            this.setNumberCode(number.toString());
        }
    }

    public Long getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(Long payNumber) {
        this.payNumber = payNumber;
        if(null != payNumber){
            this.setPayNumberCode(payNumber.toString());
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        if(null != status){
            if(status == 0){
                this.setStatusCode("订单已创建");
            }else if(status == 1){
                this.setStatusCode("订单已取消");
            }else if(status == 2){
                this.setStatusCode("订单已支付");
            }else if(status == 3){
                this.setStatusCode("订单已发货");
            }else if(status == 4){
                this.setStatusCode("订单已收货");
            }else if(status == 5){
                this.setStatusCode("订单已关闭");
            }else if(status == 6){
                this.setStatusCode("订单已完成");
            }
        }
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
        if(null != channel){
            if(channel == 1){
                this.setChannelCode("支付宝");
            }else if(channel == 2){
                this.setChannelCode("微信");
            }
        }
    }

    public Integer getReverseStatus() {
        return reverseStatus;
    }

    public void setReverseStatus(Integer reverseStatus) {
        this.reverseStatus = reverseStatus;
        if(reverseStatus != null){
            if(reverseStatus  == 0){
                this.setReverseStatusCode("未进入退货/款流程");
            }else if(reverseStatus == 1){
                this.setReverseStatusCode("进入退货/款流程");
            }else if(reverseStatus == 2){
                this.setReverseStatusCode("退货/款流程完成（仅退款行为）");
            }else if(reverseStatus == 3){
                this.setReverseStatusCode("退货/款流程完结，未生过成功的售后行为");
            }else if(reverseStatus == 4){
                this.setReverseStatusCode("退货/款流程完结，发生过成功的售后行为");
            }
        }
    }
}
