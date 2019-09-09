package com.lx.benefits.bean.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 后台订单列表
 */
@Data
public class ItemOrderListVO {
    /**
     * 商品级订单编号
     */
    private Long number;
    /**
     * 商家级订单编号
     */
    private Long sellerOrderNumber;
    /**
     * 第三方订单编号
     */
    private String thirdOrderNumber;
    /**
     * 卖家备注
     */
    private String buyerComment;
    /**
     * 卖家备注
     */
    private String sellerComment;
    /**
     * 来源
     */
    private String source;
    /**
     * 购买用户账号
     */
    private String loginName;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 商品条码
     */
    private String itemNumber;
    /**
     * spuCode
     */
    @JsonIgnore
    private Long spuCode;
    /**
     * 商品skuID
     */
    private Long skuId;
    /**
     * 商品skuCode 商品级订单中deliverCode
     */
    private String skuCode;
    /**
     * 商品名称
     */
    private String title;
    /**
     * 商品英文名
     */
    private String titleEn;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 规格
     */
    private String goodsSpec;
    /**
     * 成本价
     */
    private BigDecimal costPrice;
    /**
     * 购买价
     */
    private BigDecimal price;
    /**
     * 市场价
     */
    private BigDecimal marketPrice;
    /**
     * 状态
     */
    private int status;
    /**
     * 状态
     */
    private String statusDesc;
    /**
     * 逆向状态
     */
    private Integer reverseStatus;
    /**
     * 逆向状态描述
     */
    private String reverseStatusDesc;
    /**
     * 支付渠道
     */
    private int payChannel;
    /**
     * 支付渠道
     */
    private String payChannelDesc;
    /**
     * 供应商名称
     */
    private String sellerName;
    /**
     * 企业名称
     */
    private String enterprName;
    /**
     * 联系人
     */
    private String shipToName;
    /**
     * 联系电话
     */
    private String shipToMobile;
    /**
     * 身份证号
     */
    private String idCardNo;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 运费
     */
    private BigDecimal totalFreightPrice;
    /**
     * 抵扣总积分
     */
    private BigDecimal totalPoint;
    /**
     * 实付金额
     */
    private BigDecimal totalRealPrice;
    /**
     * 一级类目名称
     */
    private String category1Name;
    /**
     * 二级类目名称
     */
    private String category2Name;
    /**
     * 三级类目名称
     */
    private String category3Name;
    /**
     * 购买数量
     */
    private int quantity;
    /**
     * 支付时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 发货时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date shipTime;
    /**
     * 创建时间，创建时间为下单时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 取消时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date cancelTime;

    private BigDecimal voucherBenefit;
}
