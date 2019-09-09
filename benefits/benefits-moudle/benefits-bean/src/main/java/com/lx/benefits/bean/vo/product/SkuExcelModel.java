package com.lx.benefits.bean.vo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class SkuExcelModel extends BaseRowModel{
	@ExcelProperty(index = 0, value = "一级目录")
	private String categoryName;
	@ExcelProperty(index = 1, value = "二级目录")
	private String categoryName2;
	@ExcelProperty(index = 2, value = "三级目录")
	private String categoryName3;
	@ExcelProperty(index = 3, value = "商品名称")
	private String goodsName;
	@ExcelProperty(index = 4, value = "spu")
	private Long spuCode;
	@ExcelProperty(index = 5, value = "sku")
	private Long id;
	private Integer goodsType;
	@ExcelProperty(index = 6, value = "商品类型")
	private String goodsTypeDesc;
	@ExcelProperty(index = 7, value = "规格")
	private String goodsSpec;
	@ExcelProperty(index = 8, value = "品牌")
	private String brandName;
	@ExcelProperty(index = 9, value = "市场价")
	private BigDecimal goodsMarkprice;
	@ExcelProperty(index = 10, value = "销售价")
	private BigDecimal goodsPrice;
	@ExcelProperty(index = 11, value = "成本价")
	private BigDecimal goodsCostprice;
	@ExcelProperty(index = 12, value = "毛利率")
	private String rate;
	private BigDecimal goodsRate;
	@ExcelProperty(index = 13, value = "库存")
	private Integer goodsStorge;
	private Integer status;
	@ExcelProperty(index = 14, value = "状态")
	private String statusDesc;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryName2() {
		return categoryName2;
	}
	public void setCategoryName2(String categoryName2) {
		this.categoryName2 = categoryName2;
	}
	public String getCategoryName3() {
		return categoryName3;
	}
	public void setCategoryName3(String categoryName3) {
		this.categoryName3 = categoryName3;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getSpuCode() {
		return spuCode;
	}
	public void setSpuCode(Long spuCode) {
		this.spuCode = spuCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public BigDecimal getGoodsMarkprice() {
		return goodsMarkprice;
	}
	public void setGoodsMarkprice(BigDecimal goodsMarkprice) {
		this.goodsMarkprice = goodsMarkprice;
	}
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public BigDecimal getGoodsCostprice() {
		return goodsCostprice;
	}
	public void setGoodsCostprice(BigDecimal goodsCostprice) {
		this.goodsCostprice = goodsCostprice;
	}
	public BigDecimal getGoodsRate() {
		return goodsRate;
	}
	public void setGoodsRate(BigDecimal goodsRate) {
		if(goodsRate != null) {
			this.setRate(String.valueOf(goodsRate.multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP))+"%");
		}
	}
	public Integer getGoodsStorge() {
		return goodsStorge;
	}
	public void setGoodsStorge(Integer goodsStorge) {
		this.goodsStorge = goodsStorge;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
		if(status==1) {
			this.setStatusDesc("正常销售");
		}else if(status==0) {
			this.setStatusDesc("暂停销售");
		}
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
		if(goodsType==1) {
			this.setGoodsTypeDesc("虚拟商品");
		}else if(goodsType==0) {
			this.setGoodsTypeDesc("实物商品");
		}
	}
	public String getGoodsTypeDesc() {
		return goodsTypeDesc;
	}
	public void setGoodsTypeDesc(String goodsTypeDesc) {
		this.goodsTypeDesc = goodsTypeDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	
}
