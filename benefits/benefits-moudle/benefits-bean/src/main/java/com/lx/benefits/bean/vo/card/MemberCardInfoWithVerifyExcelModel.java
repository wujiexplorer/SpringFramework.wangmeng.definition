package com.lx.benefits.bean.vo.card;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberCardInfoWithVerifyExcelModel extends BaseRowModel{

	@ExcelProperty(index = 0, value = "卡号")
	private String cardNumber;
	@ExcelProperty(index = 1, value = "校验码")
	private String verifyNumber;
	@ExcelProperty(index = 2, value = "面值")
	private Integer amount;

}
