package com.lx.benefits.support.order;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.apollo.common.exception.BusinessException;
import com.google.gson.Gson;
import com.lx.benefits.bean.bo.order.OrderRefundBO;
import com.lx.benefits.bean.vo.pay.AlipayVO;
import com.lx.benefits.config.properties.PayProperties;
import com.lx.benefits.sdk.alipay.sign.RSA;
import com.lx.benefits.sdk.alipay.util.AlipayCore;
import com.lx.benefits.bean.bo.order.OrderPayBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class OrderAliPaySupport {

    @Resource
    private PayProperties payProperties;

    private final static String ALIPAY_TRADE_REFUND_SUCC_RESPONSE_CODE="10000";

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://openapi.alipay.com/gateway.do?";

    public String pay(OrderPayBO orderPayBO) {
        try{
            if(orderPayBO.isFromWeb()){
                return buildHtml(orderPayBO);
            }

            String paramStr = buildParamsStr(orderPayBO);
            String sign = RSA.rsaSign(paramStr, payProperties.getAlipay().getPrivateKey(), payProperties.getAlipay().getInputCharset());

            Map<String, String> paramMap = new LinkedHashMap<>();
            paramMap.put("sign", "\"" + URLEncoder.encode(sign, payProperties.getAlipay().getInputCharset()) + "\"");
            paramMap.put("sign_type", "\"" + payProperties.getAlipay().getSignType() + "\"");
            paramStr += "&" + AlipayCore.createLinkString(paramMap);
            return paramStr;
        }catch (Exception e){
            log.error("order pay失败", e);
            throw new BusinessException("订单支付失败");
        }
    }

    private String buildParamsStr(OrderPayBO orderPayBO){
        PayProperties.Alipay alipay = payProperties.getAlipay();
        Map<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("partner", "\"" + alipay.getPartner() + "\"");
        paramMap.put("seller_id", "\"" + alipay.getPartner() + "\"");
        paramMap.put("out_trade_no", "\"" + orderPayBO.getPayMark() + "\"");
        paramMap.put("subject", "\"" + "福粒" + "\"");
        paramMap.put("body", "\"" + "福粒" + "\"");
        paramMap.put("total_fee", "\"" + orderPayBO.getRealPrice()/100d + "" + "\"");
        paramMap.put("notify_url", "\"" + alipay.getNotifyUrl() + "\"");
        paramMap.put("service", "\"" + "mobile.securitypay.pay" + "\"");
        paramMap.put("payment_type", "\"" + "1" + "\"");
        paramMap.put("_input_charset", "\"" + alipay.getInputCharset() + "\"");
        paramMap.put("it_b_pay", "\"" + "29m" + "\"");
        paramMap.put("return_url", "\"" + "m.alipay.com" + "\"");
        return AlipayCore.createLinkString(AlipayCore.paraFilter(paramMap));
    }

    private String buildHtml(OrderPayBO orderPayBO) throws AlipayApiException {
        PayProperties.Alipay alipay = payProperties.getAlipay();

        //这个应该是从前端端传过来的，这里为了测试就从后台写死了
        AlipayVO vo = new AlipayVO();
        vo.setOut_trade_no(orderPayBO.getPayMark());
        vo.setTotal_amount(orderPayBO.getRealPrice()/100d + "");
        vo.setSubject("福粒订单");
        //这个是固定的
        vo.setProduct_code("FAST_INSTANT_TRADE_PAY");
        String json = new Gson().toJson(vo);
        log.info("json: {}", json);


        AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY_NEW,alipay.getAppId() ,alipay.getPrivateKey(), AlipayConstants.FORMAT_JSON,alipay.getInputCharset(),alipay.getPublicKey(),alipay.getSignType());

//        AlipayTradePagePayModel payModel = new AlipayTradePagePayModel();
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipay.getReturnUrl());
        alipayRequest.setNotifyUrl(alipay.getNotifyUrl());
        alipayRequest.setBizContent(json);
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        log.info("result: {}", result);
        return result;


//        // 把请求参数打包成数组
//        Map<String, String> sParaTemp = new HashMap<>();
//        sParaTemp.put("service", "alipay.trade.page.pay");
//        sParaTemp.put("partner", alipay.getPartner());
//        sParaTemp.put("seller_id", alipay.getPartner());
//        sParaTemp.put("_input_charset", alipay.getInputCharset());
//        sParaTemp.put("payment_type", "1");
//        sParaTemp.put("notify_url", alipay.getNotifyUrl());
//        sParaTemp.put("return_url", "");
//        sParaTemp.put("out_trade_no", orderPayParam.getPayMark());
//        sParaTemp.put("subject", "福粒");
//        sParaTemp.put("total_fee", orderPayParam.getRealPrice()/100d + "");
//        sParaTemp.put("app_pay", "Y");
//        sParaTemp.put("body", "");
//
//        String sHtmlText =  AlipaySubmit.buildRequest(sParaTemp, "get", "确认", alipay.getInputCharset(), alipay.getSignType(), alipay.getPrivateKey());
//        sHtmlText += "<script>document.forms['alipaysubmit'].submit();</script>";
//        return sHtmlText;
    }

    /**
     * alipay退款打款接口
     * @param refundBO
     * @return
     * @throws AlipayApiException
     */
    public void alipayRefund(OrderRefundBO refundBO) throws AlipayApiException {
        PayProperties.Alipay alipay = payProperties.getAlipay();

        AlipayTradeRefundModel tradeRefundModel = new AlipayTradeRefundModel();
        tradeRefundModel.setOutTradeNo(refundBO.getPayMark());
        tradeRefundModel.setRefundAmount(refundBO.getRefundAmount().toString());
        tradeRefundModel.setRefundReason(refundBO.getRefundReason());
        tradeRefundModel.setOutRequestNo(refundBO.getOutRequestNo());
        tradeRefundModel.setOperatorId(refundBO.getOperatorId());

        AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY_NEW,alipay.getAppId() ,alipay.getPrivateKey(), AlipayConstants.FORMAT_JSON,alipay.getInputCharset(),alipay.getAlipayPublicKey(),alipay.getSignType());
        // 设置请求参数
        AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
        refundRequest.setReturnUrl(alipay.getReturnUrl());
        refundRequest.setNotifyUrl(alipay.getNotifyUrl());
        refundRequest.setBizModel(tradeRefundModel);
        AlipayTradeRefundResponse response = alipayClient.execute(refundRequest);
        if (ALIPAY_TRADE_REFUND_SUCC_RESPONSE_CODE.equals(response.getCode())) {
            log.info("退款打款成功 退款编号：{} ,alipay退款结果:{}",refundBO.getRefundApplyNumber(), JSON.toJSONString(response));
        }else {
            log.error("退款打款失败 退款编号：{} ,alipay退款结果:{}",refundBO.getRefundApplyNumber(), JSON.toJSONString(response));
            throw new BusinessException(String.format("退款打款失败,失败Code：%s,Msg:%s",response.getSubCode(),response.getSubMsg()));
        }
    }
}






