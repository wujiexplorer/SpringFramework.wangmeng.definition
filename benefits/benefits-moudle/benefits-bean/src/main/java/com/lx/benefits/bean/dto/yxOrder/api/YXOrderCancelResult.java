package com.lx.benefits.bean.dto.yxOrder.api;

import java.io.Serializable;

/**
 * Created by ldr on 2017/6/8.
 */
public class YXOrderCancelResult implements Serializable {

    private static final long serialVersionUID = -6964376604684424929L;
    private Integer cancelStatus;

    private String rejectReason;

    public Integer getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Integer cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
