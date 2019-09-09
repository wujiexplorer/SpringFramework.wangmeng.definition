package com.lx.benefits.bean.dto.admin.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;

/**
 * Created by softw on 2019/1/15.
 */
@ApiModel("代下单基础信息")
public class OrderInfoDto extends BaseRowModel {

    private Long userid;

    @ExcelProperty(index = 0)
    private String loginName;

    @ExcelProperty(index = 1)
    private Long tid;

    /**用于立即购买**/
    @ExcelProperty(index = 2)
    private Long sku;

    @ExcelProperty(index = 3)
    private Long count;

    @ExcelProperty(index = 4)
    private Long campaignId;

    public Long getUserid() {
        return userid;
    }


    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
