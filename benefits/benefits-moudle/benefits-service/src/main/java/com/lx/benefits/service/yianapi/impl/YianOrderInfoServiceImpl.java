package com.lx.benefits.service.yianapi.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.constants.DAOConstant;
import com.lx.benefits.bean.dto.yianapi.YiAnOrderDeliveryExcelDTO;
import com.lx.benefits.bean.dto.yianapi.YiAnOrderExcelDTO;
import com.lx.benefits.bean.dto.yianapi.YianImportOrderDto;
import com.lx.benefits.bean.dto.yx.utils.ListUtil;
import com.lx.benefits.bean.entity.ent.YianOrderInfo;
import com.lx.benefits.bean.entity.ent.YianOrderItem;
import com.lx.benefits.bean.exception.ServiceException;
import com.lx.benefits.bean.util.EasyExcelUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.mapper.ent.YianOrderInfoMapper;
import com.lx.benefits.mapper.ent.YianOrderItemMapper;
import com.lx.benefits.mapper.express.ExpressInfoMapper;
import com.lx.benefits.mapper.express.entity.ExpressInfo;
import com.lx.benefits.service.yianapi.YianOrderInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class YianOrderInfoServiceImpl  implements YianOrderInfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private YianOrderInfoMapper yianOrderInfoMapper;

    @Autowired
    private YianOrderItemMapper yianOrderItemMapper;

    @Autowired
    private ExpressInfoMapper expressInfoMapper;

    @Override
    public ResultInfo<YianOrderInfo> queryUniqueByObject(YianOrderInfo infoq) {
        return new ResultInfo<>(yianOrderInfoMapper.queryUniqueByObject(infoq));
    }

    @Override
    public JSONObject importOrder(YianImportOrderDto req) {
        if (null == req) {
            return Response.fail("导入文件参数位空");
        }
        String filePath = req.getFilePath();
        if (null == filePath || StringUtil.isEmpty(filePath)) {
            return Response.fail("文件地址位空");
        }
        try {
            UrlResource resource = new UrlResource(filePath);
            List<Object> list = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), YiAnOrderExcelDTO.class, ExcelTypeEnum.XLSX);
            int line = 2;
            StringBuilder builder = new StringBuilder("");
            List<YiAnOrderExcelDTO> orders = new ArrayList<>();
            for(Object obj : list) {
                YiAnOrderExcelDTO dto = (YiAnOrderExcelDTO) obj;
                if (dto == null) {
                    builder.append("第"+(line)+"数据为空");
                }
                if (StringUtils.isBlank(dto.getOrderCode())) {
                    builder.append("第"+(line)+"行订单号为空");
                }
                if (StringUtils.isBlank(dto.getOrgCode())) {
                    builder.append("第"+(line)+"行公司缩写为空");
                }
                if (StringUtils.isBlank(dto.getEeNo())) {
                    builder.append("第"+(line)+"行员工工号为空");
                }
                if (StringUtils.isBlank(dto.getItemName())) {
                    builder.append("第"+(line)+"产品名称为空");
                }
                if (StringUtils.isBlank(dto.getCount())) {
                    builder.append("第"+(line)+"行数量为空");
                }
                if (StringUtils.isBlank(dto.getPrice())) {
                    builder.append("第"+(line)+"行单价为空");
                }
                if ( StringUtils.isBlank(dto.getTotal())) {
                    builder.append("第"+(line)+"行销售价为空");
                }
                line++;
                orders.add(dto);
            }
            if(builder.length()>0){
                return Response.fail(builder.toString());
            }
            return doImportOrder(orders);
        } catch (MalformedURLException e) {
            throw new RuntimeException("导入订单文件失败");
        } catch (IOException e) {
            throw new RuntimeException("导入订单文件失败");
        }
    }

    @Override
    public JSONObject importOrderDelivery(YianImportOrderDto req) {
        if (null == req) {
            return Response.fail("导入文件参数位空");
        }
        String filePath = req.getFilePath();
        if (null == filePath || StringUtil.isEmpty(filePath)) {
            return Response.fail("文件地址位空");
        }
        try {
            UrlResource resource = new UrlResource(filePath);
            List<Object> list = EasyExcelUtil.readExcelWithModel(resource.getInputStream(), YiAnOrderDeliveryExcelDTO.class, ExcelTypeEnum.XLSX);
            if(CollectionUtils.isEmpty(list)) {
                throw new ServiceException("记录为空");
            }
            StringBuilder builder = new StringBuilder("");
            int line = 2;
            List<String> codes = new ArrayList<>(list.size());
            Set<String> expressCompanys = new HashSet<>();
            List<YiAnOrderDeliveryExcelDTO> orders = new ArrayList<>();
            for(Object obj : list) {
                YiAnOrderDeliveryExcelDTO dto = (YiAnOrderDeliveryExcelDTO) obj;
                if (StringUtils.isBlank(dto.getOrderCode())){
                    builder.append("第").append(line).append("行数据有误,");
                } else if ((dto.getOrderType() == null ||
                        (dto.getOrderType() != 1 && dto.getOrderType() != 2))) {
                    builder.append("第").append(line).append("行数据有误,");
                } else if (dto.getOrderType() == 1 && (StringUtils.isBlank(dto.getDeliveryCode()) ||
                        StringUtils.isBlank(dto.getDeliveryCompany()))) {
                    builder.append("第").append(line).append("行数据有误,");
                } else if (dto.getOrderType() == 2 && (StringUtils.isBlank(dto.getVirtualCardNo()) || StringUtils.isBlank(dto.getVirtualPassword()))) {
                    builder.append("第").append(line).append("行数据有误,");
                }

                if(StringUtils.isNotBlank(dto.getOrderCode())){
                    codes.add(dto.getOrderCode().trim());
                }
                if (dto.getOrderType() == 1 && StringUtils.isNotBlank(dto.getDeliveryCompany())) {
                    expressCompanys.add(dto.getDeliveryCompany());
                }
                line++;
                orders.add(dto);
            }


            List<YianOrderInfo> ordersFromDb = Collections.EMPTY_LIST;
            if (!codes.isEmpty()) {
                List<String> codesForQuery = codes.stream().map(s -> "'" + s + "'").collect(Collectors.toList());
                Map<String, Object> params = new HashMap<>();
                params.put("orderCodes", codesForQuery);
                ordersFromDb =yianOrderInfoMapper.queryByParam(params);
            }
            List<String> orderCodesFromDB = ordersFromDb.stream().map(YianOrderInfo::getOrderCode).collect(Collectors.toList());

            List<String> notExistOrderCodes = codes.stream().filter(e -> !orderCodesFromDB.contains(e)).collect(Collectors.toList());

            if (!notExistOrderCodes.isEmpty()) {
                builder.append("以下订单不存在:").append(notExistOrderCodes.toString());
            }

            List<ExpressInfo> expressInfos = Collections.EMPTY_LIST;
            if(!expressCompanys.isEmpty()) {
                List<String> exressForQ = expressCompanys.stream().map(e -> "'" + e + "'").collect(Collectors.toList());
                Map<String, Object> params2 = new HashMap<>();
                params2.put("nameList",exressForQ);
                expressInfos = expressInfoMapper.queryByParam(params2);
            }
            List<String> expressCompaynFromDB = expressInfos.stream().map(ExpressInfo::getName).collect(Collectors.toList());

            List<String> notExistExpressCompanys = expressCompanys.stream().filter(e -> !expressCompaynFromDB.contains(e)).collect(Collectors.toList());

            if (!notExistExpressCompanys.isEmpty()) {
                builder.append("以下物流公司不存在:").append(notExistExpressCompanys.toString());
            }

            Map<String, ExpressInfo> expressInfoMap = expressInfos.stream().collect(Collectors.toMap(ExpressInfo::getName, Function.identity()));

            if (builder.length() > 0) {
                return  Response.fail(builder.toString());
            } else {
                for (YianOrderInfo ii : ordersFromDb) {
                    for (YiAnOrderDeliveryExcelDTO d : orders) {
                        if (ii.getOrderCode().equals(d.getOrderCode().trim())) {
                            ii.setOrderStatus(2);
                            ii.setOrderType(d.getOrderType());
                            if (d.getOrderType() == 1) {
                                ii.setDeliveryCompany(d.getDeliveryCompany().trim());
                                ii.setDeliveryCode(d.getDeliveryCode());
                                ii.setDeliveryCompanyCode(expressInfoMap.get(ii.getDeliveryCompany()).getCode());
                            } else if (d.getOrderType() == 2) {
                                ii.setVirtualCardNo(d.getVirtualCardNo());
                                ii.setVirtualPassword(d.getVirtualPassword());
                                ii.setVirtualIntro(d.getVirtualIntro());
                            }
                        }
                    }
                }
                int  update = yianOrderInfoMapper.batchDelivery(ordersFromDb);
                if (update > 0) {
                    return Response.succ("成功");
                } else {
                    return Response.succ("失败");
                }
            }
        } catch (MalformedURLException e1) {
            throw new RuntimeException("导入文件失败");
        } catch (IOException e1) {
            throw new RuntimeException("导入文件失败");
        }
    }

    @Override
    public Integer selectCount(Map<String, Object> query) {
        return yianOrderInfoMapper.selectCount(query);
    }

    @Override
    public List<YianOrderInfo> selectPageList(Map<String, Object> map) {
        return yianOrderInfoMapper.selectPageList(map);
    }

    public JSONObject doImportOrder(List<YiAnOrderExcelDTO> orders) {
        Date date = new Date();
        try {
            List<YianOrderInfo> yianOrderInfos = new ArrayList<>(orders.size());
            List<YianOrderItem> yianOrderItems = new ArrayList<>(orders.size());
            Map<String, BigDecimal> total = new HashMap<>();
            orders.forEach(o -> {
                YianOrderInfo info = new YianOrderInfo();
                info.setOrderCode(o.getOrderCode());
                info.setEeNo(o.getEeNo());
                info.setOrgCode(o.getOrgCode());
                info.setEeName(o.getEeName());
                info.setReceiverName(o.getReceiverName());
                info.setReceiverAddress(o.getAddress());
                info.setReceiverMobile(o.getReceiverMobile());
                info.setPostCode(o.getPostCode());
                info.setOrderStatus(1);
                info.setCreateTime(date);
                info.setUpdateTime(date);

                if (!yianOrderInfos.contains(info)) {
                    yianOrderInfos.add(info);
                }

                YianOrderItem item = new YianOrderItem();
                item.setOrderCode(o.getOrderCode());
                item.setSku(o.getSku());
                item.setItemName(o.getItemName());
                item.setCount(Integer.parseInt(o.getCount()));
                item.setPrice(Double.parseDouble(o.getPrice()));
                item.setTotal(Double.parseDouble(o.getTotal()));
                item.setSupName(o.getSupplierName());
                item.setCreateTime(date);
                yianOrderItems.add(item);

                BigDecimal t = total.get(o.getOrderCode());
                if (t == null) {
                    t = new BigDecimal("0");
                }
                t = t.add(new BigDecimal(item.getTotal().toString()));
                total.put(o.getOrderCode(), t);


            });

            List<String> orderCodes = new ArrayList<>(orders.size());
            for (YianOrderInfo info : yianOrderInfos) {
                BigDecimal b = total.get(info.getOrderCode());
                info.setTotal(b == null ? 0 : b.doubleValue());
                orderCodes.add("'" +info.getOrderCode()+"'");
            }

            Map<String, Object> params = new HashMap<>();
            params.put("orderCodes", orderCodes);
            List<YianOrderInfo> ordersForUpdate = yianOrderInfoMapper.queryByParam(params);

            List<String> orderCodesForUpdate = ordersForUpdate.stream().map(e-> "'"+ e.getOrderCode()+"'").collect(Collectors.toList());
            List<String> orderCodesForUpdateORI = ordersForUpdate.stream().map(e->  e.getOrderCode()).collect(Collectors.toList());
            List<YianOrderInfo> orderForAdd = yianOrderInfos.stream().filter(s -> !orderCodesForUpdateORI.contains(s.getOrderCode())).collect(Collectors.toList());

            Map<String,List<YianOrderItem>> yianItemMap =   yianOrderItems.stream().filter(e->orderCodesForUpdateORI.contains(e.getOrderCode())).collect(Collectors.groupingBy(YianOrderItem::getOrderCode));

            for (YianOrderInfo i : ordersForUpdate) {
                for (YianOrderInfo n : yianOrderInfos) {
                    if (i.getOrderCode().equals(n.getOrderCode())) {
                        i.setEeNo(n.getEeNo());
                        i.setEeName(n.getEeName());
                        i.setOrgCode(n.getOrgCode());
                        i.setReceiverMobile(n.getReceiverMobile());
                        i.setReceiverAddress(n.getReceiverAddress());
                        i.setReceiverName(n.getReceiverName());
                        i.setPostCode(n.getPostCode());
                        i.setUpdateTime(date);

                        List<YianOrderItem> items = yianItemMap.get(n.getOrderCode());
                        BigDecimal reCalTotal = new BigDecimal("0");
                        if(!CollectionUtils.isEmpty(items)){
                            for(YianOrderItem yi: items){
                                reCalTotal = reCalTotal.add(new BigDecimal(yi.getTotal().toString()));
                            }
                        }
                        i.setTotal(reCalTotal.doubleValue());
                        break;
                    }
                }
            }

            List<List<YianOrderInfo>> sepAdd = ListUtil.separateList(orderForAdd,200);
            List<List<YianOrderInfo>> sepUpdate = ListUtil.separateList(ordersForUpdate,200);
            List<List<YianOrderItem>> sepAddItem = ListUtil.separateList(yianOrderItems,200);

            //删除需要更新的的订单的商品行
            //更新订单
            if(!orderCodesForUpdate.isEmpty()){
                sepUpdate.forEach(e-> yianOrderInfoMapper.batchUpdate(e));
                Map<String, Object> params2 = new HashMap<>();
                params2.put("orderCodes",orderCodesForUpdate);
                yianOrderItemMapper.deleteByParam(params2);
            }
            // 新增订单
            if(!orderForAdd.isEmpty()){
                sepAdd.forEach(e-> yianOrderInfoMapper.batchInsert(e));
            }
            if(!yianOrderItems.isEmpty()){
                sepAddItem.forEach(e->yianOrderItemMapper.batchInsert(e));
            }
            String msg = "共计"+yianOrderInfos.size()+",新增"+orderForAdd.size()+",更新"+ordersForUpdate.size();
            return Response.succ(msg);

        } catch (Exception e) {
            logger.error("怡安订单导入异常{}",e.getMessage());
        }
        return Response.fail("导入异常");
    }
}
