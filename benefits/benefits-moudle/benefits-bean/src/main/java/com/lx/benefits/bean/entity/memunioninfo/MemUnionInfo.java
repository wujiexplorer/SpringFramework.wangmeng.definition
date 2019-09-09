package com.lx.benefits.bean.entity.memunioninfo;

import java.util.Date;

public class MemUnionInfo {
    private Long id;

    /**
     * 用户ID
     */
    private Long mem_id;

    /**
     * 关联值
     */
    private String union_val;

    /**
     * 链接类型（1. 微信）
     */
    private Integer type;

    /**
     * 是否删除
     */
    private Integer is_deleted;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 联合登录用户信息json格式
     */
    private String union_data;

    /**
     * 联合登录用户公司code
     */
    private String org_code;

    /**
     * 联合登录用户公司工号
     */
    private String ee_no;

    /**
     * 联合登录用户用户id
     */
    private String union_user_id;

    private String root_org_code;

    public String getRoot_org_code() {
        return root_org_code;
    }

    public void setRoot_org_code(String root_org_code) {
        this.root_org_code = root_org_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMem_id() {
        return mem_id;
    }

    public void setMem_id(Long mem_id) {
        this.mem_id = mem_id;
    }

    public String getUnion_val() {
        return union_val;
    }

    public void setUnion_val(String union_val) {
        this.union_val = union_val == null ? null : union_val.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUnion_data() {
        return union_data;
    }

    public void setUnion_data(String union_data) {
        this.union_data = union_data == null ? null : union_data.trim();
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code == null ? null : org_code.trim();
    }

    public String getEe_no() {
        return ee_no;
    }

    public void setEe_no(String ee_no) {
        this.ee_no = ee_no == null ? null : ee_no.trim();
    }

    public String getUnion_user_id() {
        return union_user_id;
    }

    public void setUnion_user_id(String union_user_id) {
        this.union_user_id = union_user_id == null ? null : union_user_id.trim();
    }
}