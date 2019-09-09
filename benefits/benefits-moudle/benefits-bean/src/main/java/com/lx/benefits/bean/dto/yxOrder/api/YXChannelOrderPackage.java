package com.lx.benefits.bean.dto.yxOrder.api;

import java.io.Serializable;
import java.util.List;

/**
 * 订单包裹
 * Created by ldr on 2017/6/7.
 */
public class YXChannelOrderPackage implements Serializable {

    private static final long serialVersionUID = 1909408480865084537L;
    /**
     * 包裹号
     */
    private String packageId;

    /**
     * 物流公司名称
     */
    private String expressCompany;

    /**
     * 运单号 绑单之后才有， 不同物流公司的运单号使用英文竖线|隔开，相同物流公司的运单号使用,隔开如 sf123,sf456|yt123,yt456
     */
    private String expressNo;

    /**
     * 发货时间 绑单之后才有，2016-05-23 13:59:59
     */
    private String expCreateTime;

    /**
     * 签收时间 绑单之后才有，2016-05-24 13:59:59
     */
    private String confirmTime;

    /**
     * 包裹状态 绑单之后才有
     * WAITING_DELIVERY 等待发货
     * START_DELIVERY 已发货(等待收货)
     * WAITING_COMMENT 已收货(等待评论)
     * COMMENTED 已评价
     * SYS_CANCEL 系统取消
     * USER_CANCEL 用户取消
     * KF_CANCEL 客服取消
     * USERPAYEDCANCEL 用户付款后取消
     */
    private String packageStatus;

    /**
     * 订单SKU
     */
    private List<YXOrderSku> orderSkus;

    /**
     * 物流详细信息 绑单之后才有
     */
    private List<YXChannelOrderExpressDetailInfo> expressDetailInfos;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpCreateTime() {
        return expCreateTime;
    }

    public void setExpCreateTime(String expCreateTime) {
        this.expCreateTime = expCreateTime;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public List<YXOrderSku> getOrderSkus() {
        return orderSkus;
    }

    public void setOrderSkus(List<YXOrderSku> orderSkus) {
        this.orderSkus = orderSkus;
    }

    public List<YXChannelOrderExpressDetailInfo> getExpressDetailInfos() {
        return expressDetailInfos;
    }

    public void setExpressDetailInfos(List<YXChannelOrderExpressDetailInfo> expressDetailInfos) {
        this.expressDetailInfos = expressDetailInfos;
    }
}
