package com.lx.benefits.bean.entity.orderitem;

import java.util.Date;

public class OrderItem {
    /**
     * PK
     */
    private Long id;

    /**
     * 类型（1：商品行。2：赠品行）
     */
    private Byte type;

    /**
     * 订单ID
     */
    private Long parent_order_id;

    private Long parent_order_code;

    /**
     * 子订单ID
     */
    private Long order_id;

    private Long order_code;

    /**
     * 会员id
     */
    private Long member_id;

    /**
     * 商品ID
     */
    private Long spu_id;

    /**
     * 商品编号
     */
    private String spu_code;

    /**
     * 商品名称
     */
    private String spu_name;

    /**
     * 品牌ID
     */
    private Long brand_id;

    /**
     * 品牌名称
     */
    private String brand_Name;

    /**
     * SKU ID
     */
    private Long sku_id;

    /**
     * SKU编号
     */
    private String sku_code;

    /**
     * SKU条形码
     */
    private String bar_code;

    /**
     * 图片路径
     */
    private String img;

    /**
     * 商品版本
     */
    private String sku_version;

    /**
     * 主题ID
     */
    private Long topic_id;

    /**
     * 供应商ID
     */
    private Long supplier_id;

    /**
     * 供应商名称
     */
    private String supplier_name;

    /**
     * 仓库ID
     */
    private Long warehouse_id;

    /**
     * 入库批号
     */
    private String batch_code;

    /**
     * 销售类型
     */
    private Byte sales_type;

    /**
     * 销售属性
     */
    private String sales_property;

    /**
     * 颜色
     */
    private String color;

    /**
     * 尺码
     */
    private String size;

    /**
     * 有效期-开始时间
     */
    private Date start_time;

    /**
     * 有效期-结束时间
     */
    private Date end_time;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 商品净数量-多件装商品数量
     */
    private Integer unit_quantity;

    /**
     * 商品独立包装数
     */
    private Integer wrap_quantity;

    /**
     * 吊牌价/市场价
     */
    private Double list_price;

    /**
     * 销售价
     */
    private Double sales_price;

    /**
     * 价格
     */
    private Double price;

    /**
     * 实付行小计
     */
    private Double sub_total;

    /**
     * 应付行小计
     */
    private Double original_sub_total;

    /**
     * 原始运费
     */
    private Double orig_freight;

    /**
     * 原始税费
     */
    private Double orig_tax_fee;

    /**
     * 商品总金额
     */
    private Double item_amount;

    /**
     * 行运费
     */
    private Double freight;

    /**
     * 订单项使用积分数 
     */
    private Double points;

    /**
     * 商品重量
     */
    private Double weight;

    /**
     * 商品净重
     */
    private Double weight_net;

    /**
     * 退款状态
     */
    private Byte refund_status;

    /**
     * 税率
     */
    private Double tax_rate;

    /**
     * 增值税率
     */
    private Float added_value_rate;

    /**
     * 费消税率
     */
    private Float excise_rate;

    /**
     * 海关税率
     */
    private Float customs_rate;

    /**
     * 行邮税号
     */
    private String parcel_tax_id;

    /**
     * 下单IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 货号
     */
    private String product_code;

    /**
     * 税费
     */
    private Double tax_fee;

    /**
     * 退货限制天数
     */
    private Integer refund_days;

    /**
     * 分销提佣比率
     */
    private Double commision_rate;

    /**
     * 单位
     */
    private String unit;

    /**
     * 计量单位
     */
    private Long unit_id;

    /**
     * 佣金比率
     */
    private Double platform_rate;

    /**
     * 结算状态
     */
    private Byte settle_status;

    /**
     * 优惠券金额
     */
    private Double order_coupon_amount;

    /**
     * 红包金额
     */
    private Double coupon_amount;

    /**
     * 合同ID
     */
    private Long contract_id;

    /**
     * 原产地
     */
    private Long country_id;

    /**
     * 商品-优惠金额
     */
    private Double item_condition_coupon;

    /**
     * 商品-红包优惠金额
     */
    private Double item_coupon;

    /**
     * 商品-使用积分数
     */
    private Double item_point;

    /**
     * 运费-红包优惠金额
     */
    private Double freight_coupon;

    /**
     * 运费-使用积分数
     */
    private Double freight_point;

    /**
     * 税费-红包优惠金额
     */
    private Double tax_coupon;

    /**
     * 税费-使用积分数
     */
    private Double tax_point;

    /**
     * 商品类型：0-实物商品，1-虚拟商品
     */
    private Integer item_style;

    /**
     * 虚拟商品兑换码
     */
    private String cdkey;

    /**
     * 折扣比率
     */
    private Double discount_rate;

    /**
     * 折扣金额
     */
    private Double discount_amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getParent_order_id() {
        return parent_order_id;
    }

    public void setParent_order_id(Long parent_order_id) {
        this.parent_order_id = parent_order_id;
    }

    public Long getParent_order_code() {
        return parent_order_code;
    }

    public void setParent_order_code(Long parent_order_code) {
        this.parent_order_code = parent_order_code;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getOrder_code() {
        return order_code;
    }

    public void setOrder_code(Long order_code) {
        this.order_code = order_code;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public Long getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Long spu_id) {
        this.spu_id = spu_id;
    }

    public String getSpu_code() {
        return spu_code;
    }

    public void setSpu_code(String spu_code) {
        this.spu_code = spu_code == null ? null : spu_code.trim();
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name == null ? null : spu_name.trim();
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        this.brand_Name = brand_Name == null ? null : brand_Name.trim();
    }

    public Long getSku_id() {
        return sku_id;
    }

    public void setSku_id(Long sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_code() {
        return sku_code;
    }

    public void setSku_code(String sku_code) {
        this.sku_code = sku_code == null ? null : sku_code.trim();
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code == null ? null : bar_code.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getSku_version() {
        return sku_version;
    }

    public void setSku_version(String sku_version) {
        this.sku_version = sku_version == null ? null : sku_version.trim();
    }

    public Long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Long topic_id) {
        this.topic_id = topic_id;
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

    public Long getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(Long warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getBatch_code() {
        return batch_code;
    }

    public void setBatch_code(String batch_code) {
        this.batch_code = batch_code == null ? null : batch_code.trim();
    }

    public Byte getSales_type() {
        return sales_type;
    }

    public void setSales_type(Byte sales_type) {
        this.sales_type = sales_type;
    }

    public String getSales_property() {
        return sales_property;
    }

    public void setSales_property(String sales_property) {
        this.sales_property = sales_property == null ? null : sales_property.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnit_quantity() {
        return unit_quantity;
    }

    public void setUnit_quantity(Integer unit_quantity) {
        this.unit_quantity = unit_quantity;
    }

    public Integer getWrap_quantity() {
        return wrap_quantity;
    }

    public void setWrap_quantity(Integer wrap_quantity) {
        this.wrap_quantity = wrap_quantity;
    }

    public Double getList_price() {
        return list_price;
    }

    public void setList_price(Double list_price) {
        this.list_price = list_price;
    }

    public Double getSales_price() {
        return sales_price;
    }

    public void setSales_price(Double sales_price) {
        this.sales_price = sales_price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSub_total() {
        return sub_total;
    }

    public void setSub_total(Double sub_total) {
        this.sub_total = sub_total;
    }

    public Double getOriginal_sub_total() {
        return original_sub_total;
    }

    public void setOriginal_sub_total(Double original_sub_total) {
        this.original_sub_total = original_sub_total;
    }

    public Double getOrig_freight() {
        return orig_freight;
    }

    public void setOrig_freight(Double orig_freight) {
        this.orig_freight = orig_freight;
    }

    public Double getOrig_tax_fee() {
        return orig_tax_fee;
    }

    public void setOrig_tax_fee(Double orig_tax_fee) {
        this.orig_tax_fee = orig_tax_fee;
    }

    public Double getItem_amount() {
        return item_amount;
    }

    public void setItem_amount(Double item_amount) {
        this.item_amount = item_amount;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight_net() {
        return weight_net;
    }

    public void setWeight_net(Double weight_net) {
        this.weight_net = weight_net;
    }

    public Byte getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(Byte refund_status) {
        this.refund_status = refund_status;
    }

    public Double getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(Double tax_rate) {
        this.tax_rate = tax_rate;
    }

    public Float getAdded_value_rate() {
        return added_value_rate;
    }

    public void setAdded_value_rate(Float added_value_rate) {
        this.added_value_rate = added_value_rate;
    }

    public Float getExcise_rate() {
        return excise_rate;
    }

    public void setExcise_rate(Float excise_rate) {
        this.excise_rate = excise_rate;
    }

    public Float getCustoms_rate() {
        return customs_rate;
    }

    public void setCustoms_rate(Float customs_rate) {
        this.customs_rate = customs_rate;
    }

    public String getParcel_tax_id() {
        return parcel_tax_id;
    }

    public void setParcel_tax_id(String parcel_tax_id) {
        this.parcel_tax_id = parcel_tax_id == null ? null : parcel_tax_id.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code == null ? null : product_code.trim();
    }

    public Double getTax_fee() {
        return tax_fee;
    }

    public void setTax_fee(Double tax_fee) {
        this.tax_fee = tax_fee;
    }

    public Integer getRefund_days() {
        return refund_days;
    }

    public void setRefund_days(Integer refund_days) {
        this.refund_days = refund_days;
    }

    public Double getCommision_rate() {
        return commision_rate;
    }

    public void setCommision_rate(Double commision_rate) {
        this.commision_rate = commision_rate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Long getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Long unit_id) {
        this.unit_id = unit_id;
    }

    public Double getPlatform_rate() {
        return platform_rate;
    }

    public void setPlatform_rate(Double platform_rate) {
        this.platform_rate = platform_rate;
    }

    public Byte getSettle_status() {
        return settle_status;
    }

    public void setSettle_status(Byte settle_status) {
        this.settle_status = settle_status;
    }

    public Double getOrder_coupon_amount() {
        return order_coupon_amount;
    }

    public void setOrder_coupon_amount(Double order_coupon_amount) {
        this.order_coupon_amount = order_coupon_amount;
    }

    public Double getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(Double coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public Long getContract_id() {
        return contract_id;
    }

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public Double getItem_condition_coupon() {
        return item_condition_coupon;
    }

    public void setItem_condition_coupon(Double item_condition_coupon) {
        this.item_condition_coupon = item_condition_coupon;
    }

    public Double getItem_coupon() {
        return item_coupon;
    }

    public void setItem_coupon(Double item_coupon) {
        this.item_coupon = item_coupon;
    }

    public Double getItem_point() {
        return item_point;
    }

    public void setItem_point(Double item_point) {
        this.item_point = item_point;
    }

    public Double getFreight_coupon() {
        return freight_coupon;
    }

    public void setFreight_coupon(Double freight_coupon) {
        this.freight_coupon = freight_coupon;
    }

    public Double getFreight_point() {
        return freight_point;
    }

    public void setFreight_point(Double freight_point) {
        this.freight_point = freight_point;
    }

    public Double getTax_coupon() {
        return tax_coupon;
    }

    public void setTax_coupon(Double tax_coupon) {
        this.tax_coupon = tax_coupon;
    }

    public Double getTax_point() {
        return tax_point;
    }

    public void setTax_point(Double tax_point) {
        this.tax_point = tax_point;
    }

    public Integer getItem_style() {
        return item_style;
    }

    public void setItem_style(Integer item_style) {
        this.item_style = item_style;
    }

    public String getCdkey() {
        return cdkey;
    }

    public void setCdkey(String cdkey) {
        this.cdkey = cdkey == null ? null : cdkey.trim();
    }

    public Double getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(Double discount_rate) {
        this.discount_rate = discount_rate;
    }

    public Double getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(Double discount_amount) {
        this.discount_amount = discount_amount;
    }
}