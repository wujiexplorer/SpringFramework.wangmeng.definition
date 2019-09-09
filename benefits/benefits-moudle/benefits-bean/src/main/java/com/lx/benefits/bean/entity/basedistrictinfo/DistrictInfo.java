package com.lx.benefits.bean.entity.basedistrictinfo;


import com.lx.benefits.bean.entity.Entity;
import io.swagger.models.auth.In;

/**
 * 积分操作记录
 *
 * @author luojie
 */
public class DistrictInfo extends Entity {

    private Long id;

    private String name;

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
    
}