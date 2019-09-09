package com.lx.benefits.bean.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* @ClassName: UserExtVO
* @Description:
* @author wind
* @date 2019-2-11
*/
@Data
public class UserCollectionExtVO implements Serializable {
    /**
     * 收藏id
     */
    private Long id;
    /**
     *商品id
     */
    private Long spuCode;
    /**
     *sku ID
     */
    private Long skuId;
    /**
     * sku主题
     */
    private String title;
    /**
     * sku主题en
     */
    private String titleEn;
    /**
     * spec规格
     */
    private String spec;
    /**
     * sku 图片
     */
    private String skuImg;
    /**
     * 市场价
     */
    private BigDecimal marketPrice;
    /**
     * 售价
     */
    private BigDecimal salePrice;
    /**
     *创建时间
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;




}
