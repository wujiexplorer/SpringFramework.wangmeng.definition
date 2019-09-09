package com.lx.benefits.bean.dto.admin.product;

import lombok.Data;

@Data
public class RedueStockReqDto {

    /** sku编码 */
    private String skuCode;

    /** 购买数量 */
    private Integer num;

}
