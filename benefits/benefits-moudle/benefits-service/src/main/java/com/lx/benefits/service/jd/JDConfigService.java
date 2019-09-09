package com.lx.benefits.service.jd;

import com.lx.benefits.sdk.supplier.BaseSupplierInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by ldr on 2017/3/3.
 */
@Data
@Service("jdConfigService")
public class JDConfigService extends BaseSupplierInfo{

    public static final String CHARSET = "UTF-8";

    public static  final BigDecimal JD_ITEM_PRICE_RATE = new BigDecimal("0.95");

    public static final BigDecimal JD_ITEM_PRICE_SCALE_GATE= new BigDecimal("100");

    /**
     * CONFIG
     */
    @Value("${jd.client_id:null}")
    private String client_id;

    @Value("${jd.client_secret:null}")
    private String client_secret;

    @Value("${jd.username:null}")
    private String username;

    @Value("${jd.password:null}")
    private String password;

    /**
     * URLS
     */
    @Value("${jd.access_token_url:null}")
    private String access_token_url;

    @Value("${jd.refresh_access_token_url:null}")
    private String refresh_access_token_url;

    @Value("${jd.item_pool_url:null}")
    private String item_pool_url;

    @Value("${jd.item_pool_sku_url:null}")
    private String item_pool_sku_url;

    @Value("${jd.item_detail_url:null}")
    private String item_detail_url;

    @Value("${jd.item_state_url:null}")
    private String item_state_url;

    @Value("${jd.item_image_url:null}")
    private String item_image_url;

    @Value("${jd.item_comment_summary_url:null}")
    private String item_comment_summary_url;

    @Value("${jd.item_check_area_limit_url:null}")
    private String item_check_area_limit_url;

    @Value("${jd.item_check_url:null}")
    private String item_check_url;

    @Value("${jd.item_category_url:null}")
    private String item_category_url;

    @Value("${jd.item_categorys_url:null}")
    private String item_categorys_url;

    @Value("${jd.item_similar_url:null}")
    private String item_similar_url;

    @Value("${jd.order_freight_url:null}")
    private String order_freight_url;

    @Value("${jd.order_submit_url:null}")
    private String order_submit_url;

    @Value("${jd.order_confirm_url:null}")
    private String order_confirm_url;

    @Value("${jd.order_cancel_url:null}")
    private String order_cancel_url;

    @Value("${jd.order_pay_url:null}")
    private String order_pay_url;

    @Value("${jd.order_query_by_third_url:null}")
    private String order_query_by_third_url;

    @Value("${jd.order_query_url:null}")
    private String order_query_url;

    @Value("${jd.order_track_url:null}")
    private String order_track_url;

    @Value("${jd.check_order_new_url:null}")
    private String check_order_new_url;

    @Value("${jd.check_order_dlok_url:null}")
    private String check_order_dlok_url;

    @Value("${jd.check_order_refuse_url:null}")
    private String check_order_refuse_url;

    @Value("${jd.search_url:null}")
    private String search_url;

    @Value("${jd.area_province_url:null}")
    private String area_province_url;

    @Value("${jd.area_city_url:null}")
    private String area_city_url;

    @Value("${jd.area_county_url:null}")
    private String area_county_url;

    @Value("${jd.area_town_url:null}")
    private String area_town_url;

    @Value("${jd.area_check_url:null}")
    private String area_check_url;

    @Value("${jd.price_jdprice_url:null}")
    private String price_jdprice_url;

    @Value("${jd.price_agreement_price_url:null}")
    private String price_agreement_price_url;

    @Value("${jd.price_sale_price_url:null}")
    private String price_sale_price_url;

    @Value("${jd.price_balance_url:null}")
    private String price_balance_url;

    @Value("${jd.price_balance_detail_url:null}")
    private String price_balance_detail_url;

    @Value("${jd.stock_new_stock_url:null}")
    private String stock_new_stock_url;

    @Value("${jd.stock_stock_url:null}")
    private String stock_stock_url;

    @Value("${jd.message_get_url:null}")
    private String message_get_url;

    @Value("${jd.message_del_url:null}")
    private String message_del_url;

    @Value("${jd.aftersale_apply_url:null}")
    private String aftersale_apply_url;

    @Value("${jd.aftersale_deliver_info_update_url:null}")
    private String aftersale_deliver_info_update_url;

    @Value("${jd.aftersale_get_available_number_comp_url:null}")
    private String aftersale_get_available_number_comp_url;

    @Value("${jd.aftersale_get_customer_expect_comp_url:null}")
    private String aftersale_get_customer_expect_comp_url;

    @Value("${jd.aftersale_get_service_list_url:null}")
    private String aftersale_get_service_list_url;

    @Value("${jd.aftersale_get_service_detail_url:null}")
    private String aftersale_get_service_detail_url;

    @Value("${jd.aftersale_audit_cancel_url:null}")
    private String aftersale_audit_cancel_url;
    
    @Value("${jd.aftersale_get_ware_return_jd_comp_url:null}")
    private String aftersale_get_ware_return_jd_comp_url;
    
    @Value("${jd.aftersale_get_update_send_sku_url:null}")
    private String aftersale_get_update_send_sku_url;

    @Value("${jd.stock_new_stock_url:null}")
    private String jdStockNewStockUrl;
    
    @Value("${jd.area_getaddress_url:null}")
    private String areaGetAddressUrl;

}
