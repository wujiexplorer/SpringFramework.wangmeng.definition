package com.lx.benefits.bean.dto.admin.customized;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author unknow on 2018-12-05 00:47.
 */
@Data
public class PriceInfoDto extends BaseRowModel {

    /** skuId */
    @ExcelProperty(index = 0)
    private Long goodsId;

    /** 定制单价 */
    @ExcelProperty(index = 1)
    private BigDecimal goodsPrice;

}
