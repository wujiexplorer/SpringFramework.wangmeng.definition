package com.lx.benefits.bean.entity.orderdelivery;

import java.util.Date;

public class OrderDelivery {
    /**
     * PK
     */
    private Long id;

    /**
     * 物流公司ID
     */
    private String company_id;

    /**
     * 物流公司名称
     */
    private String company_name;

    /**
     * 订单编号
     */
    private Long parent_order_code;

    /**
     * 子订单编号
     */
    private Long order_code;

    /**
     * 物流单号
     */
    private String package_no;

    /**
     * 发货时间
     */
    private Date delivery_time;

    /**
     * 联系信息
     */
    private String link_info;

    /**
     * 发货城市描述
     */
    private String start_city;

    /**
     * 收货城市描述
     */
    private String end_city;

    /**
     * 退货信息
     */
    private String refund_info;

    /**
     * 供应商ID
     */
    private Long supplier_id;

    /**
     * 供应商名称
     */
    private String supplier_name;

    /**
     * 运费
     */
    private Double freight;

    /**
     * 重量，单位为kg，精确到g
     */
    private Double weight;

    /**
     * 是否成功推送给快递100平台,0:没有成功，1:成功
     */
    private Boolean post_kuaidi100;

    /**
     * 推送快递100次数
     */
    private Boolean post_kuaidi100_times;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 创建人ID
     */
    private String create_user;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 修改人ID
     */
    private String update_user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id == null ? null : company_id.trim();
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name == null ? null : company_name.trim();
    }

    public Long getParent_order_code() {
        return parent_order_code;
    }

    public void setParent_order_code(Long parent_order_code) {
        this.parent_order_code = parent_order_code;
    }

    public Long getOrder_code() {
        return order_code;
    }

    public void setOrder_code(Long order_code) {
        this.order_code = order_code;
    }

    public String getPackage_no() {
        return package_no;
    }

    public void setPackage_no(String package_no) {
        this.package_no = package_no == null ? null : package_no.trim();
    }

    public Date getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getLink_info() {
        return link_info;
    }

    public void setLink_info(String link_info) {
        this.link_info = link_info == null ? null : link_info.trim();
    }

    public String getStart_city() {
        return start_city;
    }

    public void setStart_city(String start_city) {
        this.start_city = start_city == null ? null : start_city.trim();
    }

    public String getEnd_city() {
        return end_city;
    }

    public void setEnd_city(String end_city) {
        this.end_city = end_city == null ? null : end_city.trim();
    }

    public String getRefund_info() {
        return refund_info;
    }

    public void setRefund_info(String refund_info) {
        this.refund_info = refund_info == null ? null : refund_info.trim();
    }

    public Long getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Long supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name == null ? null : supplier_name.trim();
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getPost_kuaidi100() {
        return post_kuaidi100;
    }

    public void setPost_kuaidi100(Boolean post_kuaidi100) {
        this.post_kuaidi100 = post_kuaidi100;
    }

    public Boolean getPost_kuaidi100_times() {
        return post_kuaidi100_times;
    }

    public void setPost_kuaidi100_times(Boolean post_kuaidi100_times) {
        this.post_kuaidi100_times = post_kuaidi100_times;
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
}