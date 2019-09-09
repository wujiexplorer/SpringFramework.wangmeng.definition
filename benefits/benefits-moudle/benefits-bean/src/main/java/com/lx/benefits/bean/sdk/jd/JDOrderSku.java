package com.lx.benefits.bean.sdk.jd;

import lombok.Data;

import java.math.BigDecimal;

/**
 * [{"skuId":商品编号, "num":商品数量,"bNeedAnnex":true, "bNeedGift":true, "price":100, "yanbao":[{"skuId":商品编号}]}](最高支持50种商品)
 *      bNeedAnnex表示是否需要附件，默认每个订单都给附件，默认值为：true，如果客户实在不需要附件bNeedAnnex可以给false，该参数配置为false时请谨慎，真的不会给客户发附件的;
 *      bNeedGift表示是否需要增品，默认不给增品，默认值为：false，如果需要增品bNeedGift请给true,建议该参数都给true,但如果实在不需要增品可以给false;
 *      price 表示透传价格，需要合同权限，接受价格权限，否则不允许传该值；
 */
@Data
public class JDOrderSku {

    private String skuId;

    private Integer num;

    private boolean bNeedAnnex = true;

    private boolean bNeedGift = true;

    private BigDecimal price;

}
