package com.lx.benefits.bean.dto.jdOrder.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldr on 2017/3/16.
 */
@Data
public class JDOrderSubOrder implements Serializable {

    private static final long serialVersionUID = 3308760913354025045L;

    /**
     * 父单号
     */
    private Long pOrder;

    /**
     * 订单状态  0为取消订单  1为有效
     */
    private Integer orderState;

    /**
     * 京东订单编号
     */
    private Long jdOrderId;

    /**
     * 物流状态 0 是新建  1是妥投   2是拒收
     */
    private Integer state;

    /**
     * 0为未确认下单订单   1为确认下单订单
     */
    private Integer submitState;

    /**
     * 订单价格
     * 商品总金额
     */
    private Double orderPrice;

    /**
     * 运费（合同配置了才返回）
     * 总运费(freight) = 基础运费(baseFreight) + 每个sku的购买数量(num) * 该sku的其它运费（otherFreight）(后期可能会有续重，超大超重之类的运费)
     */
    private Double freight;
    /**
     * 基础运费
     */
    private Double baseFreight;
    /**
     * 其他运费
     */
    private Double orderNakedPrice;

    /**
     * 订单类型   1是父订单   2是子订单
     */
    private Integer type;

    private List<JDOrderItem> sku;

    private Double orderTaxPrice;
}
