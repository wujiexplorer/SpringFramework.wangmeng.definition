package com.lx.benefits.web.controller.yianapi;


import com.lx.benefits.bean.dto.yianapi.client.*;
import com.lx.benefits.bean.enums.yianapi.enums.ClientPointPayType;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.utils.RequestHelper;
import com.lx.benefits.web.ao.ClientOrderAO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 怡安第三方平台提交积分订单
 * Created by lidongri on 2018/12/3.
 */
@Controller
@RequestMapping("/client/api")
public class ClientOrderController {


    @Autowired
    private ClientOrderAO clientOrderAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 提交第三方积分订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add-external-order.htm", method = RequestMethod.POST)
    public String orderSubmit(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("M-CLIENT-ORDER-CREATE-RECEIVED-PARAM:" + String.valueOf(jsonStr));
            ClientOrderReq req = JsonUtil.parse(jsonStr, ClientOrderReq.class);

            validParam(req);

            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                throw new MobileException("token not valid");
            }

            ClientResult<ClientOrderResp> result = clientOrderAO.submit(req, token.substring(7));
            logger.info("M-CLIENT-ORDER-CREATE-FINISHED:" + JsonUtil.convertObjToStr(result));

            return JsonUtil.convertObjToStr(result);
        } catch (MobileException me) {
            logger.error("M-CLIENT-ORDER-CREATE-ERROR:", me);

            return JsonUtil.convertObjToStr(
                    new ClientResult<>("create_order_with_ex_num", "create order failed " + me.getMessage(), false));
        } catch (Exception e) {
            logger.error("M-CLIENT-ORDER-CREATE-ERROR:", e);
            return JsonUtil.convertObjToStr(
                    new ClientResult<>("create_order_with_ex_num", "system error", false));
        }
    }

    /**
     * 更新第三方订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update-order-by-ext-num.htm", method = RequestMethod.POST)
    public String orderUpdate(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("M-CLIENT-ORDER-UPDATE-RECEIVED-PARAM:" + String.valueOf(jsonStr));
            ClientOrderUpdateReq req = JsonUtil.parse(jsonStr, ClientOrderUpdateReq.class);

            validParam(req);

            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                throw new MobileException("token not valid");
            }

            ClientResult<ClientOrderResp> result = clientOrderAO.update(req, token.substring(7));
            logger.info("M-CLIENT-ORDER-UPDATE-FINISHED:" + JsonUtil.convertObjToStr(result));

            return  JsonUtil.convertObjToStr(result);
        } catch (MobileException me) {
            logger.error("M-CLIENT-ORDER-UPDATE-ERROR:", me);

            return JsonUtil.convertObjToStr(
                    new ClientResult<>("update_order_with_ex_num", "update order failed " + me.getMessage(), false));
        } catch (Exception e) {
            logger.error("M-CLIENT-ORDER-UPDATE-ERROR:", e);
            return JsonUtil.convertObjToStr(
                    new ClientResult<>("update_order_with_ex_num", "system error", false));
        }
    }

    /**
     * 查看第三方订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get-order-detail-by-ext-num.htm", method = RequestMethod.POST)
    public String orderDetail(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("M-CLIENT-ORDER-DETAIL-RECEIVED-PARAM:" + String.valueOf(jsonStr));
            ClientOrderBaseReq req = JsonUtil.parse(jsonStr, ClientOrderBaseReq.class);

            validParam(req);

            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                throw new MobileException("token not valid");
            }

            ClientResult<ClientOrderResp> result = clientOrderAO.detail(req, token.substring(7));
            logger.info("M-CLIENT-ORDER-DETAIL-FINISHED:" + JsonUtil.convertObjToStr(result));

            return JsonUtil.convertObjToStr(result);
        } catch (MobileException me) {
            logger.error("M-CLIENT-ORDER-DETAIL-ERROR:", me);

            return JsonUtil.convertObjToStr(
                    new ClientResult<>("query_order_with_ex_num", "query order with external order number failed " + me.getMessage(), false));
        } catch (Exception e) {
            logger.error("M-CLIENT-ORDER-DETAIL-ERROR:", e);
            return JsonUtil.convertObjToStr(
                    new ClientResult<>("query_order_with_ex_num", "system error", false));
        }
    }

    /**
     * 取消第三方订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancel-external-order.htm", method = RequestMethod.POST)
    public String orderCancel(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("M-CLIENT-ORDER-CANCEL-RECEIVED-PARAM:" + String.valueOf(jsonStr));
            ClientOrderBaseReq req = JsonUtil.parse(jsonStr, ClientOrderBaseReq.class);

            validParam(req);

            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                throw new MobileException("token not valid");
            }

            ClientResult<ClientOrderCancelResp> result = clientOrderAO.cancel(req, token.substring(7));
            logger.info("M-CLIENT-ORDER-CANCEL-FINISHED:" + JsonUtil.convertObjToStr(result));

            return JsonUtil.convertObjToStr(result);
        } catch (MobileException me) {
            logger.error("M-CLIENT-ORDER-CANCEL-ERROR:", me);

            return JsonUtil.convertObjToStr(
                    new ClientResult<>("cancel_with_refund", "cancel order failed " + me.getMessage(), false));
        } catch (Exception e) {
            logger.error("M-CLIENT-ORDER-CANCEL-ERROR:", e);
            return JsonUtil.convertObjToStr(
                    new ClientResult<>("cancel_with_refund", "system error", false));
        }
    }

    /**
     * 获取账户详情
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/account-detail.htm", method = RequestMethod.POST)
    public String accountDetail(HttpServletRequest request) {
        try {
            String jsonStr = RequestHelper.getJsonStrByIO(request);
            logger.info("M-CLIENT-ACCOUNT-DETAIL-RECEIVED-PARAM:" + String.valueOf(jsonStr));
            ClientUserPointReq req = JsonUtil.parse(jsonStr, ClientUserPointReq.class);

            validParam(req);

            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                throw new MobileException("token not valid");
            }

            ClientResult<ClientUserPointResp> result = clientOrderAO.point(req, token.substring(7));
            logger.info("M-CLIENT-ACCOUNT-DETAIL-FINISHED:" + JsonUtil.convertObjToStr(result));

            return JsonUtil.convertObjToStr(result);
        } catch (MobileException me) {
            logger.error("M-CLIENT-ACCOUNT-DETAIL-ERROR:", me);

            return JsonUtil.convertObjToStr(
                    new ClientResult<>("view_account_detail", "view account detail failed " + me.getMessage(), false));
        } catch (Exception e) {
            logger.error("M-CLIENT-ACCOUNT-DETAIL-ERROR:", e);
            return JsonUtil.convertObjToStr(
                    new ClientResult<>("view_account_detail", "system error", false));
        }
    }

    private void validParam(ClientOrderReq req) {
        if (req == null) throw new MobileException("param error");

        if (StringUtils.isBlank(req.getEe_no())) throw new MobileException("ee_no is blank");
        if (StringUtils.isBlank(req.getOid())) throw new MobileException("oid is blank");
        if (StringUtils.isBlank(req.getOrg_code())) throw new MobileException("org_code is blank");
        if (StringUtils.isBlank(req.getSign())) throw new MobileException("sign is blank");
        if (StringUtils.isBlank(req.getTimestamp())) throw new MobileException("timestamp is blank");

        if (req.getShipping_fee() == null || req.getShipping_fee() < 0)
            throw new MobileException("shipping_fee is blank or less than 0");
        if (req.getPrice() == null) throw new MobileException("price is blank");

        if (CollectionUtils.isEmpty(req.getSku_items())) throw new MobileException("sku_items is blank");

        if (!CollectionUtils.isEmpty(req.getPay_channels())) {
            for (String c : req.getPay_channels()) {
                if (ClientPointPayType.getByName(c) == null)
                    throw new MobileException("pay_channels can only be [general_point,exclusive_point] your input:["+c+"]");
            }
        }
        if (req.getShipping_fee() == null || req.getShipping_fee() < 0) {
            throw new MobileException("shipping fee should great than or equal 0");
        }
        BigDecimal totalReal = new BigDecimal("0");

        for (ClientOrderItemReq itemReq : req.getSku_items()) {
            if (itemReq == null || StringUtils.isBlank(itemReq.getPartner_sku()) || itemReq.getQuantity() == null || itemReq.getQuantity() < 0 ||
                    itemReq.getUnit_price() == null || itemReq.getUnit_price() < 0) {
                throw new MobileException("sku is blank or quantity less than 0 or unit price less than 0");
            }
            totalReal = totalReal.add(new BigDecimal(itemReq.getUnit_price().toString()).multiply(new BigDecimal(itemReq.getQuantity().toString()))).setScale(2);
        }
        //if(totalReal.doubleValue()<=0D) throw new MobileException("order total price is 0");
        if (req.getPrice() > totalReal.doubleValue())
            throw new MobileException("price should less or equal to order total price");

        if (new BigDecimal(req.getShipping_fee()).add(new BigDecimal(req.getPrice())).doubleValue() <= 0) {
            throw new MobileException("order total price is 0");
        }

        validTime(req.getTimestamp());

    }

    private void validParam(ClientOrderUpdateReq req) {
        if (req == null) throw new MobileException("param error");

        if (StringUtils.isBlank(req.getOid())) throw new MobileException("oid is blank");
        if (StringUtils.isBlank(req.getSign())) throw new MobileException("sign is blank");
        if (StringUtils.isBlank(req.getTimestamp())) throw new MobileException("timestamp is blank");
        if (req.getRemark() == null) throw new MobileException("remark is null");
        if (req.getRemark().length() > 500) throw new MobileException("remark max length is 500");
        validTime(req.getTimestamp());

    }

    private void validParam(ClientOrderBaseReq req) {
        if (req == null) throw new MobileException("param error");

        if (StringUtils.isBlank(req.getOid())) throw new MobileException("oid is blank");
        if (StringUtils.isBlank(req.getSign())) throw new MobileException("sign is blank");
        if (StringUtils.isBlank(req.getTimestamp())) throw new MobileException("timestamp is blank");
        validTime(req.getTimestamp());

    }

    private void validParam(ClientUserPointReq req) {
        if (req == null) throw new MobileException("param error");

        if (StringUtils.isBlank(req.getEe_no())) throw new MobileException("ee_no is blank");
        if (StringUtils.isBlank(req.getOrg_code())) throw new MobileException("org_code is blank");
        if (StringUtils.isBlank(req.getSign())) throw new MobileException("sign is blank");
        if (StringUtils.isBlank(req.getTimestamp())) throw new MobileException("timestamp is blank");
        validTime(req.getTimestamp());

    }

    private void validTime(String time) {
        Date date = DateUtil.getDate(time, DateUtil.DATETIME_FORMAT);
        if (date == null) throw new MobileException(" time format error");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        if (date.after(calendar.getTime())) throw new MobileException(" time expired");

    }
}
