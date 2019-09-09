package com.lx.benefits.bean.vo.card;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberCardInfoExcelModel extends BaseRowModel {

	@ExcelProperty(index = 0, value = "卡号")
	private String cardNumber;
	@ExcelProperty(index = 1, value = "面值")
	private Integer amount;

}
