package com.lx.benefits.bean.param.refund;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 提交订单参数
 */
@Data
public class RefundApplyParam {

    /**
     * 商品级订单编号
     */
    @NotNull(message = "订单编号不能为空")
    private Long itemOrderNumber;
    /**
     * 退款原因ID
     */
    @NotNull(message = "请选择退款原因")
    private Integer refundApplyProblemId;
    /**
     * 退款其他原因
     */
    private String reason;
    /**
     * 退款图片
     */
    private List<String> picList;
}
