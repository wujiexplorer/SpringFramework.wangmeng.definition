package com.lx.benefits.bean.entity.suborder;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SubOrder {
    /**
     * PK
     */
    private Long id;

    /**
     * 子订单编号(11+yymmdd+LENGTH(0,id))
     */
    private Long order_code;

    /**
     * 订单PK
     */
    private Long parent_order_id;

    /**
     * 父订单编号
     */
    private Long parent_order_code;

    /**
     * 订单状态（新订单-1,待付款-2,待转移-3,待发货-4,待收货-5,已收货-6,已取消-0,退款完成-100）
     */
    private Integer order_status;

    /**
     * 订单类型（1：一般订单（入仓）。2：平台（不入仓））
     */
    private Byte type;

    /**
     * 子单商品总数量
     */
    private Integer quantity;

    /**
     * 子单实付总价
     */
    private BigDecimal total;

    /**
     * 商品实际总价
     */
    private BigDecimal item_total;

    /**
     * 子单应付总价
     */
    private BigDecimal original_total;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 子单运费
     */
    private BigDecimal freight;

    /**
     * 使用余额
     */
    private BigDecimal balance;

    /**
     * 使用积分
     */
    private BigDecimal points;

    /**
     * 供应商ID
     */
    private Long supplier_id;

    /**
     * 供应商名称
     */
    private String supplier_name;

    /**
     * 供应商别名
     */
    private String supplier_alias;

    /**
     * 仓库ID
     */
    private Long warehouse_id;

    /**
     * 仓库名称
     */
    private String warehouse_name;

    /**
     * 推送标识(转成二进制）
     */
    private Short put_sign;

    /**
     * 会员ID
     */
    private Long member_id;

    /**
     * 是否删除（1.删除 2. 不删除）
     */
    private Boolean deleted;

    /**
     * 订单完成时间
     */
    private Date done_time;

    /**
     * 速递人员ID
     */
    private Long fast_user_id;

    /**
     * 收货人名称
     */
    private String consignee_name;

    /**
     * 收货人手机
     */
    private String consignee_mobile;

    /**
     * 用户账户名
     */
    private String account_name;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 取消原因
     */
    private String cancel_reason;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 子单修改时间
     */
    private Date update_time;

    /**
     * 发货时间
     */
    private Date delivered_time;

    /**
     * 税费
     */
    private BigDecimal tax_fee;

    /**
     * 支付方式
     */
    private Byte pay_type;

    /**
     * 支付途径
     */
    private Byte pay_way;

    /**
     * 支付时间
     */
    private Date pay_time;

    /**
     * 支付号
     */
    private String pay_code;

    /**
     * 支付公司编码
     */
    private String pay_company_code;

    /**
     * 海淘渠道ID
     */
    private Long sea_channel;

    /**
     * 海淘电商企业code
     */
    private String custom_code;

    /**
     * 海淘电商企业名字
     */
    private String org_name;

    /**
     * 推送到仓库的状态（0:等待推送，1：已推送，2：推送失败）
     */
    private Byte put_status;

    /**
     * 海淘渠道名称
     */
    private String sea_channel_name;

    /**
     * 订单来源
     */
    private Byte source;

    /**
     * 推广员ID
     */
    private Long promoter_id;

    /**
     * 分销员ID
     */
    private Long shop_promoter_id;

    /**
     * 扫码关注推广员ID
     */
    private Long scan_promoter_id;

    /**
     * 订单来源（用于数据统计）
     */
    private Byte track_source;

    /**
     * 分销渠道代码
     */
    private String channel_code;

    /**
     * 团Id
     */
    private Long group_id;

    /**
     * 清关状态
     */
    private Integer clearance_status;

    /**
     * 订单推送海关状态
     */
    private Integer put_order_status;

    /**
     * 清关单推送海关状态
     */
    private Integer put_personalgoods_status;

    /**
     * 运单推送海关状态
     */
    private Integer put_waybill_status;

    /**
     * 支付单推送海关状态
     */
    private Integer put_pay_status;

    /**
     * 订单推送海关次数
     */
    private Integer put_customs_times;

    /**
     * 接收手机号码
     */
    private String receive_tel;

    /**
     * 虚拟商品兑换码
     */
    private String cdkey;

    /**
     * 0-实物订单，1-服务订单
     */
    private Integer order_type;

    /**
     * 折扣比率
     */
    private Double discount_rate;

    /**
     * 折扣金额
     */
    private BigDecimal discount_amount;

    private Byte invoice_type;

    /**
     * 企业分销ID
     */
    private Long ent_promoter_id;

    /**
     * 员工所属企业ID
     */
    private Long enterprise_id;
}