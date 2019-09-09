package com.lx.benefits.bean.dto.deliverinfo;

import java.util.List;

public class ExpressResult<T> {
	private Boolean isSuccess;// 数据是否获取成功
	private String failMessage;// 失败消息
	private List<T> data;// 数据结果
	private String company;// 物流公司
	private String expressNo;// 快递单号

	public static <T> ExpressResult<T> handleFail(String failMessage) {
		ExpressResult<T> expressResult = new ExpressResult<>();
		expressResult.setIsSuccess(false);
		expressResult.setFailMessage(failMessage);
		return expressResult;
	}

	public static <T> ExpressResult<T> handleFail(String failMessage, String expressNo, String company) {
		ExpressResult<T> expressResult = new ExpressResult<>();
		expressResult.setIsSuccess(false);
		expressResult.setFailMessage(failMessage);
		expressResult.setExpressNo(expressNo);
		expressResult.setCompany(company);
		return expressResult;
	}

	public static <T> ExpressResult<T> handleSuccess(List<T> data) {
		ExpressResult<T> expressResult = new ExpressResult<>();
		expressResult.setIsSuccess(true);
		expressResult.setData(data);
		return expressResult;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

}
