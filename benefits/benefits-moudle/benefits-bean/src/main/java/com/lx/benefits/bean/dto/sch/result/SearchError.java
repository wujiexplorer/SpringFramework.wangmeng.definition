package com.lx.benefits.bean.dto.sch.result;


import com.lx.benefits.bean.dto.jd.base.BaseDO;

/**
 * Created by ldr on 2016/3/3.
 */
public class SearchError extends BaseDO {

    private static final long serialVersionUID = -8174136205294297886L;

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
