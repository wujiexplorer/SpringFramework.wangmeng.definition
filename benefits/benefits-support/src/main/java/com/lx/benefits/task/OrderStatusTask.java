package com.lx.benefits.task;

import com.apollo.common.enums.base.DatePatternEnum;
import com.apollo.common.utils.base.DateUtil;
import com.lx.benefits.bean.dto.deliverinfo.ExpressResult;
import com.lx.benefits.bean.dto.deliverinfo.PackageLocationInfo;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.config.properties.ThirdPlaformSeller;
import com.lx.benefits.service.deliverinfo.PackageDeliverInfoService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.support.order.OrderSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class OrderStatusTask {

	@Resource
	private OrderService orderService;
	@Resource
	private OrderSupport orderSupport;
	@Resource
	private ThirdPlaformSeller thirdPlaformSeller;
	@Autowired
	private PackageDeliverInfoService packageDeliverInfoService;

	/**
	 * 定时更新已发货订单的状态
	 */
	@Scheduled(cron = "0 */5 * * * ?")
	public void cancelOvertimePaymentOrder() {
		log.info("更新已发货订单的状态，任务开始时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
		// 处理京东已支付的订单
		List<Order> jdPaidOrderList = orderService.listSellerPaidOrderListByLevel(thirdPlaformSeller.getJd().getId(), 3);
		if (!CollectionUtils.isEmpty(jdPaidOrderList)) {
			for (Order item : jdPaidOrderList) {
				if (!StringUtils.isEmpty(item.getThirdOrderNumber())) {
					ExpressResult<PackageLocationInfo> jdPackageDeliverInfo = packageDeliverInfoService.getJDPackageDeliverInfo(null,
							item.getThirdOrderNumber());
					if (jdPackageDeliverInfo.getIsSuccess()) {// 物流信息获取成功
						orderService.modifyOrderShipStatus(item.getNumber(), item.getParentNumber());
					}
				}
			}
			jdPaidOrderList = null;
		}
		List<Order> yxPaidOrderList = orderService.listSellerPaidOrderListByLevel(thirdPlaformSeller.getYx().getId(), 3);
		if (!CollectionUtils.isEmpty(yxPaidOrderList)) {
			for (Order item : yxPaidOrderList) {
				if (!StringUtils.isEmpty(item.getThirdOrderNumber())) {
					ExpressResult<PackageLocationInfo> yxPackageDeliverInfo = packageDeliverInfoService
							.getYXPackageDeliverInfo(item.getThirdOrderNumber());
					if (yxPackageDeliverInfo.getIsSuccess()) {// 物流信息获取成功
						orderService.modifyOrderShipStatus(item.getNumber(),item.getParentNumber());
					}
				}
			}
			yxPaidOrderList = null;
		}
		// 处理严选已支付的订单
		log.info("更新已发货订单的状态，任务完成时间：" + DateUtil.getDate(new Date(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
	}

}
