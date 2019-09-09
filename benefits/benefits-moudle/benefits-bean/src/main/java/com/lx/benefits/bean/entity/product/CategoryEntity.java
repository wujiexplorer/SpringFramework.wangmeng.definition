package com.lx.benefits.bean.entity.product;

import lombok.Data;

import java.util.Date;

/**
 * 分类 商品分类表 basis_category
 *
 * @author gaosong
 * @date 2019-01-29
 */
@Data
public class CategoryEntity {

    /**
     * 分类id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类英文名称
     */
    private String nameEn;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 状态 状态,1为可用,0为不可用
     */
    private Integer status;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updatedUser;
    /**
     * 更新时间
     */
    private Date updatedTime;

    public CategoryEntity() {
    }

    public CategoryEntity(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
        this.status = 1;
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }

    public CategoryEntity(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.status = 1;
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }
}
