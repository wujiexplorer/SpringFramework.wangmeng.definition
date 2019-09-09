package com.lx.benefits.bean.entity;


import com.lx.benefits.bean.enums.YNEnum;
import com.lx.benefits.bean.util.DateUtil;


public abstract class Entity {
    /**
     * 创建时间
     */
    private Long created;

    /**
     * 更新时间
     */
    private Long updated;

    /**
     * 0 未删除 1已删除
     */
    private Integer isDeleted;

    public Long getCreated() {
        return created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void checkBeforeInsert() {
        Long now = DateUtil.getNowTimestamp10();
        if(null == this.created) {
            this.created = now;
        }
        if(null == this.updated) {
            this.updated = now;
        }
        this.isDeleted = YNEnum.N.val();
    }

    public void checkBeforeUpdate() {
        Long now = DateUtil.getNowTimestamp10();
        if(null == this.updated) {
            this.updated = now;
        }
    }
}
