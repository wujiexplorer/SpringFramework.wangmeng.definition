package com.lx.benefits.bean.entity.cart;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: CartProductArchive
* @Description:
* @author wind
* @date 2019-2-11
*/
@Data
public class CartProductArchive  implements Serializable{
	
    /**
     *主键
     */
	private Long id;
    /**
     *购物车元素id
     */
	private Long sourceId;
    /**
     *用户id
     */
	private Long accountId;
    /**
     *用户类型，1：正式用户，2：临时用户
     */
	private Integer type;
    /**
     *商品id
     */
	private Long skuId;
    /**
     *店铺id
     */
	private Long sellerId;
    /**
     *购物车商品数量
     */
	private Integer count;
    /**
     *购物车渠道，0: 福粒
     */
	private Integer channel;
    /**
     *是否选中，0：未选中，1：选中
     */
	private Integer checked;
    /**
     *删除类型，1：用户删除， 2：失效删除，3：用户下单后删除
     */
	private Integer deletedType;
    /**
     *活动ID
     */
	private Long activityId;
    /**
     *是否逻辑删除
     */
	private Integer isDelete;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *最后更新时间
     */
	private Date updateTime;


}
