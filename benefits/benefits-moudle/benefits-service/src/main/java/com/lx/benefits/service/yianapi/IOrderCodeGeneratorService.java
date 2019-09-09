package com.lx.benefits.service.yianapi;

import com.lx.benefits.bean.enums.OrderCodeType;

public interface IOrderCodeGeneratorService {

    /**
     * 生成订单编号
     *
     * @param type
     * @return
     */
    Long generate(OrderCodeType type);
}
