package com.lx.benefits.bean.entity.prditeminfo;

import com.lx.benefits.bean.dto.jd.annotation.Virtual;

import java.util.Date;

public class ItemInfo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 供应商ID
     */
    private Long supplier_id;

    /**
     * 小类编号+4位流水码
     */
    private String spu;

    /**
     * spu名称
     */
    private String main_title;

    /**
     * 关联的品牌
     */
    private Integer brand_id;

    public Integer getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(Integer itemStyle) {
        this.itemStyle = itemStyle;
    }

    /**商品类型，0-实物商品，1-虚拟商品*/
    private Integer itemStyle;

    /**
     * 大类ID
     */
    private Integer large_id;

    /**
     * 中类ID
     */
    private Integer medium_id;

    /**
     * 小类ID
     */
    private Integer small_id;

    /**
     * 单位
     */
    private Integer unit_id;

    /**
     * 绑定层级
     */
    private String bind_level;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 创建人
     */
    private String create_user;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 修改人
     */
    private String update_user;

    /**
     * 商品来源 0自营 1京东 2 考拉
     */
    private Byte item_source;

    /**
     * 第三方ItemID
     */
    private String ref_id;

    /**
     * 商品类型，0-实物商品，1-虚拟商品
     */
    private Integer item_style;

    /**
     * 服务说明
     */
    private String service_description;


    /**单位名称 不做数据的存储,冗余查询*/
    @Virtual
    private String unitName;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Long supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu == null ? null : spu.trim();
    }

    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title == null ? null : main_title.trim();
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getLarge_id() {
        return large_id;
    }

    public void setLarge_id(Integer large_id) {
        this.large_id = large_id;
    }

    public Integer getMedium_id() {
        return medium_id;
    }

    public void setMedium_id(Integer medium_id) {
        this.medium_id = medium_id;
    }

    public Integer getSmall_id() {
        return small_id;
    }

    public void setSmall_id(Integer small_id) {
        this.small_id = small_id;
    }

    public Integer getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Integer unit_id) {
        this.unit_id = unit_id;
    }

    public String getBind_level() {
        return bind_level;
    }

    public void setBind_level(String bind_level) {
        this.bind_level = bind_level == null ? null : bind_level.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user == null ? null : create_user.trim();
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user == null ? null : update_user.trim();
    }

    public Byte getItem_source() {
        return item_source;
    }

    public void setItem_source(Byte item_source) {
        this.item_source = item_source;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id == null ? null : ref_id.trim();
    }

    public Integer getItem_style() {
        return item_style;
    }

    public void setItem_style(Integer item_style) {
        this.item_style = item_style;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description == null ? null : service_description.trim();
    }
}