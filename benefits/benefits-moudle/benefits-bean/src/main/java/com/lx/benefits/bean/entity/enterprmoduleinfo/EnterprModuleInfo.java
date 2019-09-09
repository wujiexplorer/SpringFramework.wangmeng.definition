package com.lx.benefits.bean.entity.enterprmoduleinfo;


import com.lx.benefits.bean.entity.Entity;
import lombok.Data;

@Data
public class EnterprModuleInfo extends Entity {
    /**
     * bannerId,自增主键ID
     */
    private Long moduleId;

    /**
     * 所属定制模块id
     */
    private Long customId;

    /**
     * module名称
     */
    private String moduleName;


    /**
     * module名称
     */
    private String moduleNameEn;

    /**
     * module链接地址
     */
    private String moduleLink;

    /**
     * module图片地址
     */
    private String modulePic;

    /**
     * module背景色
     */
    private String background;

    /**
     * 模块选中背景色
     */
    private String selectedBackground;

    /**
     * module展示顺序
     */
    private Integer moduleSort;

    /**
     * 展示开始时间，无限制设置为0
     */
    private Integer startTime;

    /**
     * 展示结束时间，无限制设置为0
     */
    private Integer endTime;

    private Integer isShow;

    private Integer type;
}