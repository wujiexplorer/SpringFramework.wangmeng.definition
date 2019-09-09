package com.lx.benefits.bean.dto.sch;



import com.lx.benefits.bean.dto.jd.base.BaseDO;
import com.lx.benefits.bean.enums.PlatformEnum;
import lombok.Data;

import java.util.List;

/**
 * Created by ldr on 2016/2/18.
 */
@Data
public class SearchQuery extends BaseDO {

    private static final long serialVersionUID = 5883884908918133226L;

    /**
     * 关键字
     */
    private String key;

    /**
     * 筛选条件 品牌id list
     */
    private List<Long> brandIds;

    /**
     * 筛选条件 国家Id list
     */
    private List<Long> countryIds;

    /**
     * 筛选条件 商品小类Id
     */
    private List<Long> smallCategoryIds;

    /**
     * 是否只显示有库存的，1是，0否 默认0
     */
    private Integer hasInventoryOnly = 0;
    /**
     * 排序字段
     * 1 价格降序
     * 2 价格升序
     * 3 销售数量降序
     * 4 销售数量升序
     * @see
     */
    private Integer sort;

    /**
     * 平台
     */
    private PlatformEnum platform;


    /**
     * 分类导航Id
     */
    private Long navCategoryId;

    /**
     * 分类导航BrandId
     */
    private Long navBrandId;

    private Long userId;

    /**
     * 销售方式
     * @see com.lx.dto.mmp.enums.SalesPartten
     */
    private Integer salesPattern;

    /**
     * 优惠券Id 此id为usercoupon主键
     * 用于查询优惠券可用商品
     */
    private Long couponId;


   private Long supplierId;

    /**
     * 价格区间
     */
    private Double priceFrom;

    private Double priceTo;

    /**
     * 是否自营 0 否 1 是
     */
    private Integer selfOnly;

    private Long categoryId;

    private String lan;
}
