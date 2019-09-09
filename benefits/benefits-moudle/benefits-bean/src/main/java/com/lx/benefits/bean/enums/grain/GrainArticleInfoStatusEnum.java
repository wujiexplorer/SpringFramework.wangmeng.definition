package com.lx.benefits.bean.enums.grain;

/**
 * User:wangmeng
 * Date:2019/5/22
 * Time:16:10
 * Verision:2.x
 * Description:TODO
 **/
public enum GrainArticleInfoStatusEnum {

    SUSPEND_AWARD(1,"暂停奖励"),
    OPEN_AWARD(2,"开启奖励"),
    NOT_READ(3,"未查看"),
    ALREADY_READ(4,"已查看"),
    ALREADY_SCREEN(5,"已屏蔽"),
    BE_PENDING(6,"待定");
    private Integer code;
    private String desc;
    private GrainArticleInfoStatusEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static GrainArticleInfoStatusEnum getGrainArticleInfoStatusEnum
            (Integer value,GrainArticleInfoStatusEnum nullOfValue){
        if(null == value){
            return nullOfValue;
        }
        for(GrainArticleInfoStatusEnum grainArticleInfoStatusEnum : GrainArticleInfoStatusEnum.values()){
            if(value.equals(grainArticleInfoStatusEnum.getCode())){
                return grainArticleInfoStatusEnum;
            }
        }
        return nullOfValue;
    }
}
