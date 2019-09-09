package com.lx.benefits.bean.param.order;

import com.apollo.common.bean.bo.BasePageQueryBO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class RefundQueryParam extends BasePageQueryBO {

    private Long itemOrderNumber;
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date applyCreateTimeStart;
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date applyCreateTimeEnd;

    private Long sellerId;

    private Integer status;
}
