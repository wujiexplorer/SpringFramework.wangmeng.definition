package com.lx.benefits.bean.entity.creditoperateinfo;


import com.lx.benefits.bean.entity.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 积分操作记录
 *
 * @author luojie
 */
@Data
public class CreditOperateInfo extends Entity {
    /**
     * 积分操作id,自增主键ID
     */
    private Long optId;

    /**
     * 积分接收操作id,如当前积分是那一次操作过来的
     */
    private Long parentOptId;

    /**
     * 积分所有者用户id，企业OR员工userId
     */
    private Long ownerUserId;

    /**
     * 积分类型{1: 普通积分; 2: 节日礼金; 3: 认可激励积分;4:预售}
     */
    private Integer creditType;

    /**
     * 积分描述{1: 普通积分; 2: 节日礼金; 3: 认可激励积分}
     */
    private String creditTypeDesc;

    /**
     * 活动id
     */
    private Long campaignId;

    /**
     * 积分数额
     */
    private BigDecimal credit;

    /**
     * 财务编号
     */
    private String financeNo;

    /**
     * 操作者userId
     */
    private Long optUserId;

    private String optUserName;

    /**
     * 操作时间
     */
    private Long optTime;

    private String optTimeFormat;

    /**
     * 审核者userId
     */
    private Long auditUserId;

    /**
     * 审核时间
     */
    private Long auditTime;

    /**
     * 操作类型{1: 运营积分充值; 2: 运营积分退款; 3: HR积分分配扣减企业积分; 4: HR积分回收增加企业积分; 5: HR积分分配员工增加积分; 
     6: HR积分回收员工扣减积分; 7: 员工下单扣积分; 8: 员工退货退款时退积分;14生日自动分配积分 }
     */
    private Integer optType;

    private List<Integer> optTypes;

    /**
     * 积分审核状态,目前optType=1or2才需要审核,其他操作默认为已审核{1: 待审核; 2: 已审核; 3: 已拒绝}
     */
    private Integer auditStatus;

    /**
     * 操作备注
     */
    private String remark;

    private String employeeName;

    private String employeeNo;

    private String filePath;

    private Integer employeeId;
    
    private String recycleCreditTime;
}