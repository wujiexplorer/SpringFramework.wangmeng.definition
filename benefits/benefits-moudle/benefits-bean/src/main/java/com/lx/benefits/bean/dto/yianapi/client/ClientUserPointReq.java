package com.lx.benefits.bean.dto.yianapi.client;

import java.io.Serializable;

/**1
 * Created by lidongri on 2018/12/4.
 */
public class ClientUserPointReq extends ClientBaseReq implements Serializable {

    private static final long serialVersionUID = 5039359706242065100L;
    private String org_code;

    private String ee_no;

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
}
