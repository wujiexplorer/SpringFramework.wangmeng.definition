package com.lx.benefits.bean.dto.admin.product;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author unknow on 2018-12-05 00:47.
 */
@Data
public class ProductInfoDto extends BaseRowModel {

    /** 品牌名称 */
    @ExcelProperty(index = 0)
    private String brandName;
    private Long brandId;

    /** 一级类目 */
    @ExcelProperty(index = 1)
    private String categoryName;
    private Long categoryId;

    /** 二级类目 */
    @ExcelProperty(index = 2)
    private String categoryName2;
    private Long categoryId2;

    /** 三级类目 */
    @ExcelProperty(index = 3)
    private String categoryName3;
    private Long categoryId3;

    /** 商品类型 虚拟商品，0实物商品，默认为0 */
    @ExcelProperty(index = 4)
    private Integer goodsType = 0;

    /** 跨境 1:跨境0非跨境 */
    @ExcelProperty(index = 5)
    private Integer isCross = 0;

    /** 供应商名称 */
    @ExcelProperty(index = 6)
    private String supplierName;
    private Long supplierId;

    /** 商品名称 */
    @ExcelProperty(index = 7)
    private String goodsName;

    /** 英文名称 */
    @ExcelProperty(index = 8)
    private String goodsNameEn;

    /** 商品描述 */
    @ExcelProperty(index = 9)
    private String goodsBody;

    /** 商品主图 */
    @ExcelProperty(index = 10)
    private String goodsImage;

    /** 轮播图 */
    @ExcelProperty(index = 11)
    private String goodsImages;

    /** 产地 */
    @ExcelProperty(index = 12)
    private String placeOrigin;
    private Long placeOriginId;

    /** 原厂货号 */
    @ExcelProperty(index = 13)
    private String itemNumber;

    /** 运费 */
    @ExcelProperty(index = 14)
    private BigDecimal goodsFreight = new BigDecimal("0");

    /** * 折扣*/
    @ExcelProperty(index = 15)
    private BigDecimal goodsDiscount;

    /** 市场价 */
    @ExcelProperty(index = 16)
    private BigDecimal goodsMarkprice;

    /** 成本价 */
    @ExcelProperty(index = 17)
    private BigDecimal goodsCostprice;

    /** 销售价 */
    @ExcelProperty(index = 18)
    private BigDecimal goodsPrice;

    /** 颜色 */
    @ExcelProperty(index = 19)
    private String colour;

    /** 尺寸 */
    @ExcelProperty(index = 20)
    private String size;

    /** 商品库存 */
    @ExcelProperty(index = 21)
    private Integer goodsStorge;

    /** 单位 */
    @ExcelProperty(index = 22)
    private String saleUnit;



}
