package com.lx.benefits.bean.dto.yxOrder.api;

import java.io.Serializable;
import java.util.List;

/**
 * 渠道订单回调的包裹详情信息
 * Created by ldr on 2017/6/7.
 */
public class YXChannelOrderExpressDetailInfo implements Serializable {

    private static final long serialVersionUID = 2098316597788766104L;
    /**
     * 物流公司名称
     * EMS     顺丰    韵达   中通   百世汇通  圆通  申通  天天   全峰  宅急送
     * 国通  德邦  速尔  能达  优速  快捷  新邦物流  晟邦物流
     */
    private String expressCompany;

    /**
     * 物流单号
     */
    private String expressNo;

    /**
     * 子物流单列表
     */
    private List<String> subExpressNos;

    /**
     * 物流包裹包含SKU信息
     */
    private List<YXOrderSku> skus;


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

    public List<String> getSubExpressNos() {
        return subExpressNos;
    }

    public void setSubExpressNos(List<String> subExpressNos) {
        this.subExpressNos = subExpressNos;
    }

    public List<YXOrderSku> getSkus() {
        return skus;
    }

    public void setSkus(List<YXOrderSku> skus) {
        this.skus = skus;
    }
}
