package com.lx.benefits.bean.enums.card;

/**
 * 会员卡订单状态
 * 
 * <pre>
 * 订单状态:
 * 1、审核中：销售单已提交
 * 2、审核失败：被财务复核拒接
 * 3、等待发卡：复核通过 
 * 4、销售单已经完成
 * </pre>
 *
 */
public enum CardOrderStatus {

	INIT(0), // 审核中（销售单已提交）
	VERIFY_UNPASSED(1), // 审核失败（被财务复核拒接）
	VERIFY_PASSED(2), // 审核通过（等待发卡）
	COMPLETE(3),// 销售单已经完成（已发卡）
	;

	public Integer status;

	private CardOrderStatus(Integer status) {
		this.status = status;
	}
}
