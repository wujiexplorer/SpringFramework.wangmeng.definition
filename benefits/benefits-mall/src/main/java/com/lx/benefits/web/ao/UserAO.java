package com.lx.benefits.web.ao;

import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.dto.yianapi.AccountVO;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 帐户业务层
 *
 * @author zhuss
 * @2016年1月3日 下午4:58:54
 */
@Service
public class UserAO {

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;
    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;

	/**
	 * @param
	 * @return
	 */
	public MResultVO<AccountVO> getUser(Long employeeId) {
		if (null == employeeId || null == employeeId) {
            return new MResultVO<>(MResultInfo.USER_NO_EXIST);
        }
        // 查询企业员工信息
        EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(employeeId,false);
        AccountVO  accountVO=new AccountVO();
        if (null != employeeInfoDto) {
        	accountVO.setTel(employeeInfoDto.getMobile());
            accountVO.setIsEntUser("1");
            // 查询企业信息
            EnterprUserInfo enterprUserInfo =  enterprUserInfoMapper.selectByPrimaryKey(employeeInfoDto.getEnterprId());
            String entName = enterprUserInfo.getEnterprName();
            accountVO.setEnterpriseName(entName);
            accountVO.setEmployeename(employeeInfoDto.getEmployeeName());
        } else {
            accountVO.setIsEntUser("0");
            //accountVO.setIsIndividuation(Individuation.NO.getCode());
        }
        return new MResultVO<>(MResultInfo.OPERATION_SUCCESS, accountVO);
	}
}
