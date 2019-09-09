package com.lx.benefits.bean.sdk.jd;

import java.io.Serializable;

/**
 * Created by ldr on 2017/3/15.
 */
public class JDFreight implements Serializable {

    private Double freight;
    private Double baseFreight;

    private Double remoteRegionFreight;

    private String remoteSku;

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getBaseFreight() {
        return baseFreight;
    }

    public void setBaseFreight(Double baseFreight) {
        this.baseFreight = baseFreight;
    }

    public Double getRemoteRegionFreight() {
        return remoteRegionFreight;
    }

    public void setRemoteRegionFreight(Double remoteRegionFreight) {
        this.remoteRegionFreight = remoteRegionFreight;
    }

    public String getRemoteSku() {
        return remoteSku;
    }

    public void setRemoteSku(String remoteSku) {
        this.remoteSku = remoteSku;
    }
}
