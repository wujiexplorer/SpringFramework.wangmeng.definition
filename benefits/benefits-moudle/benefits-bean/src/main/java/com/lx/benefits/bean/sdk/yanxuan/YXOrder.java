package com.lx.benefits.bean.sdk.yanxuan;

import com.lx.benefits.bean.dto.yxOrder.api.YXOrderSku;
import lombok.Data;

import java.util.List;

@Data
public class YXOrder {

    /**
     * 订单号 最大128位 是
     */
    private String orderId;

    /**
     * 下单时间 2016-05-23 09:00:00 是
     */
    private String submitTime;

    /**
     * 支付时间 2016-05-23 13:59:59 是
     */
    private String payTime;

    /**
     * 买家用户名  否
     */
    private String buyerAccount;

    /**
     * 收件人姓名 是
     */
    private String receiverName;

    /**
     * 收件人手机   否
     */
    private String receiverMobile;

    /**
     * 收件人电话   是
     */
    private String receiverPhone;

    /**
     * 收件人省名称   是
     */
    private String receiverProvinceName;

    /**
     * 收件人市名称   是
     */
    private String receiverCityName;

    /**
     * 收件人区名称   是
     */
    private String receiverDistrictName;

    /**
     * 收件人详细地址   是
     */
    private String receiverAddressDetail;

    /**
     * 订单实付金额 是
     */
    private Double realPrice;

    /**
     * 邮费 是
     */
    private Double expFee;

    /**
     * 支付方式 支付宝SDK 是
     */
    private String payMethod;

    /**
     * 发票抬头 否
     */
    private String invoiceTitle;

    /**
     * 发票金额  否
     */
    private String invoiceAmount;

    /**
     * 备注否
     */
    private String remark;

    /**
     * 订单SKU 是
     */
    private List<YXOrderSku> orderSkus;
}
