package com.lx.benefits.bean.vo.cardkey;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Data;
@Data
public class CardKeyExcel extends BaseRowModel{
	@ExcelProperty(index = 0, value = "卡号")
	private String cardNum;
	@ExcelProperty(index = 1, value = "密码")
	private String password;
	@ExcelProperty(index = 2, value = "截止日期" ,format = "yyyy-MM-dd")
	private Date deadTime;
	@ExcelProperty(index = 3, value = "成本价")
	private BigDecimal costPrice;
}
