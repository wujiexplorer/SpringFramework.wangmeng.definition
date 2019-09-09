package com.lx.benefits.bean.dto.yx;

import java.io.Serializable;

/**
 * @Author: mickey
 * @Date: 2018/8/131549
 * @Description:
 */
public class YXSpecGroup implements Serializable {


    /**
     * 规格ID
     */
    private Long id;

    /**
     * 规格名
     */
    private String name;

    /**
     * 规格类型 0-文字 1 图片
     */
    private Integer type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
