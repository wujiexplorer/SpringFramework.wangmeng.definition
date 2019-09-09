package com.lx.benefits.bean.param.pay;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class PayCallbackParam implements Serializable {

    private static final long serialVersionUID = -6396631974053071988L;
    private String payMark;
    private String payTid;
    private Integer payChannel;
}
