package com.lx.benefits.bean.dto.yianapi;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author szy
 *
 */
@Data
public class YiAnOrderDeliveryExcelDTO extends BaseRowModel implements Serializable{

	private static final long serialVersionUID = -3262519324189215135L;

	@ExcelProperty(index = 0)
	private String orderCode;

	@ExcelProperty(index = 1)
	private Integer orderType;

	@ExcelProperty(index = 2)
	private String deliveryCompany;

	@ExcelProperty(index = 3)
	private String deliveryCode;

	@ExcelProperty(index = 4)
	private String virtualCardNo;

	@ExcelProperty(index = 5)
	private String virtualPassword;

	@ExcelProperty(index = 6)
	private String virtualIntro;

}
