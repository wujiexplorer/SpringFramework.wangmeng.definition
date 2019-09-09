package com.lx.benefits.service.yxOrder;


import com.lx.benefits.bean.dto.yx.result.YXResult;
import com.lx.benefits.bean.dto.yxOrder.api.YXChannelOrderOut;
import com.lx.benefits.bean.dto.yxOrder.api.YXOrderCancelResult;
import com.lx.benefits.bean.sdk.yanxuan.YXOrder;

/**
 * 严选订单相关接口
 * Created by ldr on 2017/6/7.
 */
public interface IYXOrderService {

   YXChannelOrderOut submit(Long sellerOrderNumber);

   YXOrderCancelResult cancel(String orderId);

   YXResult confirm(String orderId, String packageId);

   YXResult<YXChannelOrderOut> query(String orderId);
   
   
   Integer freight(Long totalGoodsPrice);

}
