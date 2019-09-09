package com.lx.benefits.service.jdOrder.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.dto.jd.res.JDResult;
import com.lx.benefits.bean.dto.jd.utils.AssertUtil;
import com.lx.benefits.bean.dto.jdOrder.JdMerchantOrderDto;
import com.lx.benefits.bean.dto.jdOrder.JdMerchantOrderItemDto;
import com.lx.benefits.bean.dto.jdOrder.JdMerchantOrderReq;
import com.lx.benefits.bean.dto.jdOrder.api.*;
import com.lx.benefits.bean.dto.jdOrder.enums.JDOrderType;
import com.lx.benefits.bean.entity.jdOrder.JdMerchantOrder;
import com.lx.benefits.bean.entity.jdOrder.JdMerchantOrderItem;
import com.lx.benefits.bean.util.BeansUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.jdOrder.JdMerchantOrderItemMapper;
import com.lx.benefits.mapper.jdOrder.JdMerchantOrderMapper;
import com.lx.benefits.service.jd.impl.JDBaseService;
import com.lx.benefits.service.jdOrder.IJDOrderService;
import com.lx.benefits.service.jdOrder.JdOrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 15:48
 */
@Service
public class JdOrderServiceImpl extends JDBaseService implements JdOrderService {

    private Logger logger = LoggerFactory.getLogger(JdOrderServiceImpl.class);

    @Autowired
    JdMerchantOrderMapper orderMapper;

    @Autowired
    JdMerchantOrderItemMapper orderItemMapper;

    @Autowired
    IJDOrderService ijdOrderService;

    @Override
    public JSONObject getJdOrderList(JdMerchantOrderReq record) {
        JSONObject jsonObject = new JSONObject();
        record.setPage(record.getPage() > 0 ? (record.getPage() - 1) * record.getPageSize() : 0);
        jsonObject.put("list", BeansUtils.copyArrayProperties(orderMapper.getOrderList(record), JdMerchantOrderDto.class));
        jsonObject.put("count", orderMapper.getOrderListCount(record));
        return jsonObject;
    }

    @Override
    public JSONObject getJdOrderDetail(Long orderId) {
        JSONObject jsonObject = new JSONObject();
        JdMerchantOrderDto orderDto = BeansUtils.copyProperties(orderMapper.getOrderById(orderId), JdMerchantOrderDto.class);
        if (orderDto != null) {
            List<JdMerchantOrderItem> list = orderItemMapper.getOrderItemById(orderDto.getMerchantOrderId());
            orderDto.setItem(BeansUtils.copyArrayProperties(list, JdMerchantOrderItemDto.class));
        }
        jsonObject.put("order", orderDto);
        return jsonObject;
    }

    @Override
    public JSONObject getJdOrderCancel(Long orderId) {
        JdMerchantOrder order = orderMapper.getOrderById(orderId);
        if (order == null) {
            return Response.fail("获取订单信息为空");
        }
        if (order.getSubmitState() == null || order.getSubmitState() != 1 || order.getOrderState() == null || order.getOrderState() != 1) {
            return Response.fail("已取消并确认下单的订单才可取消我方订单");
        }
        Boolean bool = ijdOrderService.cancel(order.getMerchantOrderId());
        order.setSubmitState(0);
        if (bool && orderMapper.update(order) > 0) {
            return Response.succ("取消订单成功");
        }
        return Response.fail("取消订单失败");
    }

    @Override
    public JSONObject getJdOrderUpdate(Long orderId) {
        JdMerchantOrder order = orderMapper.getOrderByMerchantId(orderId);
        if (order == null) {
            return Response.fail("获取订单信息为空");
        }
        try {
            if (updateJDOrder(Long.valueOf(order.getMerchantOrderId()), order)) {
                return Response.succ("更新订单成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Response.fail("更新订单失败");

    }

    @Override
    public JSONObject getJdOrderTrack(Long orderId) {
        JdMerchantOrder order = orderMapper.getOrderByMerchantId(orderId);
        if (order == null) {
            return Response.fail("获取订单信息为空");
        }
        return Response.succ(ijdOrderService.orderTrack(order.getMerchantOrderId()));
    }

    @Transactional
    public boolean updateJDOrder(Long orderId, JdMerchantOrder order) throws Exception {
        JdOrder jdOrder = jdOrder(orderId);
        Date cur = new Date();
        if (jdOrder.getType().equals(JDOrderType.PARENT.getCode())) {
            JdOrderParentOrder parentOrder = jdOrder.getParentOrder();
            JDOrderParentOrderSnap parentOrderSnap = parentOrder.getpOrder();
            getParentOrder(cur, order, parentOrder, parentOrderSnap);
            orderMapper.update(order);
            delOrderItem(String.valueOf(orderId));
            addOrderItem(cur, parentOrderSnap.getSku(), String.valueOf(orderId));
            List<JDOrderSubOrder> jdOrderSubOrders = parentOrder.getcOrder();
            if (CollectionUtils.isEmpty(jdOrderSubOrders)) {
                logger.warn("JD_ORDER_WITH_NO_SUB_ORDER_RETURN_JD_ORDER_ID_" + parentOrderSnap.getJdOrderId());
                return true;
            }
            //sub order
            JdMerchantOrderReq subOrderQuery = new JdMerchantOrderReq();
            subOrderQuery.setMerchantParentOrderId(order.getMerchantOrderId());
            List<JdMerchantOrder> subOrders = orderMapper.getOrderList(subOrderQuery);//.queryByObject(subOrderQuery);
            List<Long> transforIds = new ArrayList<>(jdOrderSubOrders.size());
            for (JDOrderSubOrder jdOrderSubOrder : jdOrderSubOrders) {
                transforIds.add(jdOrderSubOrder.getJdOrderId());
                boolean needAdd = true;
                for (JdMerchantOrder sub : subOrders) {
                    if (jdOrderSubOrder.getJdOrderId().toString().equals(sub.getMerchantOrderId())) {
                        //update;
                        needAdd = false;
                        updateOrderInfo(cur, jdOrderSubOrder, sub);
                        break;
                    }
                }
                if (needAdd) {
                    // add
                    addOrderInfo(orderId, order.getSubOrderCode(), order.getMemberId(), cur, jdOrderSubOrder);
                }
            }
            for (JdMerchantOrder sub : subOrders) {
                boolean needDel = true;
                for (Long id : transforIds) {
                    if (sub.getMerchantOrderId().equals(id.toString())) {
                        needDel = false;
                        break;
                    }
                }
                if (needDel) {
                    sub.setIsDelete(true);
                    orderMapper.update(sub);
                    delOrderItem(sub.getMerchantOrderId());
                }
            }
        } else if (jdOrder.getType().equals(JDOrderType.SUB.getCode())) {
            //子单只可能更新,新增子单需要父单来完成~~~~
            JDOrderSubOrder subOrder = jdOrder.getSubOrder();
            JdMerchantOrder merchantOrder = orderMapper.getOrderByMerchantId(subOrder.getJdOrderId());
            if (merchantOrder == null) {
                logger.error("JD_UPDATE_SUB_ORDER_ERROR_SUB_ORDER_NOT_EXIT_ORDER_ID_" + orderId);
                return false;
            }
            updateOrderInfo(cur, subOrder, merchantOrder);
        }
        return true;
    }

    private void getParentOrder(Date cur, JdMerchantOrder order, JdOrderParentOrder parentOrder, JDOrderParentOrderSnap parentOrderSnap) {
        order.setOrderPrice(parentOrderSnap.getOrderPrice());
        order.setOrderNakedPrice(parentOrder.getOrderNakedPrice());
        order.setOrderTaxPrice(parentOrder.getOrderTaxPrice());
        order.setFreight(parentOrderSnap.getFreight());
        order.setOrderState(parentOrder.getOrderState());
        order.setSubmitState(parentOrder.getSubmitState());
        order.setState(parentOrder.getState());
        order.setType(JDOrderType.PARENT.getCode());
        order.setUpdateTime(cur);
        order.setUpdateUser("system");
    }

    private void updateOrderInfo(Date cur, JDOrderSubOrder jdOrderSubOrder, JdMerchantOrder sub) {
        getUpdateOrderInfo(cur, jdOrderSubOrder, sub);
        orderMapper.update(sub);
        delOrderItem(sub.getMerchantOrderId());
        addOrderItem(cur, jdOrderSubOrder.getSku(), sub.getMerchantOrderId());
    }

    private void addOrderInfo(Long merchantParentOrderId, Long subOrderCode, Long memberId, Date cur, JDOrderSubOrder jdOrderSubOrder) {
        JdMerchantOrder addOrder = getMerchantOrder(merchantParentOrderId, subOrderCode, memberId, cur, jdOrderSubOrder);
        orderMapper.insert(addOrder);
        addOrderItem(cur, jdOrderSubOrder.getSku(), String.valueOf(jdOrderSubOrder.getJdOrderId()));
    }

    private JdMerchantOrder getMerchantOrder(Long merchantParentOrderId, Long subOrderCode, Long memberId, Date cur, JDOrderSubOrder jdOrderSubOrder) {
        JdMerchantOrder addOrder = new JdMerchantOrder();
        addOrder.setMerchantOrderId(String.valueOf(jdOrderSubOrder.getJdOrderId()));
        addOrder.setMerchantParentOrderId(String.valueOf(merchantParentOrderId));
        addOrder.setSubOrderCode(subOrderCode);
        addOrder.setMemberId(memberId);
        addOrder.setIsDelete(false);
        addOrder.setType(JDOrderType.SUB.getCode());
        addOrder.setCreateTime(cur);
        addOrder.setCreateUser("system");
        addOrder.setState(jdOrderSubOrder.getState());
        addOrder.setSubmitState(jdOrderSubOrder.getSubmitState());
        addOrder.setOrderState(jdOrderSubOrder.getOrderState());
        addOrder.setBaseFreight(jdOrderSubOrder.getBaseFreight());
        addOrder.setFreight(jdOrderSubOrder.getFreight());
        addOrder.setOrderPrice(jdOrderSubOrder.getOrderPrice());
        addOrder.setOrderNakedPrice(jdOrderSubOrder.getOrderNakedPrice());
        addOrder.setOrderTaxPrice(jdOrderSubOrder.getOrderTaxPrice());
        addOrder.setUpdateTime(cur);
        addOrder.setUpdateUser("system");
        return addOrder;
    }

    private void getUpdateOrderInfo(Date cur, JDOrderSubOrder jdOrderSubOrder, JdMerchantOrder sub) {
        sub.setState(jdOrderSubOrder.getState());
        sub.setSubmitState(jdOrderSubOrder.getSubmitState());
        sub.setOrderState(jdOrderSubOrder.getOrderState());
        sub.setType(JDOrderType.SUB.getCode());
        sub.setBaseFreight(jdOrderSubOrder.getBaseFreight());
        sub.setFreight(jdOrderSubOrder.getFreight());
        sub.setOrderPrice(jdOrderSubOrder.getOrderPrice());
        sub.setOrderNakedPrice(jdOrderSubOrder.getOrderNakedPrice());
        sub.setOrderTaxPrice(jdOrderSubOrder.getOrderTaxPrice());
        sub.setMerchantParentOrderId(String.valueOf(jdOrderSubOrder.getPOrder()));
        sub.setUpdateTime(cur);
        sub.setUpdateUser("system");
    }

    private void delOrderItem(String orderId) {
        JdMerchantOrderItem merchantOrderItemForUpdate = new JdMerchantOrderItem();
        merchantOrderItemForUpdate.setOrderId(orderId);
        merchantOrderItemForUpdate.setIsDelete(true);
        orderItemMapper.update(merchantOrderItemForUpdate);
    }

    private void addOrderItem(Date cur, List<JDOrderItem> jdOrderItems, String orderId) {
        for (JDOrderItem jdOrderItem : jdOrderItems) {
            JdMerchantOrderItem merchantOrderItem = new JdMerchantOrderItem();
            merchantOrderItem.setOrderId(orderId);
            merchantOrderItem.setSku(String.valueOf(jdOrderItem.getSkuId()));
            merchantOrderItem.setName(jdOrderItem.getName());
            merchantOrderItem.setType(jdOrderItem.getType());
            merchantOrderItem.setCategory(jdOrderItem.getCategory());
            merchantOrderItem.setOid(jdOrderItem.getOid());
            merchantOrderItem.setPrice(jdOrderItem.getPrice());
            merchantOrderItem.setNakedPrice(jdOrderItem.getNakedPrice());
            merchantOrderItem.setRemoteRegionFreight(jdOrderItem.getRemoteRegionFreight());
            merchantOrderItem.setTaxPrice(jdOrderItem.getTaxPrice());
            merchantOrderItem.setNum(jdOrderItem.getNum());
            merchantOrderItem.setTax(jdOrderItem.getTax());
            merchantOrderItem.setIsDelete(false);
            merchantOrderItem.setCreateTime(cur);
            merchantOrderItem.setUpdateTime(cur);
            merchantOrderItem.setCreateUser("system");
            merchantOrderItem.setUpdateUser("system");
            orderItemMapper.insert(merchantOrderItem);
        }
    }

    public JdOrder jdOrder(Long jdOrderId) {
        AssertUtil.notNull(jdOrderId, "订单号为空");
        Map<String, String> param = getParam();
        param.put("jdOrderId", jdOrderId.toString());
        String res;
        try {
            res = postData(getOrder_query_url(), param, CHARSET);
        } catch (Exception e) {
            logger.error("JD_ORDER_GET_JD_ORDER_ERROR", e);
            throw new ServiceException("获取订单失败");
        }
        logger.info("JD_ORDER_GET_JD_ORDER_RESULT_" + res);
        JSONObject jsonObject = JSON.parseObject(res);
        if (jsonObject == null) {
            throw new ServiceException("获取订单失败");
        }
        if (!StringUtils.equals("true", String.valueOf(jsonObject.get("success")))) {
            logger.error("JD_ORDER_GET_JD_ORDER_ERROR_RESULT_" + res);
            throw new ServiceException("获取订单信息失败:" + String.valueOf(jsonObject.get("resultMessage")));
        }
        JSONObject ord = jsonObject.getJSONObject("result");
        if (ord == null) {
            logger.error("JD_ORDER_GET_ORDER_ERROR_ORDER_NOT_EXIST_ORDER_ID_" + jdOrderId);
            throw new ServiceException("订单不存在");
        }
        Integer type = ord.getInteger("type");
        if (type == null) {
            logger.error("JD_ORDER_GET_JD_ORDER_ERROR_UNKNOWN_ORDER_TYPE_RESULT_" + res);
            throw new ServiceException("未知的京东订单类型");
        }
        if (type.equals(JDOrderType.PARENT.getCode())) {
            //parent order
            JDResult<JdOrderParentOrder> jdResult = JSON.parseObject(res, new TypeReference<JDResult<JdOrderParentOrder>>() {
            });
            JdOrder order = new JdOrder();
            order.setParentOrder(jdResult.getResult());
            order.setType(JDOrderType.PARENT.getCode());
            return order;
        } else if (type.equals(JDOrderType.SUB.getCode())) {
            // sub order
            JDResult<JDOrderSubOrder> jdResult = JSON.parseObject(res, new TypeReference<JDResult<JDOrderSubOrder>>() {
            });
            JdOrder order = new JdOrder();
            order.setSubOrder(jdResult.getResult());
            order.setType(JDOrderType.SUB.getCode());
            return order;
        } else {
            logger.error("JD_ORDER_GET_JD_ORDER_ERROR_UNKNOWN_ORDER_TYPE_RESULT_" + res);
            throw new ServiceException("未知的订单类型");
        }
    }


}
