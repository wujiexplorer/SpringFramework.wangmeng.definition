package com.lx.benefits.service.client.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.bo.order.ProductOrderDetails;
import com.lx.benefits.bean.entity.client.ClientMessage;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.enums.ClientMessageType;
import com.lx.benefits.bean.vo.client.ClientMessageBean;
import com.lx.benefits.config.properties.YibaoProperties;
import com.lx.benefits.service.client.ClientMessageService;
import com.lx.benefits.service.client.YibaoApiService;
import com.lx.benefits.service.order.OrderService;

import lombok.extern.slf4j.Slf4j;

/**
 * 易豹接口调用
 * 
 */
@Service
@Slf4j
public class YibaoApiServiceImpl implements YibaoApiService {

	@Autowired
	private YibaoProperties yibaoProperties;
	@Resource(name = "formStringJsonRestTemplate")
	private RestTemplate restTemplate;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ClientMessageService clientMessageService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private String YIBAO_CLIENT_MESSAGE_QUEUE = "client_message_yibao";

	// 用户查询接口【DUB00046】
	@Override
	public EmployeeUserInfo getUserInfo(String token) {
		JSONObject content = new JSONObject();
		content.put("token", token);
		JSONObject response = this.sendRequest("DUB00046", content);
		EmployeeUserInfo employeeUserInfo = null;
		if (response != null && Integer.valueOf(0).equals(response.getInteger("code"))) {
			JSONObject message = response.getJSONObject("message");
			String employeeNo = message.getString("userId");
			if (!StringUtils.isEmpty(employeeNo)) {
				employeeUserInfo = new EmployeeUserInfo();
				employeeUserInfo.setEmployeeNo(employeeNo);
				employeeUserInfo.setEnterprId(yibaoProperties.getEnterprId());
				employeeUserInfo.setEmployeeName(message.getString("userName"));
			}
		}
		return employeeUserInfo;
	}

	// 可用积分查询接口【DUB00047】
	@Override
	public BigDecimal getAvailablePoints(String userId) {
		JSONObject content = new JSONObject();
		content.put("userId", userId);
		JSONObject response = this.sendRequest("DUB00047", content);
		BigDecimal validPoints = null;
		if (response != null && Integer.valueOf(0).equals(response.getInteger("code"))) {
			JSONObject message = response.getJSONObject("message");
			validPoints = message.getBigDecimal("validPoints");
		}
		return validPoints == null ? BigDecimal.ZERO : validPoints;
	}

	// 积分预扣减接口【DUB00048】
	@Override
	public boolean preReducePoints(String clientUserId, BigDecimal preReducePoints, String orderPayNumber) {
		JSONObject content = new JSONObject();
		content.put("userId", clientUserId);
		content.put("preReducePoints", preReducePoints);
		content.put("orderPayNumber", orderPayNumber);
		JSONObject response = this.sendRequest("DUB00048", content);
		return response == null ? false : Integer.valueOf(0).equals(response.getInteger("code"));
	}

	// 订单状态通知接口【DUB00049】
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
	@Async
	public void sendOrderMessage(String clientUserId, Long payOrderNumber, boolean isSuccess) {
		ClientMessage clientMessage = new ClientMessage();
		clientMessage.setMessageId(UUID.randomUUID().toString().replace("-", ""));
		clientMessage.setCreateTime(new Date());
		ProductOrderDetails productOrderDetails;
		if (isSuccess) {
			productOrderDetails = orderService.getProductOrderDetailsByOrderNumber(payOrderNumber);
			if (productOrderDetails != null) {
				clientMessage.setMessageType(ClientMessageType.ORDER_SUCCESS.type);
			}
		} else {
			clientMessage.setMessageType(ClientMessageType.ORDER_FAILED.type);
			productOrderDetails = new ProductOrderDetails();
			productOrderDetails.setOrderPayNumber(payOrderNumber.toString());
		}
		if (productOrderDetails == null) {
			log.error("sendOrderMessage failed, cannot find productOrderDetails, payOrderNumber={}", payOrderNumber);
			return;
		}
		ClientMessageBean<ProductOrderDetails> clientMessageBean = new ClientMessageBean<>(clientMessage.getMessageId(), clientMessage.getMessageType(),
				productOrderDetails, clientMessage.getCreateTime());
		if (this.sendClientMessage(clientUserId, clientMessageBean)) {// 发送成功
			clientMessage.setIsSuccess(true);
		} else {// 发送失败
			clientMessage.setIsSuccess(false);
			try {
				stringRedisTemplate.opsForList().rightPush(YIBAO_CLIENT_MESSAGE_QUEUE, clientMessage.getMessageId());
			} catch (Exception e) {
				log.error("cache failed clientMessage failed, messageId={}", clientMessage.getMessageId());
			}
		}
		clientMessage.setMessageInfo(JSON.toJSONString(clientMessageBean));
		clientMessage.setClientUserId(clientUserId);
		clientMessage.setOrgCode(yibaoProperties.getOrgCode());
		clientMessageService.addMessageRecorder(clientMessage);
	}

	private boolean sendClientMessage(String clientUserId, ClientMessageBean<?> clientMessage) {
		JSONObject content = new JSONObject();
		content.put("userId", clientUserId);
		content.put("data", clientMessage);
		JSONObject response = this.sendRequest("DUB00049", content);
		return response == null ? false : Integer.valueOf(0).equals(response.getInteger("code"));
	}

	private JSONObject sendRequest(String bizCode, JSONObject content) {
		log.info("sendRequest, bizCode={}, content={}", bizCode, content.toJSONString());
		content.put("platformId", yibaoProperties.getPlatformId());
		String contentStr = JSON.toJSONStringWithDateFormat(content, "yyyy-MM-dd HH:mm:ss");
		Long timestamp = System.currentTimeMillis();
		MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
		request.set("appId", yibaoProperties.getAppId());
		request.set("bizCode", bizCode);
		request.set("bizId", bizCode);
		request.set("content", contentStr);
		request.set("timestamp", timestamp);
		request.set("sign", DigestUtils.sha1Hex(
				String.format("bizCode=%s&bizId=%s&content=%s&timestamp=%s&appKey=%s", bizCode, bizCode, contentStr, timestamp, yibaoProperties.getAppKey())));
		for (Entry<String, List<Object>> item : request.entrySet()) {
			System.out.println(item.getKey() + " : " + item.getValue().get(0));
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(request, httpHeaders);
		JSONObject result;
		try {
			log.info("request={}", JSON.toJSONString(request));
			result = restTemplate.postForObject(yibaoProperties.getApiUrl(), httpEntity, JSONObject.class);
			log.info("result={}", result.toJSONString());
		} catch (Exception e) {
			result = null;
			log.error("sendRequest failed, bizCode={}, message={}", bizCode, e.getMessage());
		}
		return result;
	}

	@Override
	public boolean isBelongTo(Long enterprId) {
		return yibaoProperties.getEnterprId().equals(enterprId);
	}

	@Override
	public String getOrgCode() {
		return yibaoProperties.getOrgCode();
	}

	@Override
	public void handleFailedMessage() {
		for (int i = 0; i < 20; i++) {
			String messageId = stringRedisTemplate.opsForList().leftPop(YIBAO_CLIENT_MESSAGE_QUEUE);
			if (messageId == null) {
				break;
			}
			TypeReference<ClientMessageBean<ProductOrderDetails>> clientMessageBeantype = new TypeReference<ClientMessageBean<ProductOrderDetails>>() {
			};
			ClientMessage record = clientMessageService.getMessageById(messageId);
			if (record == null) {
				continue;
			}
			log.info("timing handleFailedMessage, messageId={}", messageId);
			boolean result = this.sendClientMessage(record.getClientUserId(),
					(ClientMessageBean<?>) JSONObject.parseObject(record.getMessageInfo(), clientMessageBeantype));
			clientMessageService.udateMessageStatus(messageId, result);
		}
	}
}
