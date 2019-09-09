package com.lx.benefits.bean.enums;

/**
 * @author unknow on 2018-12-15 19:20.
 */
public enum CampaignStatusEnum {
    UNDERWAY(0, "UNDERWAY"),
    OVER(1, "OVER");


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

    CampaignStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value获取CreditTypeEnum
     * @param value    value
     * @return CreditTypeEnum
     */
    public static CampaignStatusEnum getCampaignStatusEnum(Integer value) {
        if (null == value) {
            return null;
        }
        for (CampaignStatusEnum creditTypeEnum : CampaignStatusEnum.values()) {
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
    public static CampaignStatusEnum getCampaignStatusEnum(String code) {
        if (null == code || code.isEmpty()) {
            return null;
        }
        for (CampaignStatusEnum creditTypeEnum : CampaignStatusEnum.values()) {
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
        return null != getCampaignStatusEnum(value);
    }

    /**
     * 验证是否为合法有效的value
     * @param  code                   code
     * @return boolean   {true: 合法的; 不合法的}
     */
    public static boolean isVaildCode(String code) {
        return null != getCampaignStatusEnum(code);
    }
}
