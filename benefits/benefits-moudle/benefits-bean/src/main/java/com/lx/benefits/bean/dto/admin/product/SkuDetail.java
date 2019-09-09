package com.lx.benefits.bean.dto.admin.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 * User: fan
 * Date: 2019/03/11
 * Time: 23:44
 */
@Data
public class SkuDetail {

    /** skuId */
    private String skuId;
    /** 销售价 */
    private BigDecimal goodsPrice;
    /** 成本价 */
    private BigDecimal goodsCostprice;
    /** 市场价 */
    private BigDecimal goodsMarkprice;
    /** 折扣 */
    private BigDecimal goodsDiscount;

    /** 颜色 */
    private String colour;

    /** 尺寸 */
    private String size;

    /** 库存 */
    private Integer goodsStorge;

    private String goodsSpec;

    private BigDecimal goodsRate;

    private String goodsImage;
}
