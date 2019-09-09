package com.lx.benefits.bean.dto.jd;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by ldr on 2017/3/16.
 */
public class JDMessage implements Serializable {

    private static final long serialVersionUID = -6329531444337164792L;

    private Long id;

    private Map<String,String> result;

    private Integer type;

    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
