package com.lx.benefits.bean.dto.admin.advanceSale;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author slong
 */
@Data
public class AdvanceSaleExcelDto extends BaseRowModel {
    /**
     * 员工工号
     */
    @ExcelProperty(value = "员工工号", index = 0)
    private String employeeNo;

    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名", index = 1)
    private String employeeName;

    /**
     * sku
     */
    @ExcelProperty(value = "sku", index = 2)
    private String sku;

    /**
     * 商品数量
     */
    @ExcelProperty(value = "积分剩余", index = 3)
    private String count;

}
