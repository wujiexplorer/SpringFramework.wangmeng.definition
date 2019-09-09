package com.lx.benefits.bean.enums.grain;

/**
 * User:wangmeng
 * Date:2019/5/21
 * Time:10:30
 * Verision:2.x
 * Description:TODO
 **/
public enum GrainInfoStatusEnum {

    UNDER_VIEW(1,"审核中"),
    PASS(2,"通过"),
    FREEZE(3,"冻结"),
    BE_PENDING(4,"待定");

    private Integer code;
    private String desc;
    private GrainInfoStatusEnum(Integer code, String desc){
        this .code = code;
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

    public static GrainInfoStatusEnum getGrainInfoStatus(Integer value, GrainInfoStatusEnum nullOfValue){
        if(null == value){
            return nullOfValue;
        }
        for(GrainInfoStatusEnum grainInfoStatus : GrainInfoStatusEnum.values()){
            if(value.equals(grainInfoStatus.getCode())){
                return grainInfoStatus;
            }
        }
        return nullOfValue;
    }
}
