package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import lombok.Data;

/**
 * @author slong
 */
@Data
public class EmployeeRecoveryReqDto {

    private Long employeeId;

    /**
     * 1:全部积分  2：普通积分  3：节日积分
     */
    private Integer creditStatus;

    private Long enterprId;

    private String remark;

}
