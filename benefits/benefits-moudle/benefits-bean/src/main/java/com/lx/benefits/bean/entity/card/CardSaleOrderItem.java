package com.lx.benefits.bean.entity.card;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class CardSaleOrderItem implements Serializable {
    private Integer id;
   
    private Integer amount;
    @NotNull(message = "面值不能为空")
    private Integer amountId;
    @NotNull(message = "会员卡数量不能为空")
    private Integer num;

    private Integer saleOrderId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountId() {
        return amountId;
    }

    public void setAmountId(Integer amountId) {
        this.amountId = amountId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Integer saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}