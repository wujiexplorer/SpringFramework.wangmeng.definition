package com.lx.benefits.bean.eo;

import com.apollo.common.annotation.ExcelAnnotation;
import com.apollo.common.annotation.ExcelStringConfig;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ExcelStringConfig(fileName="订单导出")
public class SellerItemOrderEO implements Serializable{


    private static final long serialVersionUID = 1592508514586478356L;

    @ExcelAnnotation(header = "子订单编号", cell = 0)
    private Long number;
    @ExcelAnnotation(header = "主订单编号", cell = 1)
    private Long sellerOrderNumber;
    @ExcelAnnotation(header = "第三方订单编号", cell = 2)
    private String thirdOrderNumber;
    @ExcelAnnotation(header = "spu ID", cell = 3)
    private Long spuCode;
    @ExcelAnnotation(header = "sku ID", cell = 4)
    private Long skuId;
    @ExcelAnnotation(header = "skuCode", cell = 5)
    private String skuCode;
    @ExcelAnnotation(header = "商品名称", cell = 6)
    private String title;
    @ExcelAnnotation(header = "商品英文名", cell = 7)
    private String titleEn;
    @ExcelAnnotation(header = "规格", cell = 8)
    private String goodsSpec;
    @ExcelAnnotation(header = "数量", cell = 9)
    private int quantity;
    @ExcelAnnotation(header = "成本价", cell = 10)
    private BigDecimal costPrice;
    @ExcelAnnotation(header = "商品条码", cell = 11)
    private String itemNumber;
    @ExcelAnnotation(header = "品牌名称", cell = 12)
    private String brandName;
    /**存在售后时显示逆向状态*/
    @ExcelAnnotation(header = "订单状态", cell = 13)
    private String statusDesc;
    @ExcelAnnotation(header = "收件人", cell = 14)
    private String shipToName;
    @ExcelAnnotation(header = "电话", cell = 15)
    private String shipToMobile;
    @ExcelAnnotation(header = "收货地址", cell = 16)
    private String address;
    @ExcelAnnotation(header = "身份证号", cell = 17)
    private String idCardNo;
    @ExcelAnnotation(header = "发货时间", cell = 18)
    private String shipTime;
    @ExcelAnnotation(header = "买家备注", cell = 19)
    private String buyerComment;
    @ExcelAnnotation(header = "卖家备注", cell = 20)
    private String sellerComment;
    @ExcelAnnotation(header = "物流公司", cell = 21)
    private String logisticsChannel;
    @ExcelAnnotation(header = "物流编号", cell = 22)
    private String logisticsNumber;


}