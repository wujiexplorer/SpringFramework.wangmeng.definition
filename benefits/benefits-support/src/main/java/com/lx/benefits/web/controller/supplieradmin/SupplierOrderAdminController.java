package com.lx.benefits.web.controller.supplieradmin;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.enums.base.DatePatternEnum;
import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.base.DateUtil;
import com.apollo.common.utils.bean.BeanUtil;
import com.apollo.common.utils.bean.CollectionExtUtil;
import com.apollo.common.utils.export.StringTemplateExcelUtil;
import com.apollo.starter.web.utils.PageResult;
import com.apollo.starter.web.utils.Result;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.lx.benefits.bean.dto.deliverinfo.ExpressResult;
import com.lx.benefits.bean.dto.deliverinfo.PackageLocationInfo;
import com.lx.benefits.bean.entity.order.OrderShipLogistics;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.enums.PayChannelEnum;
import com.lx.benefits.bean.eo.SellerItemOrderEO;
import com.lx.benefits.bean.eo.ShipOrderEO;
import com.lx.benefits.bean.param.order.ItemOrderListQueryParam;
import com.lx.benefits.bean.param.order.OrderLogisticsParam;
import com.lx.benefits.bean.util.EasyExcelUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionSupplierInfo;
import com.lx.benefits.bean.vo.order.ItemOrderListVO;
import com.lx.benefits.bean.vo.order.LogisticsVO;
import com.lx.benefits.mapper.express.entity.ExpressInfo;
import com.lx.benefits.service.express.ExpressService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.OrderShipLogisticsService;
import com.lx.benefits.support.order.OrderDeliveryInfoSupport;
import com.lx.benefits.support.order.OrderSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Api(tags = "供应商后台-订单管理模块")
@RestController("supplieradminOrderAdminController")
@RequestMapping("/supplieradmin/order")
public class SupplierOrderAdminController extends BaseSupplierAdminController {

    private final static Logger logger = LoggerFactory.getLogger(SupplierOrderAdminController.class);

    @Resource
    private OrderService orderService;
    @Resource
    private ExpressService expressService;
    @Resource
    private OrderSupport orderSupport;
    @Resource
    private OrderShipLogisticsService orderShipLogisticsService;
    @Resource
    private OrderDeliveryInfoSupport orderDeliveryInfoSupport;

    private Set<Integer> reverseSet = ImmutableSet.of(OrderEnum.REVERSE_STATUS.END.getCode(), OrderEnum.REVERSE_STATUS.END_SUCC.getCode(), OrderEnum.REVERSE_STATUS.APPLY.getCode());


    @PostMapping(value = "/list")
    public PageResult<ItemOrderListVO> list(@RequestBody ItemOrderListQueryParam req) {
        SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();
        Long supplierId = sessionSupplierInfo.getSupplierId();
        req.setSellerId(supplierId);
        if(req.getTitle() != null && !"".equals(req.getTitle().trim())) {
        	req.setTitle(req.getTitle().trim());
        }
        List<ItemOrderListVO> listVOS = orderService.getItemOrderList(req);
        if (CollectionUtils.isEmpty(listVOS)) {
            return PageResult.EMPTY;
        } else {
            listVOS.forEach(itemOrderListVO -> {
                itemOrderListVO.setStatusDesc(OrderEnum.STATUS.getDescByCode(itemOrderListVO.getStatus()));
                itemOrderListVO.setPayChannelDesc(PayChannelEnum.getDescByCode(itemOrderListVO.getPayChannel()));
                itemOrderListVO.setReverseStatusDesc(OrderEnum.REVERSE_STATUS.getFrontDescByCode(itemOrderListVO.getReverseStatus()));
            });
        }

        int count = orderService.ItemOrderCount(req);
        return PageResult.wrapPageResult(listVOS, count, req.getPageSize());
    }

    @PostMapping(value = "/export")
    public void export(@RequestBody ItemOrderListQueryParam req, HttpServletResponse response) {
        SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();
        Long supplierId = sessionSupplierInfo.getSupplierId();
        req.setSellerId(supplierId);
        if(req.getTitle() != null && !"".equals(req.getTitle().trim())) {
        	req.setTitle(req.getTitle().trim());
        }
        Function<ItemOrderListQueryParam, List<SellerItemOrderEO>> f = (pageQueryBO) -> {
            List<ItemOrderListVO> listVOS = orderService.getItemOrderList(pageQueryBO);
            if (CollectionUtils.isEmpty(listVOS)) {
                return Lists.newArrayList();
            }
            return CollectionExtUtil.copyListWithCheck(listVOS, itemOrderListVO -> {
                SellerItemOrderEO eo = BeanUtil.copySpring(itemOrderListVO, SellerItemOrderEO.class);

                String reverseStatusDesc = OrderEnum.REVERSE_STATUS.getFrontDescByCode(itemOrderListVO.getReverseStatus());
                String statusDesc = OrderEnum.STATUS.getDescByCode(itemOrderListVO.getStatus());
                eo.setStatusDesc(reverseSet.contains(itemOrderListVO.getReverseStatus()) ? reverseStatusDesc : statusDesc);
                eo.setShipTime(DateUtil.getDate(itemOrderListVO.getShipTime(), DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
                return eo;
            });
        };
        StringTemplateExcelUtil.exportCompressDataListBySegment(req, f, SellerItemOrderEO.class, response);
    }

    @ApiOperation(value = "供应商订单单个发货", response = JSONObject.class)
    @PostMapping(value = "/ship/{orderNumber}")
    public JSONObject ship(@PathVariable Long orderNumber, @RequestBody List<OrderLogisticsParam> logistics) {
        if (CollectionUtils.isEmpty(logistics)) {
            return Response.fail("请输入物流信息");
        }
        SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();
        Long supplierId = sessionSupplierInfo.getSupplierId();
        orderSupport.shipOrder(orderNumber, logistics, supplierId);
        return Response.succ("发货成功");
    }


    @ApiOperation(value = "供应商订单批量发货", response = JSONObject.class)
    @PostMapping(value = "/batchship")
    public JSONObject batchShip(@ApiParam(value = "待发货订单文件流", name = "file") @RequestParam(name = "file") MultipartFile multipartFile) throws IOException {
        if (null == multipartFile) {
            return Response.fail("请先上传待发货的订单明细文件");
        }

        List<ExpressInfo> list = expressService.selectByExample(null);
        Set<String> expressMap = CollectionExtUtil.toSet(list, ExpressInfo::getName);

        SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();
        Long supplierId = sessionSupplierInfo.getSupplierId();
        List<Object> data = EasyExcelUtil.readExcelWithModel(multipartFile.getInputStream(), ShipOrderEO.class, ExcelTypeEnum.XLSX);
        List<String> logisticsChannel = new ArrayList<>();
        List<Object> collect = data.stream().distinct().collect(Collectors.toList());
        for (Object row : collect) {
            ShipOrderEO shipOrderEO = (ShipOrderEO) row;
            if (Objects.nonNull(shipOrderEO)) {
                Long itemOrderNumber = shipOrderEO.getOrderNumber();
                OrderLogisticsParam orderLogisticsParam = new OrderLogisticsParam();
                orderLogisticsParam.setLogisticsChannel(shipOrderEO.getLogisticsChannel());
                orderLogisticsParam.setLogisticsNumber(shipOrderEO.getLogisticsNumber());

                if (!expressMap.contains(shipOrderEO.getLogisticsChannel())) {
                    logger.error(String.format("供应商订单批量发货异常 物流公司不存在  订单编号: %s  物流信息: %s", itemOrderNumber, JSON.toJSONString(orderLogisticsParam)));
                    if(!logisticsChannel.contains(shipOrderEO.getLogisticsChannel())) {
                    	logisticsChannel.add(shipOrderEO.getLogisticsChannel());
                    }
                } else {
                    try {
                        orderSupport.shipOrder(itemOrderNumber, Lists.newArrayList(orderLogisticsParam), supplierId);
                    } catch (BusinessException e) {
                        logger.error(String.format("供应商订单批量发货异常  订单编号: %s  物流信息: %s   失败原因：%s", itemOrderNumber, JSON.toJSONString(orderLogisticsParam), e.getMessage()));
                    }
                }
            }
        }
        if(!CollectionUtils.isEmpty(logisticsChannel)) {
        	throw new BusinessException(logisticsChannel.toString()+"物流公司不存在!");
        }
        return Response.succ("操作成功");
    }

    @PostMapping(value = "/shipinfo/{itemOrderNumber}")
    public Result<List<LogisticsVO>> getShip(@PathVariable Long itemOrderNumber) {

        List<OrderShipLogistics> orderShipLogistics = orderShipLogisticsService.listItemOrderLogistics(itemOrderNumber);
        List<LogisticsVO> logisticsVOS = CollectionExtUtil.copyListWithCheck(orderShipLogistics, LogisticsVO.class);
        return Result.wrapDefaultSuccessResult(logisticsVOS);
    }

    // 获取订单的物流信息
    @GetMapping("/deliverinfo/{orderNumber}")
    public Result<Object> getDeliverInfo(@PathVariable Long orderNumber) {
        List<ExpressResult<PackageLocationInfo>> orderDeliverInfo = orderDeliveryInfoSupport.getOrderDeliverInfo(orderNumber);
        return Result.wrapDefaultSuccessResult(orderDeliverInfo);
    }

}