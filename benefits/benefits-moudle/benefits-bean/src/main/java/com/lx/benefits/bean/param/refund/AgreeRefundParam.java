package com.lx.benefits.bean.param.refund;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AgreeRefundParam {

    /**
     * 状态 目前只允许传 0: 拒绝,1：同意
     * 详见 {@link com.lx.benefits.bean.enums.RefundEnum.STATUS]
     */
    @NotNull(message = "参数错误")
    private Integer status;
}
