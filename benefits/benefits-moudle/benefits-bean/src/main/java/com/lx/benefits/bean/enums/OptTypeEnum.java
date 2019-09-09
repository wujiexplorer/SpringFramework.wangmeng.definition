package com.lx.benefits.bean.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 企业/员工积分操作类型
 * 
 * ###############注意######################
 * 
 * <pre>
 * 增添新的操作类型时:
 * 如果涉及到员工积分增加，请同步更新getEmployeeAddOptType方法;
 * 如果涉及到员工积分扣减，请同步更新getEmployeeReduceOptType方法;
 * 
 * </pre>
 */
public enum OptTypeEnum {
	
    DISTRIBUTION(1, "运营给企业充值积分"),
    RECOVERY(2, "运营给企业退款积分"),
    HR_DISTRIBUTION_REDUCE(3, "HR积分分配扣减企业积分"),
    HR_RECOVERY_ADD(4, "HR积分回收增加企业积分"),
    HR_DISTRIBUTION_USER_ADD(5, "HR积分分配员工增加积分"),
    HR_RECOVERY_USER_REDUCE(6, "HR积分回收员工扣减积分"),
    USER_ORDER_REDUCE(7, "员工下单扣积分"),
    USER_REFUND_ADD(8, "员工退货退款时退积分"),
    DISTRIBUTION_TO_EMPLOYEE(9, "运营从企业可用积分账户分配积分给员工 - 企业扣减积分"),
    ADMIN_DISTRIBUTION_USER_ADD(10, "运营从企业可用积分账户分配积分给员工 - 员工增加积分"),
    PRE_SALE_DISTRIBUTION_USER_ADD(11, "预售购买扣除额度"),
    SALE_DISTRIBUTION_USER_ADD(12, "运营平台分配预售额度给员工 - 员工增加预售额度"),
    ADMIN_DISTRIBUTION_HR_ADD(13, "运营平台回收增加企业积分"),
    BIRTHDAY_CREDIT(14,"生日自动分配积分"),
    MEMBER_CARD_CREDIT(15,"奖励积分"),//会员卡充值
    ;

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

    OptTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value获取OptTypeEnum
     *
     * @param value value
     * @return OptTypeEnum
     */
    public static OptTypeEnum getOptTypeEnum(Integer value) {
        if (null == value || value < 1) {
            return null;
        }
        for (OptTypeEnum optTypeEnum : OptTypeEnum.values()) {
            if (optTypeEnum.getValue().equals(value)) {
                return optTypeEnum;
            }
        }
        return null;
    }

    /**
     * 根据code获取OptTypeEnum
     *
     * @param code code
     * @return OptTypeEnum
     */
    public static OptTypeEnum getOptTypeEnum(String code) {
        if (null == code || code.isEmpty()) {
            return null;
        }
        for (OptTypeEnum optTypeEnum : OptTypeEnum.values()) {
            if (optTypeEnum.name().equals(code)) {
                return optTypeEnum;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String aaa = "";
        for (OptTypeEnum optTypeEnum : OptTypeEnum.values()) {
            aaa += " "+optTypeEnum.name();
        }
        System.out.println(aaa);
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
    
	public static List<Integer> getEmployeeAddOptType() {
		return Arrays.asList(// 创建一个不可变的集合，防止被篡改
				HR_DISTRIBUTION_USER_ADD.value, //
				USER_REFUND_ADD.value, //
				ADMIN_DISTRIBUTION_USER_ADD.value, //
				BIRTHDAY_CREDIT.value, //
				MEMBER_CARD_CREDIT.value//
		);
	}

	public static List<Integer> getEmployeeReduceOptType() {
		return Arrays.asList(// 创建一个不可变的集合，防止被篡改
				HR_RECOVERY_USER_REDUCE.value, //
				USER_ORDER_REDUCE.value//
		);
	}
}
