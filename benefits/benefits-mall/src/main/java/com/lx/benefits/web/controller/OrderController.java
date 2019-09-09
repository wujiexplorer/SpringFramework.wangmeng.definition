package com.lx.benefits.web.controller;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apollo.common.exception.BusinessException;
import com.apollo.common.utils.ClientIPUtil;
import com.apollo.starter.web.utils.PageResult;
import com.apollo.starter.web.utils.Result;
import com.lx.benefits.bean.bo.order.OrderSaveBO;
import com.lx.benefits.bean.bo.order.UserOrderOverviewBO;
import com.lx.benefits.bean.dto.deliverinfo.ExpressResult;
import com.lx.benefits.bean.dto.deliverinfo.PackageLocationInfo;
import com.lx.benefits.bean.dto.wechat.Code2SessionDTO;
import com.lx.benefits.bean.entity.order.Order;
import com.lx.benefits.bean.param.order.OrderBuyNowParam;
import com.lx.benefits.bean.param.order.OrderBuyParam;
import com.lx.benefits.bean.param.order.OrderQueryParam;
import com.lx.benefits.bean.param.order.OrderSubmitParam;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.bean.vo.order.OrderConfirmVO;
import com.lx.benefits.bean.vo.order.OrderDetailVO;
import com.lx.benefits.bean.vo.order.OrderListVO;
import com.lx.benefits.bean.vo.pay.PayChargeVO;
import com.lx.benefits.service.order.GetAdressService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.order.RefundApplyService;
import com.lx.benefits.service.product.ProductSettingService;
import com.lx.benefits.support.order.OrderDeliveryInfoSupport;
import com.lx.benefits.support.order.OrderPaySupport;
import com.lx.benefits.support.order.OrderQuerySupport;
import com.lx.benefits.support.order.OrderSupport;
import com.lx.benefits.web.support.WeChatSupport;

/**
 * 订单
 **/
@RestController
@RequestMapping("/benefits/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private RefundApplyService refundApplyService;
    @Resource
    private OrderSupport orderSupport;
    @Resource
    private OrderPaySupport orderPaySupport;
    @Resource
    private OrderQuerySupport orderQuerySupport;
    @Resource
    private OrderDeliveryInfoSupport orderDeliveryInfoSupport;
    @Resource
    private GetAdressService getAdressService;
	@Autowired
	private WeChatSupport weChatSupport;
    @Autowired
    private ProductSettingService productSettingService;
    @PostMapping("/overview")
    public Result<UserOrderOverviewBO> overview(){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();

        int refundCount = refundApplyService.getUserRefundCount(accountId,null);
        UserOrderOverviewBO overviewBO = orderService.getUserOrderView(accountId);
        overviewBO.setRefundCount(refundCount);
        return Result.wrapDefaultSuccessResult(overviewBO);
    }

    @PostMapping("/")
    public PageResult<OrderListVO> index(@RequestBody OrderQueryParam queryParam){

        return orderQuerySupport.listUserOrders(queryParam);
    }

    @PostMapping("/confirm")
    public Result<OrderConfirmVO> confirm(@RequestParam(value = "userReceiveAddrId",defaultValue = "0") Long userReceiveAddrId,@RequestParam(value = "voucherIds",required = false) String voucherids){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        return Result.wrapDefaultSuccessResult(orderSupport.confirmOrder(userReceiveAddrId,accountId,voucherids));
    }
    @PostMapping("/buyNow")
    public Result<OrderConfirmVO> buyNow(@RequestBody OrderBuyNowParam orderBuyNowParam){
        return Result.wrapDefaultSuccessResult(orderSupport.buyNowConfirmOrder(orderBuyNowParam));
    }

    @PostMapping("/validate")
    public Object validateOrderInfo(@Validated @RequestBody OrderBuyParam orderBuyParam){
    	return Result.wrapDefaultSuccessResult(orderSupport.validateOrderInfo(orderBuyParam,orderBuyParam.getUserReceiveAddrId()));
    }

    @PostMapping("/submit")
    public Result<Long> submit(@RequestBody OrderSubmitParam orderSubmitParam){
		SessionShopInfo sessionShopInfo = SessionContextHolder.getSessionEmployeeInfo();
		List<String> enterprIds = productSettingService.getOrerSubmitBlackEnterprId();
		if (!CollectionUtils.isEmpty(enterprIds) && enterprIds.contains(sessionShopInfo.getEnterprId().toString())) {
			throw new BusinessException("积分核对中,请勿下单!");
		}
		
    	Long accountId = sessionShopInfo.getEmployeeId();
    	int platform = sessionShopInfo.getPlatform();
        orderSubmitParam.setAccountId(accountId);
        orderSubmitParam.setPlatform(platform);
        orderSubmitParam.setOnlyPointPay(false);
        orderSubmitParam.setEnterprId(sessionShopInfo.getEnterprId());
        OrderSaveBO orderSaveBO = orderSupport.assembleOrder(orderSubmitParam);
        orderSupport.saveOrder(orderSaveBO, orderSubmitParam.getCampaginId());
        return Result.wrapDefaultSuccessResult(orderSaveBO.getPayOrderNumber());
    }

    @PostMapping("/detail/{sellerOrderNumber}")
    public Result<OrderDetailVO> detail(@PathVariable Long sellerOrderNumber){
        return Result.wrapDefaultSuccessResult(orderQuerySupport.getOrderDetailVO(sellerOrderNumber));
    }

    /**
     * 商家级订单取消
     * @param sellerOrderNumber 商家级订单编号
     * @return
     */
    @PostMapping("/cancel/{sellerOrderNumber}")
    public Result cancel(@PathVariable Long sellerOrderNumber){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        orderSupport.cancelOrder(sellerOrderNumber,accountId,false);
        return Result.wrapDefaultSuccessResult();
    }

    /**
     *
     * @param payOrderNumber
     * @param payChannel
     * @param isFromWeb 是否web页面登录 0 否 1 是
     * @param request
     * @return
     */
    @PostMapping("/pay")
    public Result pay(@RequestParam("payOrderNumber") Long payOrderNumber,
                      @RequestParam("payChannel") Integer payChannel,
                      @RequestParam(name = "isFromWeb",defaultValue = "1",required = false) Integer isFromWeb,
                      @RequestParam(required=false) String wxCode,
                      HttpServletRequest request){
        boolean fromWeb = isFromWeb==1;
        String ip = ClientIPUtil.getIpAddress(request);
        SessionShopInfo simpleUser = SessionContextHolder.getSessionEmployeeInfo();
        Order payOrder = orderSupport.getOrCreatePayOrder(payOrderNumber, simpleUser.getEmployeeId());
        String wxOpenId=null;
		if (!StringUtil.isEmpty(wxCode)) {
			Code2SessionDTO code2SessionDTO = weChatSupport.code2Session(wxCode);
			if (code2SessionDTO != null) {
				wxOpenId = code2SessionDTO.getOpenId();
			}
		}
		PayChargeVO payChargeVO = orderPaySupport.pay(payOrder,payChannel,fromWeb,ip,wxOpenId);
        return Result.wrapDefaultSuccessResult(payChargeVO);
    }

    @PostMapping("/check/payStatus/{payOrderNumber}")
    public Result<Integer> payStatus(@PathVariable Long payOrderNumber){
        Order order = orderService.getOrderByNumber(payOrderNumber);
        if(Objects.isNull(order)){
            throw new BusinessException("订单不存在");
        }
        return Result.wrapDefaultSuccessResult(order.getStatus());
    }

    /**
     * 
     * @Title: 确认收货   
     * @Description:    
     * @param: @param sellerOrderNumber
     * @param: @return      
     * @return: Result      
     * @throws
     */
    @PostMapping("/confirm/receipt/{sellerOrderNumber}")
    public Result confirmReceipt(@PathVariable Long sellerOrderNumber){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        orderSupport.confirmReceipt(sellerOrderNumber,accountId);
        return Result.wrapDefaultSuccessResult();
    }

 // 获取订单的物流信息
 	@GetMapping("/deliverinfo/{orderNumber}")
 	public Result getDeliverInfo(@PathVariable Long orderNumber) {
 		List<ExpressResult<PackageLocationInfo>> orderDeliverInfo = orderDeliveryInfoSupport.getOrderDeliverInfo(orderNumber);
 		return Result.wrapDefaultSuccessResult(orderDeliverInfo);
 	}



}
