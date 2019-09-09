package com.lx.benefits.bean.dto.order;

import com.lx.benefits.bean.base.dto.BaseVO;
import lombok.Data;

/**
 * User:wangmeng
 * ate:2019/4/23
 * Time:16:01
 * Verision:2.x
 * Description:TODO
 **/
@Data
public class OrderInfoValidateVO implements BaseVO {

    private Integer skuId;
    private String areaId;
    private Integer stockStateId;
    private String stockStateDesc;
    private Integer remainNum;
}
