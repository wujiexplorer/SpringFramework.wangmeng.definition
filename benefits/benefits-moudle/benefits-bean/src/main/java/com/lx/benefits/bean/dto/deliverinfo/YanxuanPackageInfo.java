package com.lx.benefits.bean.dto.deliverinfo;

import java.util.List;

public class YanxuanPackageInfo {
	private String orderId;
	private String orderStatus;
	private List<YanxuanOrderPackage> orderPackages;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<YanxuanOrderPackage> getOrderPackages() {
		return orderPackages;
	}

	public void setOrderPackages(List<YanxuanOrderPackage> orderPackages) {
		this.orderPackages = orderPackages;
	}



	public static class YanxuanOrderPackage {
		private String packageId;
		private String packageStatus;// WAITING_DELIVERY、START_DELIVERY、WAITING_COMMENT
		private String expressCompany;
		private String expressNo;
		private String expCreateTime;
		private String confirmTime;

		public String getPackageId() {
			return packageId;
		}

		public void setPackageId(String packageId) {
			this.packageId = packageId;
		}

		public String getPackageStatus() {
			return packageStatus;
		}

		public void setPackageStatus(String packageStatus) {
			this.packageStatus = packageStatus;
		}

		public String getExpressCompany() {
			return expressCompany;
		}

		public void setExpressCompany(String expressCompany) {
			this.expressCompany = expressCompany;
		}

		public String getExpressNo() {
			return expressNo;
		}

		public void setExpressNo(String expressNo) {
			this.expressNo = expressNo;
		}

		public String getExpCreateTime() {
			return expCreateTime;
		}

		public void setExpCreateTime(String expCreateTime) {
			this.expCreateTime = expCreateTime;
		}

		public String getConfirmTime() {
			return confirmTime;
		}

		public void setConfirmTime(String confirmTime) {
			this.confirmTime = confirmTime;
		}

	}
}
