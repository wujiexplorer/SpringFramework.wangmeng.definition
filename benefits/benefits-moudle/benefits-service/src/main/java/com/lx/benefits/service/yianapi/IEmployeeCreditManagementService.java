package com.lx.benefits.service.yianapi;

import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;

public interface IEmployeeCreditManagementService {

    /**
     * 使用积分
     * @param dto
     * @return
     */
    ResultInfo use(EmployeeCreditInfo dto);

    /**
     * 返还积分
     * @param dto
     * @return
     */
    ResultInfo payback(EmployeeCreditInfo dto);

    /**
     * 查询用户积分信息
     * @param dto
     * @return
     */
    ResultInfo<EmployeeCreditInfo> info(EmployeeCreditInfo dto);

    /**
     * 查询用户积分使用记录，返还积分时使用
     * @param dto
     * @return
     */
    ResultInfo<CreditOperateInfo> getCreditOperateInfo(EmployeeCreditInfo dto);
}
