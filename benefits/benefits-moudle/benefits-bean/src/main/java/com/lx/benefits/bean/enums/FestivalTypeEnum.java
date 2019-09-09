package com.lx.benefits.bean.enums;

/**
 * @author unknow on 2018-12-15 19:20. 1：节日 2：生日  3：周年庆
 */
public enum FestivalTypeEnum {
    FESTIVAL(1, "节日"),
    BIRTHDAY(2, "生日"),
    ANNIVERSARY(3, "周年庆"),
    ADVANCE(4, "预售活动");

    private Integer value;
    private String desc;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    FestivalTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value获取FestivalTypeEnum
     * @param value    value
     * @return CreditTypeEnum
     */
    public static FestivalTypeEnum getFestivalTypeEnum(Integer value) {
        if (null == value || value < 1) {
            return null;
        }
        for (FestivalTypeEnum creditTypeEnum : FestivalTypeEnum.values()) {
            if (creditTypeEnum.getValue().equals(value)) {
                return creditTypeEnum;
            }
        }
        return null;
    }

    /**
     * 根据code获取CreditTypeEnum
     * @param code   code
     * @return    CreditTypeEnum
     */
    public static FestivalTypeEnum getFestivalTypeEnum(String code) {
        if (null == code || code.isEmpty()) {
            return null;
        }
        for (FestivalTypeEnum creditTypeEnum : FestivalTypeEnum.values()) {
            if (creditTypeEnum.name().equals(code)) {
                return creditTypeEnum;
            }
        }
        return null;
    }

    /**
     * 验证是否为合法有效的value
     * @param value                   value
     * @return boolean   {true: 合法的; 不合法的}
     */
    public static boolean isVaildValue(Integer value) {
        return null != getFestivalTypeEnum(value);
    }

    /**
     * 验证是否为合法有效的value
     * @param  code                   code
     * @return boolean   {true: 合法的; 不合法的}
     */
    public static boolean isVaildCode(String code) {
        return null != getFestivalTypeEnum(code);
    }
}
