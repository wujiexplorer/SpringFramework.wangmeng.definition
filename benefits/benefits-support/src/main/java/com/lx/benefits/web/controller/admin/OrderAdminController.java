package com.lx.benefits.web.controller.admin;

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
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.entity.order.OrderShip;
import com.lx.benefits.bean.entity.order.OrderShipLogistics;
import com.lx.benefits.bean.enums.OrderEnum;
import com.lx.benefits.bean.enums.PayChannelEnum;
import com.lx.benefits.bean.eo.ItemOrderEO;
import com.lx.benefits.bean.param.order.ItemOrderListQueryParam;
import com.lx.benefits.bean.param.order.OrderShipParam;
import com.lx.benefits.bean.vo.order.ItemOrderListVO;
import com.lx.benefits.bean.vo.order.LogisticsVO;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.OrderShipLogisticsService;
import com.lx.benefits.service.order.OrderShipService;
import com.lx.benefits.support.order.OrderDeliveryInfoSupport;
import com.lx.benefits.web.controller.supplieradmin.BaseSupplierAdminController;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

@Api(tags = "运营后台-订单管理模块")
@RestController("adminOrderAdminController")
@RequestMapping("/admin/order")
public class OrderAdminController extends BaseSupplierAdminController {

    private final static Logger logger = LoggerFactory.getLogger(OrderAdminController.class);

    @Resource
    private OrderService orderService;
    @Resource
    private OrderShipLogisticsService orderShipLogisticsService;
    @Resource
    private OrderShipService orderShipService;
    @Resource
    private OrderDeliveryInfoSupport orderDeliveryInfoSupport;

    private Set<Integer> reverseSet = ImmutableSet.of(OrderEnum.REVERSE_STATUS.END.getCode(),OrderEnum.REVERSE_STATUS.END_SUCC.getCode(),OrderEnum.REVERSE_STATUS.APPLY.getCode());


    @PostMapping(value = "/list")
    public PageResult<ItemOrderListVO> list(@RequestBody ItemOrderListQueryParam req) {

        List<ItemOrderListVO> listVOS = orderService.listItemOrderList(req);
        if(CollectionUtils.isEmpty(listVOS)){
            return PageResult.EMPTY;
        }else {
            listVOS.forEach(itemOrderListVO -> {
                itemOrderListVO.setStatusDesc(OrderEnum.STATUS.getDescByCode(itemOrderListVO.getStatus()));
                itemOrderListVO.setPayChannelDesc(PayChannelEnum.getDescByCode(itemOrderListVO.getPayChannel()));
                itemOrderListVO.setReverseStatusDesc(OrderEnum.REVERSE_STATUS.getDescByCode(itemOrderListVO.getReverseStatus()));
            });
        }

        int count = orderService.getItemOrderCount(req);
        return PageResult.wrapPageResult(listVOS,count,req.getPageSize());
    }

    @PostMapping(value = "/export")
    public void export(@RequestBody ItemOrderListQueryParam req, HttpServletResponse response) {

        Function<ItemOrderListQueryParam, List<ItemOrderEO>> f = (pageQueryBO) -> {
            List<ItemOrderListVO> listVOS = orderService.listItemOrderList(pageQueryBO);
            if ( CollectionUtils.isEmpty(listVOS)) {
                return Lists.newArrayList();
            }
            return CollectionExtUtil.copyListWithCheck(listVOS,itemOrderListVO -> {
                ItemOrderEO eo = BeanUtil.copySpring(itemOrderListVO,ItemOrderEO.class);

                String reverseStatusDesc = OrderEnum.REVERSE_STATUS.getFrontDescByCode(itemOrderListVO.getReverseStatus());
                String statusDesc= OrderEnum.STATUS.getDescByCode(itemOrderListVO.getStatus());
                eo.setStatusDesc(reverseSet.contains(itemOrderListVO.getReverseStatus())?reverseStatusDesc:statusDesc);
                eo.setCategoryName(itemOrderListVO.getCategory1Name()+"-"+itemOrderListVO.getCategory2Name()+"-"+itemOrderListVO.getCategory3Name());
                eo.setTotalRealPrice(itemOrderListVO.getTotalPrice().setScale(2, RoundingMode.HALF_UP));
                eo.setDeductionPrice(BigDecimal.ZERO);
                eo.setCostPrice(itemOrderListVO.getCostPrice().setScale(2, RoundingMode.HALF_UP));
                eo.setTotalFreightPrice(itemOrderListVO.getTotalFreightPrice().setScale(2, RoundingMode.HALF_UP));
                eo.setTotalPrice(itemOrderListVO.getTotalPrice().setScale(2, RoundingMode.HALF_UP));

                eo.setPayTime(DateUtil.getDate(itemOrderListVO.getPayTime(),DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
                eo.setShipTime(DateUtil.getDate(itemOrderListVO.getShipTime(),DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
                eo.setCreateTime(DateUtil.getDate(itemOrderListVO.getCreateTime(),DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
                eo.setCancelTime(DateUtil.getDate(itemOrderListVO.getCancelTime(),DatePatternEnum.Y_M_D_SPACE_H_M_S.getPattern()));
                return eo;
            });
        };
        StringTemplateExcelUtil.exportCompressDataListBySegment(req,f, ItemOrderEO.class, response);
    }



    @PostMapping(value = "/shipinfo/{itemOrderNumber}")
    public Result<List<LogisticsVO>> getShip(@PathVariable Long itemOrderNumber) {

        List<OrderShipLogistics> orderShipLogistics = orderShipLogisticsService.listItemOrderLogistics(itemOrderNumber);
        List<LogisticsVO> logisticsVOS = CollectionExtUtil.copyListWithCheck(orderShipLogistics,LogisticsVO.class);
        return Result.wrapDefaultSuccessResult(logisticsVOS);
    }

    @PostMapping(value = "/modify/recieve/address/{sellerOrderNumber}")
    public Result modifyReceiveAddress(@PathVariable Long sellerOrderNumber,@RequestBody OrderShipParam orderShipParam) {
        List<Order> orderList = orderService.listByNumberAndLevel(sellerOrderNumber,2);
        if (CollectionUtils.isEmpty(orderList)) {
            throw new BusinessException("订单不存在");
        }
        if (orderList.get(0).getStatus() != OrderEnum.STATUS.PAID.getCode()) {
            throw new BusinessException("当前订单状态不允许修改收货地址");
        }
        OrderShip orderShip = orderShipService.getByOrderNumber(sellerOrderNumber);
        if(Objects.isNull(orderShip)){
            throw new BusinessException("订单异常");
        }
        OrderShip orderShipUpdate = BeanUtil.copySpring(orderShipParam,OrderShip.class);
        orderShipUpdate.setId(orderShip.getId());
        orderShipService.doModRecord(orderShipUpdate);
        return Result.wrapDefaultSuccessResult();
    }

    
 // 获取订单的物流信息
  	@GetMapping("/deliverinfo/{orderNumber}")
  	public Result getDeliverInfo(@PathVariable Long orderNumber) {
  		List<ExpressResult<PackageLocationInfo>> orderDeliverInfo = orderDeliveryInfoSupport.getOrderDeliverInfo(orderNumber);
  		return Result.wrapDefaultSuccessResult(orderDeliverInfo);
  	}

}
