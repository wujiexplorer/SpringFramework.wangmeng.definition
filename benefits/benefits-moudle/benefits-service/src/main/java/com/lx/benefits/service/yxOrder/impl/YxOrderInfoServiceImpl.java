package com.lx.benefits.service.yxOrder.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.FailInfo;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.yx.result.YXResult;
import com.lx.benefits.bean.dto.yxOrder.YxOrderInfoReq;
import com.lx.benefits.bean.dto.yxOrder.api.YXChannelOrderOut;
import com.lx.benefits.bean.dto.yxOrder.api.YXChannelOrderPackage;
import com.lx.benefits.bean.dto.yxOrder.api.YXOrderCancelResult;
import com.lx.benefits.bean.dto.yxOrder.enums.YXOrderPackageStatus;
import com.lx.benefits.bean.dto.yxOrder.enums.YXOrderStatus;
import com.lx.benefits.bean.dto.yxOrder.enums.YxOrderCancelStatus;
import com.lx.benefits.bean.entity.yxOrder.YxOrderInfo;
import com.lx.benefits.bean.entity.yxOrder.YxOrderInfoCancel;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.yxOrder.YxOrderInfoCancelMapper;
import com.lx.benefits.mapper.yxOrder.YxOrderInfoMapper;
import com.lx.benefits.service.yxOrder.IYXOrderService;
import com.lx.benefits.service.yxOrder.YxOrderInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/03/03
 * Time: 22:41
 */
@Service
public class YxOrderInfoServiceImpl implements YxOrderInfoService {

    @Autowired
    YxOrderInfoMapper orderInfoMapper;

    @Autowired
    IYXOrderService yxOrderService;

    @Autowired
    YxOrderInfoCancelMapper yxOrderCancelMapper;

    @Override
    public JSONObject getYxOrderList(YxOrderInfoReq record) {
        JSONObject jsonObject = new JSONObject();
        record.setPage(record.getPage() > 0 ? (record.getPage() - 1) * record.getPageSize() : 0);
        jsonObject.put("list", orderInfoMapper.getOrderList(record));
        jsonObject.put("count", orderInfoMapper.getOrderListCount(record));
        return jsonObject;
    }

    @Override
    public JSONObject getYxOrderCancel(String orderId) {
        YxOrderInfo order = orderInfoMapper.gerOrderByOrderCode(orderId);
        if (order == null) {
            return Response.fail("获取订单信息为空");
        }
        ResultInfo resultInfo = cancel(order.getSubOrderCode().toString(), "System", 1L);
        return resultInfo.success ? Response.succ() : Response.fail("订单取消失败");
    }

    @Override
    public JSONObject getYxOrderUpdate(String orderId) {
        YxOrderInfo order = orderInfoMapper.gerOrderByOrderCode(orderId);
        if (order == null) {
            return Response.fail("获取订单信息为空");
        }
        YXResult<YXChannelOrderOut> yxChannelOrderOutYXResult = yxOrderService.query(order.getSubOrderCode().toString());
        if (yxChannelOrderOutYXResult.success() && yxChannelOrderOutYXResult.getResult() != null) {
            addOrUpdateYXOrderInfo(yxChannelOrderOutYXResult.getResult());
            return Response.succ();
        }
        return Response.fail(yxChannelOrderOutYXResult.getMsg());
    }

    public void addOrUpdateYXOrderInfo(YXChannelOrderOut orderOut) {
        YxOrderInfo yxOrderInfo = new YxOrderInfo();
        YxOrderInfo info = orderInfoMapper.gerOrderByOrderCode(orderOut.getOrderId());
        if (info != null) {
            yxOrderInfo.setId(info.getId());
        } else {
            yxOrderInfo.setCreateTime(new Date());
        }
        yxOrderInfo.setUpdateTime(new Date());
        yxOrderInfo.setYxOrderStatus(orderOut.getOrderStatus());
        //yxOrderInfo.setOrderTime(yxSubOrder.getCreateTime());
        //yxOrderInfo.setRealPrice(yxSubOrder.getOriginalTotal());
        yxOrderInfo.setExpFee(orderOut.getExpFee());
        //yxOrderInfo.setUserId(yxSubOrder.getMemberId());
        //yxOrderInfo.setUserName(yxSubOrder.getMemberId().toString());
        //yxOrderInfo.setCreateUser(yxSubOrder.getMemberId().toString());
        //yxOrderInfo.setUpdateUser(yxSubOrder.getMemberId().toString());

        List<YXChannelOrderPackage> yxChannelOrderPackageList = orderOut.getOrderPackages();
        StringBuilder builder = new StringBuilder("");
        if (!CollectionUtils.isEmpty(yxChannelOrderPackageList)) {
            for (int i = 0; i < yxChannelOrderPackageList.size(); i++) {
                YXChannelOrderPackage orderPackage = yxChannelOrderPackageList.get(i);
                StringBuilder b = new StringBuilder();
                b.append(orderPackage.getPackageId());
                if (StringUtils.isNotBlank(orderPackage.getPackageStatus())) {
                    b.append(" ").append(YXOrderPackageStatus.getByName(orderPackage.getPackageStatus()));
                }
                if (StringUtils.isNotBlank(orderPackage.getExpressCompany())) {
                    b.append(" ").append(orderPackage.getExpressCompany());
                }
                if (StringUtils.isNotBlank(orderPackage.getExpressNo())) {
                    b.append(" ").append(orderPackage.getExpressNo());
                }
                builder.append(b.toString());
                if (i < yxChannelOrderPackageList.size() - 1) {
                    builder.append(",");
                }
            }
        }
        yxOrderInfo.setYxPackageId(builder.toString());
        if (yxOrderInfo.getId() != null) {
            orderInfoMapper.update(yxOrderInfo);
        } else {
            orderInfoMapper.insert(yxOrderInfo);
        }
    }

    public ResultInfo cancel(String orderCode, String operateUserName, Long memberId) {
        try {
            YXOrderCancelResult yxOrderCancelResult = yxOrderService.cancel(orderCode);
            ResultInfo result = processCancelResult(orderCode, operateUserName, memberId, yxOrderCancelResult, 1);
            return result;
        } catch (Exception e) {
            return new ResultInfo("取消订单失败,系统异常" + e.getMessage());
        }
    }

    public ResultInfo processCancelResult(String orderCode, String operateUserName, Long memberId, YXOrderCancelResult yxOrderCancelResult, int cancelType) {
        addCancelInfo(orderCode, operateUserName, memberId, yxOrderCancelResult, cancelType);
        return getYxOrderCancelResult(orderCode, memberId, yxOrderCancelResult);
    }

    private void addCancelInfo(String orderCode, String operateUserName, Long memberId, YXOrderCancelResult yxOrderCancelResult, Integer type) {
        YxOrderInfoCancel yxOrderCancel = new YxOrderInfoCancel();
        yxOrderCancel.setSubOrderCode(orderCode);
        yxOrderCancel.setCancelStatus(String.valueOf(yxOrderCancelResult.getCancelStatus()));
        yxOrderCancel.setRejectReason(yxOrderCancelResult.getRejectReason());
        yxOrderCancel.setCreateTime(new Date());
        yxOrderCancel.setType(type);
        yxOrderCancel.setMemberId(memberId);
        yxOrderCancel.setCreateUser(operateUserName);
        yxOrderCancelMapper.insert(yxOrderCancel);
    }


    private ResultInfo getYxOrderCancelResult(String orderCode, Long memberId, YXOrderCancelResult yxOrderCancelResult) {

        if (yxOrderCancelResult.getCancelStatus() == null) {
            return new ResultInfo(new FailInfo("取消失败,严选返回未知的取消状态"));
        }
        if (yxOrderCancelResult.getCancelStatus() == YxOrderCancelStatus.NO.ordinal()) {
            return new ResultInfo(new FailInfo("严选不允许取消订单,原因:" + String.valueOf(yxOrderCancelResult.getRejectReason())));
        } else if (yxOrderCancelResult.getCancelStatus() == YxOrderCancelStatus.YES.ordinal()) {
            return doCancelOurOrder(orderCode, memberId);
        } else if (yxOrderCancelResult.getCancelStatus() == YxOrderCancelStatus.AUDITING.ordinal()) {
            return new ResultInfo(new FailInfo("严选审核中,请稍后查看", 100));
        }
        return new ResultInfo(new FailInfo("取消失败,严选返回未知的取消状态"));
    }

    private ResultInfo doCancelOurOrder(String orderCode, Long memberId) {
        try {
            YxOrderInfo yxOrderInfo = orderInfoMapper.gerOrderByOrderCode(orderCode);
            String orderStatus = yxOrderInfo.getYxOrderStatus();
            if (!YXOrderStatus.isCanceled(orderStatus)) {
                return new ResultInfo(new FailInfo("取消失败,查询严选订单状态不为已取消"));
            }
            yxOrderInfo.setYxOrderStatus(YXOrderStatus.USER_CANCEL.getDesc());
            yxOrderInfo.setUpdateTime(new Date());
            orderInfoMapper.update(yxOrderInfo);
            // 这里需要取消我方订单
            return new ResultInfo();
        } catch (Exception e) {
            return new ResultInfo(new FailInfo("取消我方订单异常" + e.getMessage()));
        }
    }

}
