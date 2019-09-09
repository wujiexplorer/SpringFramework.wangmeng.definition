package com.lx.benefits.bean.dto.sch.result;


import com.lx.benefits.bean.dto.jd.base.BaseDO;
import com.lx.benefits.bean.dto.sch.Element;

import java.util.List;

/**
 * Created by ldr on 2016/2/18.
 */
public class Aggregate extends BaseDO {

    private static final long serialVersionUID = 199498332053691288L;

    private String code;

    private String name;

    private List<Element> elements;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Aggregate() {
    }

    public Aggregate(String code, String name, List<Element> elements) {
        this.code = code;
        this.name = name;
        this.elements = elements;
    }

    public Aggregate(String code, Integer value) {
        this.code = code;
        this.count = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
