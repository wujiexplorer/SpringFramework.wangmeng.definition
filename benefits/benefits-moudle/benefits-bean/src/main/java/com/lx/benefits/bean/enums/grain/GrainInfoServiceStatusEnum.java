package com.lx.benefits.bean.enums.grain;



/**
 * User:wangmeng
 * Date:2019/5/21
 * Time:10:43
 * Verision:2.x
 * Description:TODO
 **/
public enum GrainInfoServiceStatusEnum {

    NORMAL(1,"正常"),
    SUSPEND(2,"暂停"),
    BE_PENDING(3,"待定");

    private Integer code;
    private String desc;

    private GrainInfoServiceStatusEnum(Integer code, String desc){
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

    public static GrainInfoServiceStatusEnum getGrainInfoServiceStatus(Integer value, GrainInfoServiceStatusEnum nullOfValue){
        if(null == value){
            return nullOfValue;
        }
        for(GrainInfoServiceStatusEnum grainInfoServiceStatus : GrainInfoServiceStatusEnum.values()){
            if(value.equals(grainInfoServiceStatus.getCode())){
                return grainInfoServiceStatus;
            }
        }
        return nullOfValue;
    }
}
