package com.lx.benefits.service.yianapi.impl;

import com.apollo.starter.web.utils.PageResult;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.ent.ClientOrder;
import com.lx.benefits.bean.entity.ent.ClientOrderCondition;
import com.lx.benefits.bean.entity.ent.ClientOrderItem;
import com.lx.benefits.bean.enums.OrderCodeType;
import com.lx.benefits.bean.enums.yianapi.enums.ClientOrderStatus;
import com.lx.benefits.mapper.ent.ClientOrderItemMapper;
import com.lx.benefits.mapper.ent.ClientOrderMapper;
import com.lx.benefits.service.yianapi.ClientOrderService;
import com.lx.benefits.service.yianapi.IClientOrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    @Resource
    private OrderCodeGeneratorService orderCodeGeneratorService;
    @Resource
    private EmployeeCreditManagementService  employeeCreditManagementService;
    @Resource
    private IClientOrderManagementService clientOrderManagementService;

    @Autowired
    private ClientOrderMapper clientOrderMapper;

    @Autowired
    private ClientOrderItemMapper clientOrderItemMapper;

    @Override
    public ResultInfo<ClientOrder> submit(ClientOrder order, List<ClientOrderItem> itemList) {
        ResultInfo<ClientOrder> result = new ResultInfo<>();
        ResultInfo<ClientOrder> resultInfo = create(order, itemList);
        result.setData(resultInfo.getData());
        result.setMsg(resultInfo.getMsg());
        result.setSuccess(resultInfo.success);
        return result;
    }

    @Override
    public ResultInfo<Integer> cancel(ClientOrder order) {
        ResultInfo<Integer> result = new ResultInfo<>();
        ResultInfo<Integer> resultInfo = clientOrderManagementService.cancel(order);
        result.setData(resultInfo.getData());
        result.setMsg(resultInfo.getMsg());
        result.setSuccess(resultInfo.success);
        return result;
    }

    @Override
    public ClientOrder queryUniqueByObject(ClientOrder orderQuery) {
        return clientOrderMapper.queryUniqueByObject(orderQuery);
    }

    @Override
    public Integer queryByObjectCount(ClientOrder orderQuery) {
        return clientOrderMapper.queryByObjectCount(orderQuery);
    }

    @Override
    public Integer updateById(ClientOrder orderQuery) {
        return clientOrderMapper.updateById(orderQuery);
    }

    @Override
    public ClientOrder queryById(Long id) {
        return clientOrderMapper.queryById(id);
    }

    @Override
    public ClientOrder insert(ClientOrder orderQuery) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
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

        Long orderId = clientOrderMapper.insert(order);
        resultInfo.setData(order);
        for (ClientOrderItem item : items) {
            item.setClientOrderId(order.getId());
            item.setClientOrderCode(orderCode);
            item.setOid(order.getOid());
            item.setCreateTime(cur);
            item.setUpdateTime(cur);
            clientOrderItemMapper.insert(item);
        }

        //扣减第企业用户积分：怡安用户积分
        EmployeeCreditInfo creditDto = new EmployeeCreditInfo();
        creditDto.setEmployeeId(order.getMemberId());
        creditDto.setFinanceNo(orderCode.toString());
        creditDto.setCredit(totalReal);
        ResultInfo creditRes = employeeCreditManagementService.use(creditDto);
        if (!creditRes.isSuccess()) {
            return creditRes;
        }

        order.setStatus(ClientOrderStatus.FINISHED.getCode());
        clientOrderMapper.updateById(order);

        return new ResultInfo<>(order);
    }

    @Override
	public PageResult<ClientOrder> getOrderListByExample(Integer pageNo, Integer pageSize,
			ClientOrderCondition clientOrderCondition) {
		int count = clientOrderMapper.countByExample(clientOrderCondition);
		if (count == 0) {
			return PageResult.EMPTY;
		}
		if (pageSize < 1) {
			pageSize = 10;
		} else if (pageSize > 50) {
			pageSize = 50;
		}
		int totalPage = (count - 1) / pageSize + 1;
		if (pageNo < 1) {
			pageNo = 1;
		} else if (pageNo > totalPage) {
			pageNo = totalPage;
		}
		clientOrderCondition.setLimit(pageSize);
		clientOrderCondition.setOffset((pageNo - 1) * pageSize);
		return PageResult.wrapPageResult(clientOrderMapper.selectByExample(clientOrderCondition), count, pageSize);
	}

}
