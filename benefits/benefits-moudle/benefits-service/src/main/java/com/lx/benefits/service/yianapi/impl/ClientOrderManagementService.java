package com.lx.benefits.service.yianapi.impl;


import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.ent.ClientOrder;
import com.lx.benefits.bean.entity.ent.ClientOrderItem;
import com.lx.benefits.bean.enums.OrderCodeType;
import com.lx.benefits.bean.enums.yianapi.enums.ClientOrderStatus;
import com.lx.benefits.service.yianapi.*;
import com.lx.benefits.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lidongri on 2018/12/2.
 */

@Service
public class ClientOrderManagementService implements IClientOrderManagementService {

    private static String cancel_order_key = "cancel-client-order-key-12";
    @Resource
    private ClientOrderService clientOrderService;
    //private IClientOrderService clientOrderService;
    @Resource
    private IClientOrderItemService clientOrderItemService;
//    @Autowired
//    private IMemberInfoService memberInfoService;
//    @Autowired
//    private IPointDetailService pointDetailService;
//    @Autowired
//    private IUnionInfoService unionInfoService;
    @Resource
    private IOrderCodeGeneratorService orderCodeGeneratorService;
    @Autowired
    private RedisUtils cacheUtil;

    @Resource
    private IEmployeeCreditManagementService employeeCreditManagementService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 生成第三方平台 积分订单
     * <p>
     * 1生成平台订单信息
     * <p>
     * 2扣减积分
     *
     * @param order
     * @param items
     * @return
     */
    @Override
    @Transactional
    public ResultInfo<ClientOrder> create(ClientOrder order, List<ClientOrderItem> items) {
        ResultInfo<ClientOrder> resultInfo = new ResultInfo<>();
        Date cur = new Date();
        Long orderCode = orderCodeGeneratorService.generate(OrderCodeType.CLIENT_ORDER);
        order.setCode(orderCode);

        BigDecimal totalReal = new BigDecimal(order.getPrice().toString());
        if (order.getShippingFee() != null || order.getShippingFee() > 0D) {
            totalReal = totalReal.add(new BigDecimal(order.getShippingFee().toString())).setScale(2);
        }
        order.setRealPrice(totalReal.doubleValue());
        order.setCreateTime(cur);
        order.setUpdateTime(cur);
        order.setStatus(ClientOrderStatus.PAYING.getCode());

        order = clientOrderService.insert(order);
        resultInfo.setData(order);

        for (ClientOrderItem item : items) {
            item.setClientOrderId(order.getId());
            item.setClientOrderCode(orderCode);
            item.setCreateTime(cur);
            item.setOid(order.getOid());
            item.setUpdateTime(cur);
            clientOrderItemService.insert(item);
        }

        //扣减第企业用户积分：怡安用户积分
        EmployeeCreditInfo creditDto = new EmployeeCreditInfo();
        creditDto.setEmployeeId(order.getMemberId());
        creditDto.setCredit(totalReal);
        creditDto.setFinanceNo(orderCode.toString());
        ResultInfo creditRes = employeeCreditManagementService.use(creditDto);
        if (!creditRes.isSuccess()) {
            return creditRes;
        }
        order.setStatus(ClientOrderStatus.FINISHED.getCode());
        clientOrderService.updateById(order);

        return new ResultInfo<>(order);
    }

    @Override
    public ResultInfo<Integer> cancel(ClientOrder order) {

        ClientOrder clientOrder = clientOrderService.queryById(order.getId());

        if (clientOrder.getStatus() == ClientOrderStatus.CANCELED.getCode()) {
            return new ResultInfo<>();
        }
        if (clientOrder.getStatus() == ClientOrderStatus.PAYING.getCode()) {
            clientOrder.setStatus(ClientOrderStatus.CANCELED.getCode());
            clientOrder.setUpdateTime(new Date());
            clientOrderService.updateById(clientOrder);
            return new ResultInfo<>();
        }

        String lock = cancel_order_key + order.getCode().toString();
        if (!cacheUtil.lock(lock,5)) {
            throw new ServiceException(" please try latter", 5500);
        }
        try {
            EmployeeCreditInfo creditDto = new EmployeeCreditInfo();
            creditDto.setEmployeeId(clientOrder.getMemberId());
            creditDto.setFinanceNo(clientOrder.getCode().toString());
            ResultInfo<CreditOperateInfo> opRes = employeeCreditManagementService.getCreditOperateInfo(creditDto);
            if (opRes.getData() == null) {
                logger.error("point record not find");
                throw new ServiceException("point record not find");
            }
            creditDto.setCredit(opRes.getData().getCredit());
            ResultInfo paybackRes = employeeCreditManagementService.payback(creditDto);
            if (!paybackRes.isSuccess())
                return  paybackRes;

            clientOrder.setStatus(ClientOrderStatus.CANCELED.getCode());
            clientOrder.setUpdateTime(new Date());
            clientOrderService.updateById(clientOrder);
            return paybackRes;

        } finally {
            cacheUtil.unLock(cancel_order_key + order.getCode().toString());
        }
    }

    @Override
    public ResultInfo<ClientOrder> detail(ClientOrder order) {
        return null;
    }
}
