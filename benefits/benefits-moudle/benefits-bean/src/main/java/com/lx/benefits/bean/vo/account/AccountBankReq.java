package com.lx.benefits.bean.vo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:16:58
 * Version:2.x
 * Description:TODO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBankReq {

    private String startTime;

    private Long enterprId;

    private String endTime;

    private Date startDate;

    private Date endDate;

    private Long number;

    private Integer status;

    private Long parentNumber;

    private String thirdOrderNumber;

    private String supplierName;

    private String goodsName;

    private Integer skuCode;

    private String categoryName;

    private String enterprName;

    private Integer agentId;

    private String agentName;

    private String employeeName;

    private String employeeNo;

    private Long payNumber;

    private String settleNumber;

    private Integer channel;

    private Integer reverseStatus;

    private Integer supplierId;

    private Integer categoryId;

    private Integer categoryId2;

    private Integer categoryId3;

    private Integer offset;

    private Integer limit;

    @ApiModelProperty(value = "页码")
    private Integer page;
    @ApiModelProperty(value = "每页数据条数")
    private Integer pageSize;

    public Integer getPage() {
        return null == this.page || this.page < 1 ? 1 : this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (this.getPage() - 1) * this.getPageSize();
    }

}
