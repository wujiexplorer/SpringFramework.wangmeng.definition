package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;
import java.math.BigDecimal;

/**1
 * Created by lidongri on 2018/12/4.
 */
public class ClientUserPointResp implements Serializable {

    private static final long serialVersionUID = 5039359706242065100L;
    private String org_code;

    private String ee_no;

    private BigDecimal general_point;

    private Integer exclusive_point;

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getEe_no() {
        return ee_no;
    }

    public void setEe_no(String ee_no) {
        this.ee_no = ee_no;
    }

    public BigDecimal getGeneral_point() {
        return general_point;
    }

    public void setGeneral_point(BigDecimal general_point) {
        this.general_point = general_point;
    }

    public Integer getExclusive_point() {
        return exclusive_point;
    }

    public void setExclusive_point(Integer exclusive_point) {
        this.exclusive_point = exclusive_point;
    }
}
