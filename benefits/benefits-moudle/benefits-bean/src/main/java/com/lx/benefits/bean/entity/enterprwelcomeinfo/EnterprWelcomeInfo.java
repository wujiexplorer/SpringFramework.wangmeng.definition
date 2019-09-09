package com.lx.benefits.bean.entity.enterprwelcomeinfo;


import com.lx.benefits.bean.entity.Entity;
import lombok.Data;

@Data
public class EnterprWelcomeInfo extends Entity {
    /**
     * 定制模块id,自增主键ID
     */
    private Long customId;
    /**
     * 积分所属企业客户id
     */
    private Long enterprId;
    /**
     * Logo图片地址
     */
    private String logoPath;
    /**
     * 网页背景色
     */
    private String pageBackground;
    /**
     * 导航栏背景色
     */
    private String navBackground;
    /**
     * 企业联系电话
     */
    private String contactPhone;

    /**
     * 企业联系Email
     */
    private String contactEmail;

    private Integer type;

    /**
     * 0:隐藏  1：显示
     */
    private Integer isShowSearchBar;
    /**
     * 0:关闭  1：打开
     */
    private Integer wxacode;//小程序码

}