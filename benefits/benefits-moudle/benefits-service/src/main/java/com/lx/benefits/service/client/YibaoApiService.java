package com.lx.benefits.service.client;

import java.math.BigDecimal;

import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;

/**
 * 易豹接口API
 *
 */
public interface YibaoApiService {

	/**
	 * 获取用户信息
	 * 
	 * @param token
	 *            第三方用户token信息
	 * @return
	 */
	EmployeeUserInfo getUserInfo(String token);

	/**
	 * 获取可用积分
	 * 
	 * @param clientUserId
	 *            第三方用户ID,对应于福粒中的employeeNo
	 * @return
	 */
	BigDecimal getAvailablePoints(String clientUserId);

	/**
	 * 预扣减积分
	 * 
	 * @param clientUserId
	 *            第三方用户ID,对应于福粒中的employeeNo
	 * @param preReducePoints
	 *            预扣减积分数
	 * @param orderPayNumber
	 *            支付级订单号
	 * @return
	 */
	boolean preReducePoints(String clientUserId, BigDecimal preReducePoints, String orderPayNumber);

	/**
	 * 订单信息发送
	 * 
	 * @param clientUserId
	 *            第三方用户ID,对应于福粒中的employeeNo
	 * 
	 * @param payOrderNumber
	 *            支付级订单号
	 * @param isSuccess
	 *            是否下单成功
	 */
	void sendOrderMessage(String clientUserId, Long payOrderNumber, boolean isSuccess);

	/**
	 * 该企业是否属于易豹
	 * 
	 * @param enterprId
	 *            福粒企业ID
	 * @return
	 */
	boolean isBelongTo(Long enterprId);

	String getOrgCode();

	/**
	 * 处理失败信息
	 */
	void handleFailedMessage();
}
