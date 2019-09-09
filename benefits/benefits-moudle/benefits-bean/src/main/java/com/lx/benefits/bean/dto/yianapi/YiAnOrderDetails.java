package com.lx.benefits.bean.dto.yianapi;


import com.lx.benefits.bean.base.dto.BaseQuery;

/**
 * Created by lidongri on 2018/11/15.
 */
public class YiAnOrderDetails extends BaseQuery {

    private static final long serialVersionUID = 1161881563436320648L;
    private String code;

    private String oid;

    private String com;

    private String nam;

    private String nu;

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }
}
