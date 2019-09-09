package com.lx.benefits.bean.dto.sch.result;


import com.lx.benefits.bean.dto.jd.base.BaseDO;

import java.util.List;

/**
 * Created by ldr on 9/23/2016.
 */
public class SearchResultBase extends BaseDO {

    private static final long serialVersionUID = 462784058443067401L;

    private String status;

    private List<SearchError> errors;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SearchError> getErrors() {
        return errors;
    }

    public void setErrors(List<SearchError> errors) {
        this.errors = errors;
    }
}
