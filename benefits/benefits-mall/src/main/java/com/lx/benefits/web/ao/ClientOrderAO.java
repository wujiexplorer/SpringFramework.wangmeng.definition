package com.lx.benefits.web.ao;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.dto.yianapi.client.*;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.ent.ClientInfo;
import com.lx.benefits.bean.entity.ent.ClientOrder;
import com.lx.benefits.bean.entity.ent.ClientOrderItem;
import com.lx.benefits.bean.entity.memunioninfo.MemUnionInfo;
import com.lx.benefits.bean.enums.MemberUnionType;
import com.lx.benefits.bean.enums.yianapi.enums.ClientOrderStatus;
import com.lx.benefits.bean.enums.yianapi.enums.ClientPointPayType;
import com.lx.benefits.bean.enums.yianapi.util.ClientSignUtil;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.yianapi.ClientInfoService;
import com.lx.benefits.service.yianapi.ClientOrderService;
import com.lx.benefits.service.yianapi.UnionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lidongri on 2018/12/3.
 */
@Service
public class ClientOrderAO {

    @Resource
    private UnionInfoService unionInfoService;
    @Resource
    private ClientOrderService clientOrderService;
    @Resource
    private ClientInfoService clientInfoService;
    @Resource
    private EmployeeUserInfoService employeeUserInfoService;
    @Resource
    private EmployeeCreditInfoService employeeCreditInfoService;

    public ClientResult submit(ClientOrderReq req, String token) {

        ClientInfo clientInfoQ = new ClientInfo();
        clientInfoQ.setToken(token);
        ResultInfo<ClientInfo> resultInfo = clientInfoService.validToken(token);
        if (!resultInfo.isSuccess()) throw new MobileException(resultInfo.getMsg().getMessage());

        ClientInfo clientInfo = resultInfo.getData();
        String sign = req.getSign();

        String signed;
        try {
            signed = ClientSignUtil.getSign(req, clientInfo.getClientSecret());
        } catch (Exception e) {
            throw new MobileException("sign error");
        }
        if (!sign.equals(signed)) throw new MobileException("sign error");

        MemUnionInfo unionInfoQuery = new MemUnionInfo();
        unionInfoQuery.setEe_no(req.getEe_no());
        unionInfoQuery.setOrg_code(req.getOrg_code());
        unionInfoQuery.setType(MemberUnionType.YIAN.code);

        MemUnionInfo unionInfo = unionInfoService.queryByUnionInfo(unionInfoQuery);
        if (unionInfo == null) throw new MobileException("union user info error");

        EmployeeInfoDto memberInfo = employeeUserInfoService.findByEmployeeId(unionInfo.getMem_id(),false);
        if (memberInfo == null) throw new MobileException("user info error");

        ClientOrder orderQuery = new ClientOrder();
        orderQuery.setOid(req.getOid());
        Integer count = clientOrderService.queryByObjectCount(orderQuery);
        if (count != null && count > 0) throw new MobileException("oid duplicate");

        Date cur = new Date();

        List<ClientOrderItem> items = buildItems(req, cur);
        ClientOrder clientOrder = buildOrder(req, memberInfo, cur);

        ResultInfo<ClientOrder> result = clientOrderService.submit(clientOrder, items);
        if (!result.isSuccess() && result.getMsg() != null && result.getMsg().getCode() == 98001) {
            throw new MobileException("general point not enough");
        }
        if (!result.isSuccess()) {
            throw new MobileException("submit order error ");
        }
        ClientOrderResp res = new ClientOrderResp();
        res.setNumber(result.getData().getCode().toString());
        res.setOid(result.getData().getOid());
        return new ClientResult("create_order_with_ex_num", "create order with external order number successfully", true, res);
    }


    public ClientResult update(ClientOrderUpdateReq req, String token) {

        ClientInfo clientInfoQ = new ClientInfo();
        clientInfoQ.setToken(token);
        ResultInfo<ClientInfo> resultInfo = clientInfoService.validToken(token);
        if (!resultInfo.isSuccess()) throw new MobileException(resultInfo.getMsg().getMessage());

        ClientInfo clientInfo = resultInfo.getData();
        String sign = req.getSign();

        String signed;
        try {
            signed = ClientSignUtil.getSign(req, clientInfo.getClientSecret());
        } catch (Exception e) {
            throw new MobileException("sign error");
        }

        if (!sign.equals(signed)) throw new MobileException("sign error");

        ClientOrder orderQuery = new ClientOrder();
        orderQuery.setOid(req.getOid());
        ClientOrder clientOrder = clientOrderService.queryUniqueByObject(orderQuery);
        if (clientOrder == null) throw new MobileException("order not exist");

        clientOrder.setRemark(req.getRemark().trim());
        clientOrder.setUpdateTime(new Date());
        clientOrderService.updateById(clientOrder);

        ClientOrderUpdateResp resp = new ClientOrderUpdateResp();
        resp.setOid(clientOrder.getOid());
        resp.setOrder_number(clientOrder.getCode().toString());
        resp.setRemark(clientOrder.getRemark());

        return new ClientResult("update_order_with_ex_num", "update order with external order number successfully", true, resp);
    }

    public ClientResult detail(ClientOrderBaseReq req, String token) {

        ClientInfo clientInfoQ = new ClientInfo();
        clientInfoQ.setToken(token);
        ResultInfo<ClientInfo> resultInfo = clientInfoService.validToken(token);
        if (!resultInfo.isSuccess()) throw new MobileException(resultInfo.getMsg().getMessage());

        ClientInfo clientInfo = resultInfo.getData();
        String sign = req.getSign();

        String signed;
        try {
            signed = ClientSignUtil.getSign(req, clientInfo.getClientSecret());
        } catch (Exception e) {
            throw new MobileException("sign error");
        }

        if (!sign.equals(signed)) throw new MobileException("sign error");

        ClientOrder orderQuery = new ClientOrder();
        orderQuery.setOid(req.getOid());
        ClientOrder clientOrder = clientOrderService.queryUniqueByObject(orderQuery);
        if (clientOrder == null) throw new MobileException("order not exist");

        ClientOrderDetailResp resp = new ClientOrderDetailResp();
        resp.setStatus(clientOrder.getStatus());
        resp.setOrder_number(clientOrder.getCode().toString());
        resp.setRemark(clientOrder.getRemark());

        return new ClientResult("query_order_with_ex_num", "query order with external order number successfully", true, resp);
    }

    public ClientResult cancel(ClientOrderBaseReq req, String token) {

        ClientInfo clientInfoQ = new ClientInfo();
        clientInfoQ.setToken(token);
        ResultInfo<ClientInfo> resultInfo = clientInfoService.validToken(token);
        if (!resultInfo.isSuccess()) throw new MobileException(resultInfo.getMsg().getMessage());

        ClientInfo clientInfo = resultInfo.getData();
        String sign = req.getSign();

        String signed;
        try {
            signed = ClientSignUtil.getSign(req, clientInfo.getClientSecret());
        } catch (Exception e) {
            throw new MobileException("sign error");
        }

        if (!sign.equals(signed)) throw new MobileException("sign error");

        ClientOrder orderQuery = new ClientOrder();
        orderQuery.setOid(req.getOid());
        ClientOrder clientOrder = clientOrderService.queryUniqueByObject(orderQuery);
        if (clientOrder == null) throw new MobileException("order not exist");

        if (clientOrder.getStatus() == ClientOrderStatus.CANCELED.getCode()) {
            throw new MobileException("order already canceled");
        }

        ResultInfo cr = clientOrderService.cancel(clientOrder);
        if (!cr.isSuccess() && cr.getMsg() != null && cr.getMsg().getCode() != null && cr.getMsg().getCode() == 98002) {
            throw new MobileException("point order not find");
        }
        if (!cr.isSuccess() && cr.getMsg() != null && cr.getMsg().getCode() != null && cr.getMsg().getCode() == 98003) {
            throw new MobileException("point refund more than used");
        }
        if (!cr.isSuccess() && cr.getMsg() != null && cr.getMsg().getCode() != null && cr.getMsg().getCode() == 98000) {
            throw new MobileException("please retry later");
        }
        if (!cr.isSuccess()) throw new MobileException("cancel failed");
        ClientOrderCancelResp resp = new ClientOrderCancelResp();
        resp.setOid(clientOrder.getOid());

        return new ClientResult("cancel_without_refund", "order cancelled and refund successfully", true, resp);
    }

    public ClientResult point(ClientUserPointReq req, String token) {

        ClientInfo clientInfoQ = new ClientInfo();
        clientInfoQ.setToken(token);
        ResultInfo<ClientInfo> resultInfo = clientInfoService.validToken(token);
        if (!resultInfo.isSuccess()) throw new MobileException(resultInfo.getMsg().getMessage());

        ClientInfo clientInfo = resultInfo.getData();
        String sign = req.getSign();

        String signed;
        try {
            signed = ClientSignUtil.getSign(req, clientInfo.getClientSecret());
        } catch (Exception e) {
            throw new MobileException("sign error");
        }
        if (!sign.equals(signed)) throw new MobileException("sign error");

        MemUnionInfo unionInfoQuery = new MemUnionInfo();
        unionInfoQuery.setEe_no(req.getEe_no());
        unionInfoQuery.setOrg_code(req.getOrg_code());
        unionInfoQuery.setType(MemberUnionType.YIAN.code);

        MemUnionInfo unionInfo = unionInfoService.queryByUnionInfo(unionInfoQuery);
        if (unionInfo == null) throw new MobileException("union user info error");

        EmployeeInfoDto memberInfo = employeeUserInfoService.findByEmployeeId(unionInfo.getMem_id(),false);
        if (memberInfo == null) throw new MobileException("user info error");


        EmployeeCreditInfo dto = new EmployeeCreditInfo();
        dto.setEmployeeId(memberInfo.getEmployeeId());
        ResultInfo<EmployeeCreditInfo> creditRes = employeeCreditInfoService.info(dto);

        ClientUserPointResp res = new ClientUserPointResp();
        res.setEe_no(unionInfo.getEe_no());
        res.setOrg_code(unionInfo.getOrg_code());
        res.setGeneral_point(creditRes.getData() == null ? null : creditRes.getData().getCredit());
        res.setExclusive_point(0);


        return new ClientResult("view_account_detail", " view account detail successfully", true, res);
    }

    private ClientOrder buildOrder(ClientOrderReq req, EmployeeInfoDto memberInfo, Date cur) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setOid(req.getOid());
        clientOrder.setEeNo(req.getEe_no());
        clientOrder.setOrgCode(req.getOrg_code());
        clientOrder.setPrice(req.getPrice());
        clientOrder.setShippingFee(req.getShipping_fee());

        clientOrder.setMemberId(memberInfo.getEmployeeId());
        clientOrder.setStatus(ClientOrderStatus.PAYING.getCode());

        clientOrder.setPayChannels(ClientPointPayType.getPayValue(req.getPay_channels()));

        if (clientOrder.getPayChannels() == ClientPointPayType.exclusive_point.getCode()) {
            throw new MobileException("exclusive point not enough");
        }

        clientOrder.setCreateTime(cur);
        clientOrder.setUpdateTime(cur);
        return clientOrder;
    }

    private List<ClientOrderItem> buildItems(ClientOrderReq req, Date cur) {
        List<ClientOrderItem> items = new ArrayList<>();
        for (ClientOrderItemReq itemReq : req.getSku_items()) {
            ClientOrderItem item = new ClientOrderItem();
            item.setOid(req.getOid());
            item.setPartnerSku(itemReq.getPartner_sku());
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(itemReq.getUnit_price());
            item.setCreateTime(cur);
            item.setUpdateTime(cur);
            items.add(item);
        }
        return items;
    }
}
