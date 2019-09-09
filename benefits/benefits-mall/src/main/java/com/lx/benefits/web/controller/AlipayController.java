package com.lx.benefits.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.lx.benefits.bean.enums.PayChannelEnum;
import com.lx.benefits.config.properties.PayProperties;
import com.lx.benefits.utils.HttpHelper;
import com.lx.benefits.support.pay.PaySupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description: 支付宝后台接口
 */
@Slf4j
@RestController
@RequestMapping(value = "/alipay")
public class AlipayController {

    @Resource
    private PayProperties payProperties;

    @Resource
    private PaySupport paySupport;

    /**
     * 支付宝服务器异步通知页面
     * @param request
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易凭证号
     * @param trade_status 交易状态
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/notify")
    public String alipayNotify(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status) throws AlipayApiException {

        String payMark = request.getParameter("out_trade_no");
        String payTid = request.getParameter("trade_no");
        String tradeStatus = request.getParameter("trade_status");
        log.info("处理支付宝回调开始，payMark={}, payTid={}, tradeStatus={}", payMark, payTid, tradeStatus);

        PayProperties.Alipay alipay = payProperties.getAlipay();
        Map<String, String> params = HttpHelper.getRequestParamtersMap(request);
        log.info("notify params: {}", JSONObject.toJSON(params));
        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay.getPublicKey(), alipay.getInputCharset(), alipay.getSignType());
        log.info("notify signVerified: {}", signVerified);
        if (!signVerified) {
            log.info("验证失败,不去更新状态");
            return ("fail");
        }
        //处理你的业务逻辑，更细订单状态等
        if("TRADE_SUCCESS".equals(tradeStatus)) {
            if (paySupport.payCallback(payMark, payTid, PayChannelEnum.ALIPAY.getCode())) {
                log.info("处理支付宝回调成功，payMark={}", payMark);
                return ("success");
            } else {
                log.info("处理支付宝回调失败，payMark={}", payMark);
                return ("fail");
            }
        }else {
            return ("fail");
        }
    }

    /**
     * 支付宝服务器同步通知页面
     * @param request
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易凭证号
     * @param total_amount 交易状态
     * @return
     * @throws AlipayApiException
     */
    @GetMapping("/return")
    public String alipayReturn(HttpServletRequest request, String out_trade_no,String trade_no,String total_amount) throws AlipayApiException {
        PayProperties.Alipay alipay = payProperties.getAlipay();
        Map<String, String> params = HttpHelper.getRequestParamtersMap(request);
        log.info("return params: {}", JSONObject.toJSON(params));
        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay.getPublicKey(), alipay.getInputCharset(), alipay.getSignType());
        log.info("return signVerified: {}", signVerified);

        if (signVerified) {
            return ("success");
        } else {
            log.info("验证失败,不去更新状态");
            return ("fail");
        }
    }

//    private Map<String, String> getParamsMap(HttpServletRequest request) {
//        Map<String,String> params = new HashMap<>();
//        Map requestParams = request.getParameterMap();
//        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                        : valueStr + values[i] + ",";
//            }
//            //乱码解决，这段代码在出现乱码时使用
//            try {
//                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//                params.put(name, valueStr);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return params;
//    }

}
