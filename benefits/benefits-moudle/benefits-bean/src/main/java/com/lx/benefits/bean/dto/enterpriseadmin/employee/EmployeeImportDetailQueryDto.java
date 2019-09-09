package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-29 02:25.
 */
@ApiModel("员工导入详情信息查询参数")
public class EmployeeImportDetailQueryDto extends FLPageDto {
    @ApiModelProperty(value = "员工导入记录id")
    private Long importId;

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    @Override
    public String toString() {
        return "EmployeeImportDetailQueryDto{" +
                "importId=" + importId +
                "} " + super.toString();
    }
}
