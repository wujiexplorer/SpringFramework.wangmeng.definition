package com.lx.benefits.bean.dto.admin.finance;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @author unknow on 2018-12-06 02:36.
 */
@ApiModel("运营后台企业积分充值OR退款信息")
public class CreditExchangeListResDto {

    private List<CreditExchangeResDto> creditExchangeResDtoList;

    private Integer count;

    public List<CreditExchangeResDto> getCreditExchangeResDtoList() {
        return creditExchangeResDtoList;
    }

    public void setCreditExchangeResDtoList(List<CreditExchangeResDto> creditExchangeResDtoList) {
        this.creditExchangeResDtoList = creditExchangeResDtoList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}