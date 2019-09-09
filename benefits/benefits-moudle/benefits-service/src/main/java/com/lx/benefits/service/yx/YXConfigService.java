package com.lx.benefits.service.yx;

import com.lx.benefits.sdk.supplier.BaseSupplierInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by ldr on 2017/5/26.
 */
@Data
@Service("yxConfigService")
public class YXConfigService extends BaseSupplierInfo{

    public static final int GET_ITEM_DETAIL_LIMIT = 30;

    public static final String SYN_YX_ITEM_CACHE_KEY="SYN_YX_ITEM_CACHE_KEY_777";


//    @Value("${yx.api_host:null}")
//    private String host;
//
//    @Value("${yx.api_url:null}")
//    private String url;
//
//    @Value("${yx.api_key:null}")
//    private String apiKey;
//
//    @Value("${yx.api_secret:null}")
//    private String apiSecret;

    @Value("${yx.order_payed:null}")
    private String orderPayed;

    @Value("${yx.order_get:null}")
    private String orderGet;

    @Value("${yx.order_confirm:null}")
    private String orderConfirm;

    @Value("${yx.order_cancel:null}")
    private String orderCancel;

    @Value("${yx.item_get_ids:null}")
    private String itemGetIds;

    @Value("${yx.item_get_details:null}")
    private String itemGetDetails;

    @Value("${yx.inventory_get:null}")
    private String inventoryGet;

    @Value("${yx.openApi.registeritemChangeMethod.method:null}")
    private String registeritemChangeMethod;

    @Value("${yx.openApi.getCallBackMethods.method:null}")
    private String callBackMethods;

    @Value("${yx.openApi.callback.item.change.method:null}")
    private String callbackItemChange;

}
