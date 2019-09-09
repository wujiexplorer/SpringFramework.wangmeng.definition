package com.lx.benefits.support.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.deliverinfo.ExpressResult;
import com.lx.benefits.bean.dto.deliverinfo.PackageLocationInfo;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderShipLogistics;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.config.properties.ThirdPlaformSeller;
import com.lx.benefits.mapper.express.entity.ExpressInfo;
import com.lx.benefits.mapper.product.SkuMapper;
import com.lx.benefits.service.deliverinfo.PackageDeliverInfoService;
import com.lx.benefits.service.express.ExpressService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.OrderShipLogisticsService;

@Component
public class OrderDeliveryInfoSupport {

	@Resource
	private ThirdPlaformSeller thirdPlaformSeller;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderShipLogisticsService orderShipLogisticsService;
	@Autowired
	private PackageDeliverInfoService packageDeliverInfoService;
	@Autowired
	private ExpressService expressService;
	@Autowired
	private SkuMapper skuMapper;

	/**
	 * 获取订单的物流信息
	 * 
	 * @param sellerOrderNumber
	 */
	public List<ExpressResult<PackageLocationInfo>> getOrderDeliverInfo(Long orderNumber) {
		Order order = orderService.getOrderByNumber(orderNumber);
		if (order == null) {
			throw new BusinessException("订单信息不正确");
		}
		if (OrderEnum.STATUS.CANCEL.getCode() == order.getStatus() || OrderEnum.STATUS.INIT.getCode() == order.getStatus()) {
			throw new BusinessException("没有物流信息");
		}
		List<ExpressResult<PackageLocationInfo>> expressResult =new ArrayList<>();
		// 第三方订单
		if (order.getSellerId().equals(thirdPlaformSeller.getJd().getId())) {// 京东订单
			SkuEntity skuEntity = skuMapper.selectSkuInfoById(order.getSkuId());
			expressResult.add(packageDeliverInfoService.getJDPackageDeliverInfo(skuEntity == null ? null : skuEntity.getSkuCode(), order.getThirdOrderNumber()));
		} else if (order.getSellerId().equals(thirdPlaformSeller.getYx().getId())) {// 严选订单
			expressResult.add(packageDeliverInfoService.getYXPackageDeliverInfo(order.getThirdOrderNumber()));
		} else {
			List<OrderShipLogistics> listSelllerOrderLogistics = orderShipLogisticsService.listItemOrderLogistics(orderNumber);
			if (CollectionUtils.isEmpty(listSelllerOrderLogistics)) {
				throw new BusinessException("暂时没有获取到物流信息");
			}
			for (int i = 0; i < listSelllerOrderLogistics.size(); i++) {
				OrderShipLogistics orderShipLogistics = listSelllerOrderLogistics.get(i);
				ExpressInfo expressInfo = expressService.getExpressInfoByName(orderShipLogistics.getShipExpress());// 物流公司
				if (expressInfo != null) {
					String shipExpressNumber = orderShipLogistics.getShipExpressNumber();// 快递单号
					ExpressResult<PackageLocationInfo> packageDeliverInfo = packageDeliverInfoService
							.getPackageDeliverInfo(expressInfo.getCode(), shipExpressNumber);
					if (packageDeliverInfo.getIsSuccess()) {
						packageDeliverInfo.setCompany(orderShipLogistics.getShipExpress());
						packageDeliverInfo.setExpressNo(shipExpressNumber);
						expressResult.add(packageDeliverInfo);
						//return expressResult;
					} else {
						expressResult.add(ExpressResult.handleFail(packageDeliverInfo.getFailMessage()));
						ExpressResult<PackageLocationInfo> expressResultinfo = expressResult.get(i);
						expressResultinfo.setCompany(orderShipLogistics.getShipExpress());
						expressResultinfo.setExpressNo(shipExpressNumber);
						//return expressResult;
					}
				} else {
					expressResult.add(ExpressResult.handleFail("未查到物流公司信息"));
					ExpressResult<PackageLocationInfo> expressResultinfo = expressResult.get(i);
					expressResultinfo.setCompany(orderShipLogistics.getShipExpress());
					expressResultinfo.setExpressNo(orderShipLogistics.getShipExpressNumber());
					//return expressResult;
				}
			}
			
		}
		return expressResult;
	}

	/**
	 * 
	 * 此接口请勿随意更改，提供给第三方用户查询订单信息
	 * 
	 * 获取订单中所有物流信息
	 * 
	 * @param productOrderNumber
	 *            商品级订单号
	 */
	public List<ExpressResult<PackageLocationInfo>> getClientOrderDeliverInfo(Long orderNumber) {
		Order order = orderService.getOrderByNumber(orderNumber);
		if (order == null) {
			throw new BusinessException("订单信息不正确");
		}
		if (OrderEnum.STATUS.CANCEL.getCode() == order.getStatus() || OrderEnum.STATUS.INIT.getCode() == order.getStatus()) {
			throw new BusinessException("没有物流信息");
		}
		List<ExpressResult<PackageLocationInfo>> expressResults;
		// 第三方订单
		if (order.getSellerId().equals(thirdPlaformSeller.getJd().getId())) {// 京东订单
			SkuEntity skuEntity = skuMapper.selectSkuInfoById(order.getSkuId());
			expressResults = Collections.singletonList(
					packageDeliverInfoService.getJDPackageDeliverInfo(skuEntity == null ? null : skuEntity.getSkuCode(), order.getThirdOrderNumber()));
		} else if (order.getSellerId().equals(thirdPlaformSeller.getYx().getId())) {// 严选订单
			expressResults = Collections.singletonList(packageDeliverInfoService.getYXPackageDeliverInfo(order.getThirdOrderNumber()));
		} else {
			List<OrderShipLogistics> listSelllerOrderLogistics = orderShipLogisticsService.listItemOrderLogistics(orderNumber);
			if (CollectionUtils.isEmpty(listSelllerOrderLogistics)) {
				throw new BusinessException("暂时没有获取到物流信息");
			}
			expressResults = listSelllerOrderLogistics.parallelStream().map(orderShipLogistics -> {
				ExpressResult<PackageLocationInfo> expressResult;
				ExpressInfo expressInfo = expressService.getExpressInfoByName(orderShipLogistics.getShipExpress());// 物流公司
				if (expressInfo != null) {
					String shipExpressNumber = orderShipLogistics.getShipExpressNumber();// 快递单号
					ExpressResult<PackageLocationInfo> packageDeliverInfo = packageDeliverInfoService.getPackageDeliverInfo(expressInfo.getCode(),
							shipExpressNumber);
					if (packageDeliverInfo.getIsSuccess()) {
						packageDeliverInfo.setCompany(orderShipLogistics.getShipExpress());
						packageDeliverInfo.setExpressNo(shipExpressNumber);
						return packageDeliverInfo;
					} else {
						expressResult = ExpressResult.handleFail(packageDeliverInfo.getFailMessage());
						expressResult.setCompany(orderShipLogistics.getShipExpress());
						expressResult.setExpressNo(shipExpressNumber);
						return expressResult;
					}
				} else {
					expressResult = ExpressResult.handleFail("未查到物流公司信息");
					expressResult.setCompany(orderShipLogistics.getShipExpress());
					expressResult.setExpressNo(orderShipLogistics.getShipExpressNumber());
					return expressResult;
				}
			}).collect(Collectors.toList());
		}
		return expressResults;
	}

}
