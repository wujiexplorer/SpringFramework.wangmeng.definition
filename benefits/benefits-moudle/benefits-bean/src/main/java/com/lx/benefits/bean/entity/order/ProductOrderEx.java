package com.lx.benefits.bean.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: ProductOrderEx
* @Description:
* @author wind
* @date 2019-2-24
*/
@Data
public class ProductOrderEx implements Serializable{
	
    /**
     *主键ID
     */
	private Long id;
    /**
     *订单编号,商品级订单编号
     */
	private Long orderNumber;
    /**
     *sku id
     */
	private Long skuId;
    /**
     *买家用户ID
     */
	private Long buyerUserId;
    /**
     *商品标题（英文）
     */
	private String titleEn;
    /**
     *商品标题
     */
	private String title;
    /**
     *商品主图
     */
	private String image;
    /**
     *商品原价(市场价)，单位分
     */
	private Long price;
    /**
     *商品现价(购买价)，单位分
     */
	private Long nowPrice;
    /**
     *商品成本价
     */
	private Long costPrice;
    /**
     *规格
     */
	private String spec;
    /**
     *发货地址ID
     */
	private Long deliverAreaId;
    /**
     *发货编码
     */
	private String deliverCode;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *修改时间
     */
	private Date updateTime;


}
