package com.lx.benefits.service.jdOrder.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.bo.product.SkuBO;
import com.lx.benefits.bean.dto.jd.JDItemCheck;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.dto.jd.res.JDResult;
import com.lx.benefits.bean.dto.jd.utils.AssertUtil;
import com.lx.benefits.bean.dto.jdOrder.api.JDOrderSubOrder;
import com.lx.benefits.bean.dto.jdOrder.api.JDOrderTrack;
import com.lx.benefits.bean.dto.order.QueryFreightVO;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoEntity;
import com.lx.benefits.bean.entity.memconsigneeaddress.ConsigneeAdress;
import com.lx.benefits.bean.sdk.jd.JDFreight;
import com.lx.benefits.bean.sdk.jd.JDOrderSubmit;
import com.lx.benefits.bean.sdk.jd.JDOrderSubmitSKU;
import com.lx.benefits.bean.util.BeanUtil;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.vo.order.JDAddress;
import com.lx.benefits.dao.memconsigneeaddress.ConsignessAdressDao;
import com.lx.benefits.service.basedistrictinfo.DistricitInfoService;
import com.lx.benefits.service.jd.IJDAccessTokenService;
import com.lx.benefits.service.jd.IJDItemService;
import com.lx.benefits.service.jd.impl.JDBaseService;
import com.lx.benefits.service.jdOrder.IJDOrderService;
import com.lx.benefits.service.order.impl.OrderInfoValidateServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: fan Date: 2019/03/03 Time: 21:04
 */
@Slf4j
@Service
public class IJDOrderServiceImpl extends JDBaseService implements IJDOrderService {

	@Resource
	private DistricitInfoService districtInfoService;
	@Resource
	private IJDItemService ijdItemService;
	@Autowired
	private ConsignessAdressDao adressDao;
	@Resource
	private IJDAccessTokenService jDAccessTokenServiceImpl;
	@Resource
	private OrderInfoValidateServiceImpl orderInfoValidateServiceImpl;

	@Override
	public JDOrderSubOrder submitOrder(Long sellerOrderNumber, List<SkuBO> jdSkus, ConsigneeAdress userReceiveAddress) {
		String res;
		JDOrderSubmit jdOrderSubmit = null;
		try {
			jdOrderSubmit = getJdOrderSubmit(sellerOrderNumber, jdSkus, userReceiveAddress);
			Map<String, String> param = BeanUtil.beanMapString(jdOrderSubmit);
			param.put("token", getToken());
//            res = HttpClientUtil.postData(getOrder_submit_url(), param, CHARSET);
			res = postData(getOrder_submit_url(), param, CHARSET);

			log.info("JD_ORDER_ORDER_SUBMIT_RESULT:" + res);
			log.info("JD_ORDER_ORDER_SUBMIT_PARAM:" + JsonUtil.toString(jdOrderSubmit));
		} catch (Exception e) {
			log.error("JD_ORDER_SUBMIT_ORDER_ERROR", e);
			log.error("JD_ORDER_SUBMIT_ORDER_ERROR_PARAM", JsonUtil.toString(jdOrderSubmit));
			throw new BusinessException("订单提交失败" + e.getMessage());
		}

		JDResult<JDOrderSubOrder> jdOrderSubOrderJDResult = JsonUtil.parseObject(res,
				new TypeReference<JDResult<JDOrderSubOrder>>() {
				});
		if (!jdOrderSubOrderJDResult.isSuccess()) {
			if ("3554".equals(jdOrderSubOrderJDResult.getResultCode())) {
				throw new BusinessException("订单提交失败:用户选择的乡镇不存在!");

			}
			if (jdOrderSubOrderJDResult.getResultCode() != null && !jdOrderSubOrderJDResult.getResultCode().isEmpty()) {
				if (jdOrderSubOrderJDResult.getResultCode().matches("^3+([0-9]+)$")) {
					String msg = jdOrderSubOrderJDResult.getResultMessage();
					throw new BusinessException(msg);

				}
			}
			log.error("JD_ORDER_ORDER_SUBMIT_ERROR_RESULT" + res);
			log.error("JD_ORDER_ORDER_SUBMIT_ERROR_PARAM" + JsonUtil.convertObjToStr(jdOrderSubmit));
			throw new BusinessException("订单提交失败:" + jdOrderSubOrderJDResult.getResultMessage());
		}
		JDOrderSubOrder subOrder = jdOrderSubOrderJDResult.getResult();
		if (CollectionUtils.isEmpty(subOrder.getSku())) {
			log.error("JD_ORDER_ORDER_SUBMIT_ORDER_ITEM_RETURN_IS_EMPTY_RESULT:" + res);
			throw new BusinessException("商品不存在");
		}
		return subOrder;
	}

	private JDOrderSubmit getJdOrderSubmit(Long sellerOrderNumber, List<SkuBO> jdSkus,
			ConsigneeAdress userReceiveAddress) {
		JDOrderSubmit jdOrderSubmit = new JDOrderSubmit();
		jdOrderSubmit.setThirdOrder(sellerOrderNumber.toString());
		List<JDOrderSubmitSKU> jdOrderSKUs = Lists.newArrayList();
		List<String> skus = new ArrayList<>();
		for (SkuBO skuBO : jdSkus) {
			JDOrderSubmitSKU jdOrderSubmitSKU = new JDOrderSubmitSKU();
			jdOrderSubmitSKU.setSkuId(Long.parseLong(skuBO.getSkuCode()));
			jdOrderSubmitSKU.setNum(skuBO.getQuantity());
			jdOrderSubmitSKU.setBNeedGift(false);
			jdOrderSubmitSKU.setBNeedAnnex(true);
			jdOrderSKUs.add(jdOrderSubmitSKU);
			skus.add(skuBO.getSkuCode());
		}
		jdOrderSubmit.setSku(JsonUtil.toString(jdOrderSKUs));

//        List<JDPrice> jdPrices = jdPriceService.getPriceBySkus(getString(skus));
//        List<JdOrderSubmitPriceSnap> jdOrderSubmitPriceSnaps = new ArrayList<>();
//        for (JDPrice price : jdPrices) {
//            jdOrderSubmitPriceSnaps.add(new JdOrderSubmitPriceSnap(Long.parseLong(price.getSku()), price.getJdPrice()));
//        }
//        jdOrderSubmit.setOrderPriceSnap(JsonUtil.convertObjToStr(jdOrderSubmitPriceSnaps));
//        jdOrderSubmit.setDoOrderPriceMode(1);

		JDAddress jdAddress = districtInfoService.getJdAddress(userReceiveAddress);
		jdOrderSubmit.setName(userReceiveAddress.getName());
		jdOrderSubmit.setProvince(jdAddress.getProvinceId());
		jdOrderSubmit.setCity(jdAddress.getCityId());
		jdOrderSubmit.setCounty(jdAddress.getCountyId());
		jdOrderSubmit.setTown(jdAddress.getTownId());
		jdOrderSubmit.setAddress(userReceiveAddress.getInfo());
		jdOrderSubmit.setMobile(userReceiveAddress.getTel());
		jdOrderSubmit.setEmail("mobile@seagoor.com");
//        jdOrderSubmit.setEmail(StringUtils.isBlank(userReceiveAddress.get()) ? "mobile@seagoor.com" : userReceiveAddress.getEmail());

		boolean isCanVAT = isCanVAT(skus);
		jdOrderSubmit.setInvoiceType(isCanVAT ? 2 : 3);

		jdOrderSubmit.setInvoiceState(2);
		jdOrderSubmit.setSelectedInvoiceTitle(5);
		jdOrderSubmit.setInvoiceContent(1);
		jdOrderSubmit.setCompanyName("杭州福粒科技有限公司");

		jdOrderSubmit.setPaymentType(4);
		jdOrderSubmit.setIsUseBalance(1);
		jdOrderSubmit.setSubmitState(0);
		return jdOrderSubmit;
	}

	@Override
	public Boolean confirmOrder(String jdOrderId) throws BusinessException {

		AssertUtil.notBlank(jdOrderId, "JD_ORDER_ID_IS_BLANK");
		Map<String, String> param = getParam();
		param.put("jdOrderId", jdOrderId);
//        String res = HttpClientUtil.postData(getOrder_confirm_url(), param, CHARSET);
		String res = postData(getOrder_confirm_url(), param, CHARSET);
		log.info("JD_ORDER_CONFIRM_RESULT_" + res);
		JDResult<Boolean> jdResult = JsonUtil.parseObject(res, new TypeReference<JDResult<Boolean>>() {
		});
		if (!jdResult.isSuccess()) {
			log.error("JD_ORDER_CONFIRM_ERROR_RESULT_" + res + " PARAM=" + jdOrderId);
			throw new BusinessException(jdResult.getResultCode(), jdResult.getResultMessage());
		}
		// 错误码3103，代表该订单已确认下单，不需要重复确认
		if (!jdResult.getResult()) {
			if (StringUtils.equals(jdResult.getResultCode(), "3103")) {

				log.warn("JD_ORDER_CONFIRM_REPEAT_RESULT_" + res + " PARAM=" + jdOrderId);
				return true;
			} else {
				log.error("JD_ORDER_CONFIRM_ORDER_ERROR,RESULT_" + res + " PARAM=" + jdOrderId);
				throw new BusinessException(jdResult.getResultCode(), jdResult.getResultMessage());
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean cancel(String jdOrderId) {
		try {
			Map<String, String> param = getParam();
			param.put("jdOrderId", jdOrderId);
			String res = postData(getOrder_cancel_url(), param, CHARSET);

			JDResult<Boolean> jdResult = JSON.parseObject(res, new TypeReference<JDResult<Boolean>>() {
			});
			if (!jdResult.isSuccess()) {
				if (StringUtils.equals(jdResult.getResultCode(), "3203")) {
					return true;
				}
				return false;
			}
			return jdResult.getResult();
		} catch (Exception e) {
			throw new ServiceException("取消订单失败,请稍后重试");
		}
	}

	@Override
	public JDOrderTrack orderTrack(String jdOrderId) {
		try {
			AssertUtil.notBlank(jdOrderId, "JD_ORDER_ID_IS_BLANK");
			Map<String, String> param = getParam();
			param.put("jdOrderId", jdOrderId);
			String res = postData(getOrder_track_url(), param, CHARSET);

			JDResult<JDOrderTrack> jdResult = JSON.parseObject(res, new TypeReference<JDResult<JDOrderTrack>>() {
			});
			if (!jdResult.isSuccess()) {
				return null;
			}
			if (jdResult.getResult() == null || jdResult.getResult().getOrderTrack() == null) {
				return null;
			}
			return jdResult.getResult();
		} catch (Exception e) {
			return null;
		}
	}

	private boolean isCanVAT(List<String> skus) {
		List<List<String>> skuList = Lists.partition(skus, 100);
		for (List<String> list : skuList) {
			List<JDItemCheck> checks = ijdItemService.check(list);
			boolean containNOVAT = checks.stream().anyMatch(s -> s.getIsCanVAT() != null && s.getIsCanVAT() == 0);
			if (containNOVAT)
				return false;
		}
		return true;
	}

	@Override
	public Double freight(List<SkuBO> jdSkus, Long addressId) {
		if (addressId == null)
			return 0d;
		try {
			ConsigneeAdress orderConsignee = adressDao.getAddressById(addressId);
			DistrictInfoEntity province = districtInfoService.getById(orderConsignee.getProvinceid().intValue());
			DistrictInfoEntity city = districtInfoService.getById(orderConsignee.getCityid().intValue());
			DistrictInfoEntity county = districtInfoService.getById(orderConsignee.getDistrictid().intValue());
			DistrictInfoEntity town = orderConsignee.getStreetid() == null ? null
					: districtInfoService.getById(orderConsignee.getStreetid().intValue());

			List<JDOrderSubmitSKU> jdOrderSKUs = Lists.newArrayList();
			for (SkuBO skuBO : jdSkus) {
				JDOrderSubmitSKU jdOrderSubmitSKU = new JDOrderSubmitSKU();
				jdOrderSubmitSKU.setSkuId(Long.parseLong(skuBO.getSkuCode()));
				jdOrderSubmitSKU.setNum(skuBO.getQuantity());
				jdOrderSubmitSKU.setBNeedGift(false);
				jdOrderSubmitSKU.setBNeedAnnex(true);
				jdOrderSKUs.add(jdOrderSubmitSKU);
			}

			Map<String, String> param = getParam();
			param.put("sku", JsonUtil.convertObjToStr(jdOrderSKUs));
			param.put("province", String.valueOf(province.getJdAreaId()));
			param.put("city", String.valueOf(city.getJdAreaId()));
			param.put("county", String.valueOf(county.getJdAreaId()));
			param.put("town", String.valueOf(town == null ? 0L : town.getJdAreaId()));
			param.put("paymentType", "4");
			String res = postData(getOrder_freight_url(), param, CHARSET);
			log.info("JD_FREIGHT_RESULT_" + res);
			JDResult<JDFreight> jdFreightJDResult = JsonUtil.parseObject(res, new TypeReference<JDResult<JDFreight>>() {
			});
			if (jdFreightJDResult.isSuccess()) {
				return jdFreightJDResult.getResult().getFreight();
			} else {
				log.error("JD_FREIGHT_ERROR_RESULT" + res);
				if ("2004".equals(jdFreightJDResult.getResultCode())) {
					String skuId = res.replaceAll("^.*?(\\d+).*$", "$1");
					for (SkuBO item : jdSkus) {
						if (item.getSkuCode().equals(skuId)) {
							throw new BusinessException("2002", "抱歉,【" + item.getGoodsName() + "】已下架，暂时无法购买！");
						}
					}
				}
			}
			throw new BusinessException("获取运费错误");
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				throw e;
			} else {
				log.error("JD_FREIGHT_ERROR_RESULT" + e);
				throw new BusinessException("获取运费错误");
			}
		}
	}

	@Override
	public Double freight(QueryFreightVO qeueryFreightVO) {
		try {
			DistrictInfoEntity province = districtInfoService.getById(qeueryFreightVO.getProvinceId().intValue());
			DistrictInfoEntity city = districtInfoService.getById(qeueryFreightVO.getCityId().intValue());
			DistrictInfoEntity county = districtInfoService.getById(qeueryFreightVO.getDistrictId().intValue());
			DistrictInfoEntity town = qeueryFreightVO.getStreetId() == null ? null : districtInfoService.getById(qeueryFreightVO.getStreetId().intValue());
			
			List<JDOrderSubmitSKU> jdOrderSKUs = Lists.newArrayList();
			JDOrderSubmitSKU jdOrderSubmitSKU = new JDOrderSubmitSKU();
			jdOrderSubmitSKU.setSkuId(Long.parseLong(qeueryFreightVO.getSku()));
			jdOrderSubmitSKU.setNum(qeueryFreightVO.getNum());
			jdOrderSubmitSKU.setBNeedGift(false);
			jdOrderSubmitSKU.setBNeedAnnex(true);
			jdOrderSKUs.add(jdOrderSubmitSKU);

			Map<String, String> param = getParam();
			param.put("sku", JsonUtil.convertObjToStr(jdOrderSKUs));
			param.put("province", String.valueOf(province.getJdAreaId()));
			param.put("city", String.valueOf(city.getJdAreaId()));
			param.put("county", String.valueOf(county.getJdAreaId()));
			param.put("town", String.valueOf(town == null ? 0L : town.getJdAreaId()));
			param.put("paymentType", "4");
			String res = postData(getOrder_freight_url(), param, CHARSET);
			log.info("JD_FREIGHT_RESULT_" + res);
			JDResult<JDFreight> jdFreightJDResult = JsonUtil.parseObject(res, new TypeReference<JDResult<JDFreight>>() {
			});
			if (jdFreightJDResult.isSuccess()) {
				return jdFreightJDResult.getResult().getFreight();
			} else {
				return 0.00d;
			}
			
		} catch (Exception e) {
			log.error("JD_FREIGHT_ERROR_RESULT" + e);
			throw new BusinessException("获取运费错误");
		}
	}
}
