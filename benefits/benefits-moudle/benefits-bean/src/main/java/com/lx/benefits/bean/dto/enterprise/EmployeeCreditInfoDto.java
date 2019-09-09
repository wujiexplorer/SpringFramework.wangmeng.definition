package com.lx.benefits.bean.dto.enterprise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author unknow on 2018-12-27 03:17.
 */
@ApiModel("员工积分信息")
@Data
public class EmployeeCreditInfoDto {

    @ApiModelProperty(value = "员工标识id")
    private Long employeeId;

    private BigDecimal totalCredit;
    
    private List<EmployeeCreditDto> employeeCreditDtoList;
    
    private List<EmployeeOptCreditDto> employeeOptCreditDtoList;
}
