package com.lx.benefits.service.jd;

import com.lx.benefits.bean.dto.jd.JDPrice;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 22:21
 * <p>
 * JDAPI价格相关接口
 */
public interface IJDPriceService {

    /**
     * 商品价格
     *
     * @param sku
     * @return
     * @throws Exception
     */
    JDPrice getPriceBySku(String sku) throws Exception;

    /**
     * 商品价格
     *
     * @param skus
     * @return
     * @throws Exception
     */
    List<JDPrice> getPriceBySkus(String skus) throws Exception;

    /**
     * 查询余额
     *
     * @param payType 支付类型  4：余额 7：网银钱包 101：金采支付
     * @return
     * @throws Exception
     */
    Double balance(Integer payType) throws Exception;

}
