package com.lx.benefits.bean.dto.admin.order;

import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-29 02:25.
 */
@ApiModel("导入详情信息查询参数")
public class OrderImportItemQueryDto extends FLPageDto {
    @ApiModelProperty(value = "导入记录id")
    private Long importId;

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    @Override
    public String toString() {
        return "OrderImportItemQueryDto{" +
                "importId=" + importId +
                "} " + super.toString();
    }
}
