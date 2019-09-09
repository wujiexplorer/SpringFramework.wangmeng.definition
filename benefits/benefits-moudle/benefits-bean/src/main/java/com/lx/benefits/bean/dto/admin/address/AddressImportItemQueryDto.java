package com.lx.benefits.bean.dto.admin.address;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-29 02:25.
 */
@ApiModel("导入详情信息查询参数")
public class AddressImportItemQueryDto extends FLPageDto {
    @ApiModelProperty(value = "导入记录id")
    private Long importId;


    @ApiModelProperty(value = "状态")
    private Long status;

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    @Override
    public String toString() {
        return "AddressImportItemQueryDto{" +
                "importId=" + importId +
                "status=" + status +
                "} " + super.toString();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
