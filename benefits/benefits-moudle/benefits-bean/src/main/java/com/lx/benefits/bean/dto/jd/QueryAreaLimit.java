package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldr on 2017/3/3.
 */
public class QueryAreaLimit implements Serializable {

    private static final long serialVersionUID = -944233889121488135L;

    private List<String> skus;

    private Long province;

    private Long city;

    private Long county;

    private Long town;

    public QueryAreaLimit(List<String> skus, Long province, Long city, Long county, Long town) {
        this.skus = skus;
        this.province = province;
        this.city = city;
        this.county = county;
        this.town = town;
    }

    public List<String> getSkus() {
        return skus;
    }

    public void setSkus(List<String> skus) {
        this.skus = skus;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getCounty() {
        return county;
    }

    public void setCounty(Long county) {
        this.county = county;
    }

    public Long getTown() {
        return town;
    }

    public void setTown(Long town) {
        this.town = town;
    }
}
