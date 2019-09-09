package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author unknow on 2018-12-23 02:19.
 */
@ApiModel("企业员工导入记录信息")
public class EmployeeImportResDto {
    @ApiModelProperty(value = "员工导入记录id")
    private Long importId;
    @ApiModelProperty(value = "导入的文件路径")
    private String fileUrl;
    @ApiModelProperty(value = "操作人")
    private String optUserName;
    @ApiModelProperty(value = "导入员工数")
    private Long importCount;
    @ApiModelProperty(value = "员工导入时间")
    private String importTime;

    public Long getImportId() {
        return importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }

    public Long getImportCount() {
        return importCount;
    }

    public void setImportCount(Long importCount) {
        this.importCount = importCount;
    }

    public String getImportTime() {
        return importTime;
    }

    public void setImportTime(String importTime) {
        this.importTime = importTime;
    }

    @Override
    public String toString() {
        return "EmployeeImportResDto{" +
                "importId=" + importId +
                ", fileUrl='" + fileUrl + '\'' +
                ", optUserName='" + optUserName + '\'' +
                ", importCount=" + importCount +
                ", importTime='" + importTime + '\'' +
                '}';
    }
}
