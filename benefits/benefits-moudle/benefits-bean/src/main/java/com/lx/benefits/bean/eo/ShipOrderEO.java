package com.lx.benefits.bean.eo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ShipOrderEO extends BaseRowModel {

    @ExcelProperty(index = 0)
    private Long orderNumber;
    @ExcelProperty(index = 1)
    private String logisticsChannel;
    @ExcelProperty(index = 2)
    private String logisticsNumber;
    @ExcelProperty(index = 3)
    private BigDecimal remoteFreight;
}
