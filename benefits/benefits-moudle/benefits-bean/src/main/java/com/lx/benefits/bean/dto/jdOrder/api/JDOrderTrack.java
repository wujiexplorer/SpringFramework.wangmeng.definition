package com.lx.benefits.bean.dto.jdOrder.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldr on 2017/3/16.
 */
public class JDOrderTrack implements Serializable {

    private static final long serialVersionUID = 9184824644486398029L;

    private String jdOrderId;

    List<JDOrderTrackLine> orderTrack;

    public String getJdOrderId() {
        return jdOrderId;
    }

    public void setJdOrderId(String jdOrderId) {
        this.jdOrderId = jdOrderId;
    }

    public List<JDOrderTrackLine> getOrderTrack() {
        return orderTrack;
    }

    public void setOrderTrack(List<JDOrderTrackLine> orderTrack) {
        this.orderTrack = orderTrack;
    }
}
