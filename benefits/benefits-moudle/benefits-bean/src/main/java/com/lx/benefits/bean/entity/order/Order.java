package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: Order
* @Description:
* @author wind
* @date 2019-3-10
*/
@Data
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
     *主键ID
     */
	private Long id;
    /**
     *订单编号
     */
	private Long number;
    /**
     *父订单ID，商品级订单的父订单ID为店铺级订单ID，店铺级订单的父订单ID为支付级订单ID，支付级订单无父级订单(值为0)
     */
	private Long parentNumber;
    /**
     *订单级别，1:支付级订单(L1),2:商家级订单(L2),3:商品级订单(L3)
     */
	private Integer level;
    /**
     *订单类型，1:默认商品购买
     */
	private Integer type;
    /**
     *交易市场，1:福粒
     */
	private Integer marketplace;
    /**
     *下单来源平台，0:全终端(未知平台下单),1:IOS下单,2:Android下单,3:H5下单

     */
	private Integer platform;
    /**
     *订单可见状态，0:订单正常可见,1:订单初始删除,2:订单永久删除,99:订单初始不可见
     */
	private Integer visible;
    /**
     *订单主状态，0:订单已创建,1:订单已取消,2:订单已支付,3:订单已发货,4:订单已收货,5:订单已关闭,6:订单已完成
     */
	private Integer status;
    /**
     *订单子状态，0:订单已创建,1:订单已取消,2:订单已支付,3:订单已发货,4:订单已收货,5:订单已关闭,6:订单已完成
     */
	private Integer statusEx;
    /**
     *订单逆向状态，0:未进入退货/款流程,1:进入退货/款流程,2:退货/款流程完成（仅退款行为）,3:退货/款流程完结，未生过成功的售后行为,4:退货/款流程完结，发生过成功的售后行为
     */
	private Integer reverseStatus;
    /**
     *订单评价状态，0:初始不可评价,1:初始可评价,2:可追加评价,3:追加评价后,评价关闭
     */
	private Integer rateStatus;
    /**
     *订单货款（不包含积分），商品级订单表示购买数量*商品原价-商品级优惠，商家级订单表示其子商品级订单货款总和减去商家优惠，支付级订单表示其子商家级订单货款总和减去平台优惠
     */
	private Long price;
    /**
     *订单积分抵扣货款
     */
	private Long pointAmount;
	/**
	 *订单会员卡积分抵扣货款
	 */
	private Long cardAmount;
    /**
     *数量，商品级订单表示购买SKU数量，商家级订单表示子商品级订单购买SKU数量总和，支付级订单表示子商家级订单数量总和
     */
	private Integer quantity;
    /**
     *买家用户ID
     */
	private Long buyerUserId;
    /**
     *卖家ID
     */
	private Long sellerId;
    /**
     *商品 编号，商品级订单专用
     */
	private Long spuCode;
    /**
     *SKU ID，商品级订单专用
     */
	private Long skuId;
    /**
     *支付ID
     */
	private Long payId;
    /**
     *支付时间
     */
	private Date payTime;
    /**
     *当前状态到下一个状态的超时截止时间
     */
	private Date expireTime;
    /**
     *结算时间
     */
	private Date settleTime;
    /**
     *商家运费(不包含积分)
     */
	private Integer shipExpense;
    /**
     *积分抵扣运费
     */
	private Integer shipExpensePointAmount;
    /**
     *发货时间
     */
	private Date shipTime;
    /**
     *收货时间
     */
	private Date receiveTime;
    /**
     *收货类型
     */
	private Integer receiveType;
    /**
     *取消时间
     */
	private Date cancelTime;
    /**
     *取消原因，JSON存储
     */
	private String cancelReason;
    /**
     *卖家备注
     */
	private String sellerComment;
    /**
     *买家备注
     */
	private String buyerComment;
    /**
     *节日礼金ID
     */
	private Long campaignId;
    /**
     *优惠金额，支付级订单表示平台优惠金额，商家级订单用于改价，商品级订单暂不启用
     */
	private Long promotionAmount;
    /**
     *促销快照，JSON存储
     */
	private String promotion;
    /**
     *订单来源 0：购物车，1：立即购买，2：代下单，3：预售

     */
	private Integer source;
    /**
     *第三方订单编号
     */
	private String thirdOrderNumber;
    /**
     *订单扩展，JSON存储
     */
	private String extra;
    /**
     *订单标签
     */
	private String tags;
    /**
     *版本号
     */
	private Integer version;
    /**
     *创建时间，创建时间为下单时间
     */
	private Date createTime;
    /**
     *修改时间
     */
	private Date updateTime;
	/**
	 * 生成支付级订单号次数
	 */
	private Integer num;
	/**
	 * 优惠卷优惠金额
	 */
	private Long voucherBenefit;

	/**
	 * 优惠卷ID
	 */
	private Long voucherId;

	/**
	 * 秒杀ID
	 */
	private Long seckillId;


}
