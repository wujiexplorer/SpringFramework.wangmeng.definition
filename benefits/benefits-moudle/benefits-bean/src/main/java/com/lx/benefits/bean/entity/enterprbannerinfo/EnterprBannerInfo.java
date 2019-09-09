package com.lx.benefits.bean.entity.enterprbannerinfo;


import com.lx.benefits.bean.entity.Entity;
import lombok.Data;

@Data
public class EnterprBannerInfo extends Entity {
    /**
     * bannerId,自增主键ID
     */
    private Long bannerId;

    /**
     * 所属定制模块id
     */
    private Long customId;

    /**
     * banner图片路径
     */
    private String bannerPath;

    /**
     * banner链接地址
     */
    private String linkUrl;

    /**
     * banner图片title
     */
    private String bannerTitle;

    private String bannerTitleEn;

    /**
     * banner图片排序
     */
    private Integer bannerSort;

    /**
     * 展示开始时间，无限制设置为0
     */
    private Integer startTime;

    /**
     * 展示结束时间，无限制设置为0
     */
    private Integer endTime;

    private Integer isShow;

    /**
     * banner类型 1：pc  2：手机端
     */
    private Integer type;
}