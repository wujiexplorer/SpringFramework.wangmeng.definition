package com.lx.benefits.bean.entity.voucher;

import lombok.Data;

import java.io.Serializable;

/**
 * User:wangmeng
 * Date:2019/8/21
 * Time:11:28
 * Version:2.x
 * Description:TODO
 **/
@Data
public class VoucherBrandInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer brandId;

    private String brandName;
}
