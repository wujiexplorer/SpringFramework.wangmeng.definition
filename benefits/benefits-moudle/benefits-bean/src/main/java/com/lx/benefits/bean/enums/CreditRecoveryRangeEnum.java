package com.lx.benefits.bean.enums;

/**
 * @author unknow on 2018-12-15 19:20.
 */
public enum CreditRecoveryRangeEnum {
    ALL(1, "全部积分回收"),
    PART(2, "部分积分回收");

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

    CreditRecoveryRangeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value获取CreditRecoveryRangeEnum
     *
     * @param value value
     * @return CreditRecoveryRangeEnum
     */
    public static CreditRecoveryRangeEnum getOptTypeEnum(Integer value) {
        if (null == value || value < 1) {
            return null;
        }
        for (CreditRecoveryRangeEnum optTypeEnum : CreditRecoveryRangeEnum.values()) {
            if (optTypeEnum.getValue().equals(value)) {
                return optTypeEnum;
            }
        }
        return null;
    }

    /**
     * 根据code获取CreditRecoveryRangeEnum
     *
     * @param code code
     * @return OptTypeEnum
     */
    public static CreditRecoveryRangeEnum getOptTypeEnum(String code) {
        if (null == code || code.isEmpty()) {
            return null;
        }
        for (CreditRecoveryRangeEnum OptTypeEnum : CreditRecoveryRangeEnum.values()) {
            if (OptTypeEnum.name().equals(code)) {
                return OptTypeEnum;
            }
        }
        return null;
    }

    /**
     * 验证是否为合法有效的value
     *
     * @param value value
     * @return boolean   {true: 合法的; 不合法的}
     */
    public static boolean isVaildValue(Integer value) {
        return null != getOptTypeEnum(value);
    }

    /**
     * 验证是否为合法有效的value
     *
     * @param code code
     * @return boolean   {true: 合法的; 不合法的}
     */
    public static boolean isVaildCode(String code) {
        return null != getOptTypeEnum(code);
    }
}