package com.lx.benefits.bean.entity.employeecreditinfo;

import com.lx.benefits.bean.base.dto.FLPageDto;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 员工积分
 *
 * @author luojie
 */
@Data
public class EmployeeCreditInfo extends FLPageDto {

    private Long creditId;

    private Long employeeId;

    private Integer creditType;

    private Long campaignId;

    private BigDecimal credit;
    
    private BigDecimal birthdayCredit;

    private Integer status;

    private String financeNo;

    private Double grainValue;

    private Long grainId;

    private Integer isRead;


    private String articleIdList;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    private Integer isDeleted;

    private Integer created;

    private Integer updated;

    /**
     * 充值：5、10  消费：7，6
     */
    private Integer optType;

    private String usedVouchers;

    private String receivedVouchers;

    private String receivedSeckillIds;
}
