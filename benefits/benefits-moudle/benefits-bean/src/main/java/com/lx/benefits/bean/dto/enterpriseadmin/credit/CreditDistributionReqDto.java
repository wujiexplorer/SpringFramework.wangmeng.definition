package com.lx.benefits.bean.dto.enterpriseadmin.credit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @author unknow on 2018-12-05 21:41.
 */
@ApiModel("企业积分分配请求参数")
public class CreditDistributionReqDto {

    @ApiModelProperty(value = "企业积分类型 {PUTONG, JIERILIJIN, RENKEJILI}")
    @NotEmpty(message = "企业积分类型为空")
    private String creditType;

    @ApiModelProperty(value = "企业积分分配备注")
    private String remarks;

    @ApiModelProperty(value = "文件URL")
    @NotEmpty(message = "url地址不能为空")
    private String url;

    public String getCreditType() {
        return this.creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String
    toString() {
        return "CreditDistributionReqDto{" +
                "creditType='" + this.creditType + '\'' +
                ", remarks='" + this.remarks + '\'' +
                '}';
    }
}