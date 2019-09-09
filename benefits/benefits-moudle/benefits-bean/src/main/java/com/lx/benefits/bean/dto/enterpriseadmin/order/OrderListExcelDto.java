package com.lx.benefits.bean.dto.enterpriseadmin.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author slong
 */
@Data
public class OrderListExcelDto extends BaseRowModel {
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
//
//    /**
//     * 专题id
//     */
//    @ExcelProperty(value = "tid", index = 3)
//    private String tid;

    /**
     * 活动id
     */
    @ExcelProperty(value = "活动id", index = 3)
    private String campaignId;

    /**
     * 数量
     */
    @ExcelProperty(value = "数量", index = 4)
    private String count;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额", index = 5)
    private String goodsPrice;

    /**
     * 下单执行结果
     */
    @ExcelProperty(value = "下单执行结果", index = 6)
    private String orderMsg;

}
