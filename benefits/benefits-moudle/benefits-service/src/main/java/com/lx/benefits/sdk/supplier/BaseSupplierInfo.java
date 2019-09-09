package com.lx.benefits.sdk.supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User: fan
 * Date: 2019/03/21
 * Time: 19:04
 */
@Component
public class BaseSupplierInfo {

    @Value("${JD.SupplierId}")
    public Long JD_ID;

    @Value("${JD.SupplierName}")
    public String JD_NAME;

    @Value("${YX.SupplierID}")
    public Long YX_ID;

    @Value("${YX.SupplierName}")
    public String YX_NAME;

}
