package com.lx.benefits.bean.entity.employeecreditinfo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用于操作企业用户积分dto
 * Created by lidongri on 2019/1/5.
 */
@Data
public class EmployeeCreditOperateDto implements Serializable {

    private static final long serialVersionUID = -5232591295131419032L;
    private Long creditId;

    private BigDecimal credit;

    private Long employeeId;

    private BigDecimal oriCredit;

}
