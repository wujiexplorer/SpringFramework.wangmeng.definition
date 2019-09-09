package com.lx.benefits.bean.eo;

import com.apollo.common.annotation.ExcelAnnotation;
import com.apollo.common.annotation.ExcelStringConfig;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ExcelStringConfig(fileName="订单导出")
public class ItemOrderEO implements Serializable{

    private static final long serialVersionUID = 3691285020795131376L;

    @ExcelAnnotation(header = "子订单编号", cell = 0)
    private Long number;
    @ExcelAnnotation(header = "主订单编号", cell = 1)
    private Long sellerOrderNumber;
    @ExcelAnnotation(header = "第三方订单编号", cell = 2)
    private String thirdOrderNumber;
    @ExcelAnnotation(header = "企业", cell = 3)
    private String enterprName;
    @ExcelAnnotation(header = "用户", cell = 4)
    private String loginName;
    @ExcelAnnotation(header = "供应商", cell = 5)
    private String sellerName;
    @ExcelAnnotation(header = "spu ID", cell = 6)
    private Long spuCode;
    @ExcelAnnotation(header = "sku ID", cell = 7)
    private Long skuId;
    @ExcelAnnotation(header = "skuCode", cell = 8)
    private String skuCode;
    @ExcelAnnotation(header = "商品名称", cell = 9)
    private String title;
    @ExcelAnnotation(header = "商品英文名", cell = 10)
    private String titleEn;
    @ExcelAnnotation(header = "规格", cell = 11)
    private String goodsSpec;
    @ExcelAnnotation(header = "数量", cell = 12)
    private int quantity;
    @ExcelAnnotation(header = "成本价", cell = 13)
    private BigDecimal costPrice;
    @ExcelAnnotation(header = "商品条码", cell = 14)
    private String itemNumber;
    @ExcelAnnotation(header = "品牌名称", cell = 15)
    private String brandName;
    /**一级-二级-三级类目名称*/
    @ExcelAnnotation(header = "一二三级分类", cell = 16)
    private String categoryName;
    @ExcelAnnotation(header = "商品图片", cell = 17)
    private String image;
    @ExcelAnnotation(header = "物流费用", cell = 18)
    private BigDecimal totalFreightPrice;
    @ExcelAnnotation(header = "支付金额", cell = 19)
    private BigDecimal totalPrice;
    @ExcelAnnotation(header = "实收金额", cell = 20)
    private BigDecimal totalRealPrice;
    @ExcelAnnotation(header = "抵扣金额", cell = 21)
    private BigDecimal deductionPrice;
    /**存在售后时显示逆向状态*/
    @ExcelAnnotation(header = "订单状态", cell = 22)
    private String statusDesc;
    @ExcelAnnotation(header = "收件人", cell = 23)
    private String shipToName;
    @ExcelAnnotation(header = "电话", cell = 24)
    private String shipToMobile;
    @ExcelAnnotation(header = "收货地址", cell = 25)
    private String address;
    @ExcelAnnotation(header = "身份证号", cell = 26)
    private String idCardNo;
    @ExcelAnnotation(header = "支付时间", cell = 27)
    private String payTime;
    @ExcelAnnotation(header = "发货时间", cell = 28)
    private String shipTime;
    @ExcelAnnotation(header = "下单时间", cell = 29)
    private String createTime;
    @ExcelAnnotation(header = "取消时间", cell = 30)
    private String cancelTime;
    @ExcelAnnotation(header = "买家备注", cell = 31)
    private String buyerComment;
    @ExcelAnnotation(header = "卖家备注", cell = 32)
    private String sellerComment;

}