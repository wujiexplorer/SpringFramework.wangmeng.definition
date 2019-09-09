package com.lx.benefits.bean.entity.jd;

import lombok.Data;

@Data
public class PrdJdBaseItem {
    private Long id;

    private String jdSku;

    private String jdName;

    private String jdCategory;

    private String jdBrandName;

    private Double marketPrice;

    private Double jdPrice;

    private Double jdProtocolPrice;

    private Double jdRate;

    private Integer status;

    private Long bigCatId;

    private Long middleCatId;

    private Long smallCatId;

    private String bigCatName;

    private String middleCatName;

    private String smallCatName;

    private String catName;

    private String barcode;

    private String isselect;

    private Integer inventory;

    private Byte hasPic;

    private String inventoryDesc;

    private String hasPicDesc;

}