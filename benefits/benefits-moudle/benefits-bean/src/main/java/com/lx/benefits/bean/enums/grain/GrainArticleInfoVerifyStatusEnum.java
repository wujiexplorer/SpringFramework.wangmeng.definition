package com.lx.benefits.bean.enums.grain;

/**
 * User:wangmeng
 * Date:2019/5/23
 * Time:13:48
 * Verision:2.x
 * Description:TODO
 **/
public enum GrainArticleInfoVerifyStatusEnum {

    NOT_READ(1,"未查看"),
    ALREADY_READ(2,"已查看"),
    ALREADY_SCREEN(3,"已屏蔽");

    private Integer code;
    private String desc;
    private GrainArticleInfoVerifyStatusEnum(Integer code,String desc){
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

    public static GrainArticleInfoVerifyStatusEnum getGrainArticleInfoStatusEnum
            (Integer value,GrainArticleInfoVerifyStatusEnum nullOfValue){
        if(null == value){
            return nullOfValue;
        }
        for(GrainArticleInfoVerifyStatusEnum grainArticleInfoVerifyStatusEnum : GrainArticleInfoVerifyStatusEnum.values()){
            if(value.equals(grainArticleInfoVerifyStatusEnum.getCode())){
                return grainArticleInfoVerifyStatusEnum;
            }
        }
        return nullOfValue;
    }
}
