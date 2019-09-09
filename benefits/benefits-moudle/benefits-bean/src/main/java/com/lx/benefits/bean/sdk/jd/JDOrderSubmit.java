package com.lx.benefits.bean.sdk.jd;

import lombok.Data;

import java.io.Serializable;

/**
 * 京东下单信息
 * Created by ldr on 2017/3/14.
 */
@Data
public class JDOrderSubmit implements Serializable {

    private static final long serialVersionUID = 6247871912175851882L;

    /**
     * 第三方的订单单号
     */
    private String thirdOrder;
    /**
     * [{"skuId":商品编号, "num":商品数量,"bNeedAnnex":true, "bNeedGift":true, "price":100, "yanbao":[{"skuId":商品编号}]}](最高支持50种商品)
     bNeedAnnex表示是否需要附件，默认每个订单都给附件，默认值为：true，如果客户实在不需要附件bNeedAnnex可以给false，该参数配置为false时请谨慎，真的不会给客户发附件的;
     bNeedGift表示是否需要增品，默认不给增品，默认值为：false，如果需要增品bNeedGift请给true,建议该参数都给true,但如果实在不需要增品可以给false;
     price 表示透传价格，需要合同权限，接受价格权限，否则不允许传该值；

     */
    private String sku;

    /**
     * 收货人
     */
    private String name;

    /**
     * 一级地址
     */
    private Long province;

    /**
     * 二级地址
     */
    private Long city;

    /**
     * 三级地址
     */
    private Long county;

    /**
     * 四级地址 没有填0
     */
    private Long town;

    /**
     * 详细地址
     */
    private String address;

    /**
     *  邮编 非必填
     */
    private String zip;

    /**
     * 座机 非必填
     */
    private String phone;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注 非必填
     */
    private String remark;

    /**
     * 开票方式(1为随货开票，0为订单预借，2为集中开票 )
     */
    private Integer invoiceState;

    /**
     *1普通发票2增值税发票 3电子发票
     */
    private Integer invoiceType;

    /**
     * 发票类型：4个人，5单位
     */
    private Integer selectedInvoiceTitle;

    /**
     * 发票抬头  (如果selectedInvoiceTitle=5则此字段必须)
     */
    private String companyName;

    /**
     * 1:明细，3：电脑配件，19:耗材，22：办公用品
     备注:若增值发票则只能选1 明细
     */
    private Integer invoiceContent;

    /**
     * 支付方式 (1：货到付款，2：邮局付款，4：在线支付，5：公司转账，6：银行转账，7：网银钱包，101：金采支付
     */
    private Integer paymentType;

    /**
     * 使用余额paymentType=4时，此值固定是1
     其他支付方式0
     */
    private Integer isUseBalance;

    /**
     * 是否预占库存，0是预占库存（需要调用确认订单接口），1是不预占库存     金融支付必须预占库存传0
     */
    private Integer submitState =1;

    /**
     * 增值票收票人姓名
     备注：当invoiceType=2 且invoiceState=1时则此字段必填
     */
    private String invoiceName;

    /**
     * 增值票收票人电话
     备注：当invoiceType=2 且invoiceState=1时则此字段必填
     */
    private String invoicePhone;

    /**
     * 增值票收票人所在省(京东地址编码)
     备注：当invoiceType=2 且invoiceState=1时则此字段必填
     */
    private Integer invoiceProvice;

    /**
     * 增值票收票人所在市(京东地址编码)
     备注：当invoiceType=2 且invoiceState=1时则此字段必填
     */
    private Integer invoiceCity;

    /**
     * 增值票收票人所在区/县(京东地址编码)
     备注：当invoiceType=2 且invoiceState=1时则此字段必填
     */
    private Integer invoiceCounty;

    /**
     * 增值票收票人所在地址
     备注：当invoiceType=2 且invoiceState=1时则此字段必填
     */
    private String invoiceAddress;

    /**
     * 1:必需验证客户端订单价格快照，如果快照与京东价格不一致返回下单失败，需要更新商品价格后，重新下单;
     */
    private Integer doOrderPriceMode;

    /**
     * 客户端订单价格快照	Json格式的数据，格式为:
     [
     {
     "price":21.30, //商品价格	,类型：BigDecimal
     "skuId":123123 //商品编号,类型：long
     },{
     "price":99.55,
     "skuId":22222
     }
     ]
     */
    private String orderPriceSnap;

    /**
     * 大家电配送日期
     * 默认值为-1，0表示当天，1表示明天，2：表示后天; 如果为-1表示不使用大家电预约日历
     */
    private Integer reservingDate = -1;

    /**
     * 大家电安装日期
     * 不支持默认按-1处理，0表示当天，1表示明天，2：表示后天
     */
    private Integer installDate ;

    /**
     * 大家电是否选择了安装
     * 是否选择了安装，默认为true，选择了“暂缓安装”，此为必填项，必填值为false。
     */
    private Boolean needInstall  ;

    /**
     * 中小件配送预约日期	格式：yyyy-MM-dd
     */
    private String promiseDate;

    /**
     * 中小件配送预约时间段	时间段如： 9:00-15:00
     */
    private String promiseTimeRange;

    /**
     * 中小件预约时间段的标记
     */
    private Integer promiseTimeRangeCode;
}
