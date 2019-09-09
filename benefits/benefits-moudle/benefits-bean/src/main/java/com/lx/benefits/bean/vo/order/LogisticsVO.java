package com.lx.benefits.bean.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LogisticsVO {
    /**
     *发货时间
     */
    @JsonFormat(pattern="YYYY-MM-dd HH:mm:ss")
    private Date shipTime;
    /**
     *快递公司，发货承运快递公司
     */
    private String shipExpress;
    /**
     *快递单号，发货承运快递单号
     */
    private String shipExpressNumber;
    /**
     *发货备注信息
     */
    private String remark;
    
    
}
