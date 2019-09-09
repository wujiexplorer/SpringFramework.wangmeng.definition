package com.lx.benefits.bean.dto.enterprise;


import lombok.Data;

import java.util.List;

/**
 * @author unknow on 2018-12-09 23:54.
 */
@Data
public class GoodsInfoResDto {
    /**
     * skuId
     */
    private Long sku;
    /**
     * 产地
     */
    private String countryname;
    /**
     * 商品图片
     */
    private String imgurl;

    private List<String> imgUrlList;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格
     */
    private String price;

    /**
     * 市场价
     */
    private String marketPrice;

    /**
     * 商品状态
     */
    private Integer status;
    private String statusdesc;
    private String stock;

    private String introduction;

    private Long brandId;

    private Long placeOriginId;

    private Long supplierId;

    private String brandName;

    private String placeOrigin;

    private String sexName;

    private String season;

    private String countrySize;

/*  private String channelid;
    private String channel;
    private String salespattern;
    private String shopname;
    private String showOldprice;
    private String oldprice;
    private String itemstyle;
    private String salescount;*/

}
