package com.lx.benefits.bean.param.order;

import com.lx.benefits.bean.param.base.BasePageParam;
import lombok.Data;

/**
 * 用户订单查询参数
 */
@Data
public class OrderQueryParam extends BasePageParam {
    /**
     * 状态
     */
    private Integer status;
    /**
     * 逆向状态
     */
    private Integer reverseStatus;
    /**
     * 商品类型
     */
    private Integer goodsType;

}
