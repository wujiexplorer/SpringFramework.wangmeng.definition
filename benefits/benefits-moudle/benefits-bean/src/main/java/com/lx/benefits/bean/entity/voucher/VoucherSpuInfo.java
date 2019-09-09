package com.lx.benefits.bean.entity.voucher;

import lombok.Data;

import java.io.Serializable;

/**
 * User:wangmeng
 * Date:2019/8/21
 * Time:11:23
 * Version:2.x
 * Description:TODO
 **/
@Data
public class VoucherSpuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long spuCode;

    private String productImage;

    private String productName;

    private String supplierName;

    private String brandName;
}
