package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author slong
 */
@Data
public class EmployeeCreditResDto {

    private Long ownerUserId;
    /**
     * 节日礼金id
     */
    private Long campaignId;
    /**
     * 节日礼金名称
     */
    private String campaignName;

    private Integer campaignStatus;
    /**
     * 积分类型{1: 普通积分;2: 节日积分; 3: 认可激励积分}
     */
    private Integer creditType;
    /**
     * 积分描述
     */
    private String creditTypeDesc;
    /**
     * 积分
     */
    private BigDecimal credit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date optTime;

}
