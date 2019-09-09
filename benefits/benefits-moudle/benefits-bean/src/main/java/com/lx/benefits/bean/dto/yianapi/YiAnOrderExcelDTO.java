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
public class YiAnOrderExcelDTO extends BaseRowModel implements Serializable{

	private static final long serialVersionUID = -3262519324189215135L;
	
	@ExcelProperty(index = 0)
	private String batch;

	@ExcelProperty(index = 1)
	private String orgCode;

	@ExcelProperty(index = 2)
	private String eeNo;

	@ExcelProperty(index = 3)
	private String eeName;
	
	@ExcelProperty(index = 4)
	private String itemName;

	@ExcelProperty(index = 5)
	private String price;
	
	@ExcelProperty(index = 6)
	private String count;

	@ExcelProperty(index = 7)
	private String total;
	
	@ExcelProperty(index = 8)
	private String receiverName;

	@ExcelProperty(index = 9)
	private String address;
	
	@ExcelProperty(index = 10)
	private String postCode;

	@ExcelProperty(index = 11)
	private String receiverMobile;

	@ExcelProperty(index = 12)
	private String supplierName;

	@ExcelProperty(index = 13)
	private String sku;

	@ExcelProperty(index = 14)
	private String orderCode;

}
