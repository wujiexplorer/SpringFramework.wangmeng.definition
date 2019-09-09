package com.lx.benefits.bean.dto.enterpriseadmin.employee;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * @author unknow on 2018-12-05 00:47.
 */
@Data
@ApiModel("企业员工基础信息")
public class EmployeeInfoDto extends BaseRowModel {
    @ApiModelProperty(value = "员工唯一标示id")
    @Positive(message = "无效的员工id")
    private Long employeeId;
    
    @ApiModelProperty(value = "员工所属企业id")
    private Long enterprId;
    
    @ApiModelProperty(value = "员工工号")
    @ExcelProperty(index = 0)
    private String employeeNo;
    
    @ApiModelProperty(value = "员工姓名")
    @ExcelProperty(index = 1)
    private String employeeName;
    
    @ApiModelProperty(value = "员工手机号")
    @ExcelProperty(index = 2)
    private String mobile;
    
    @ApiModelProperty(value = "员工Email")
    @ExcelProperty(index = 3)
    private String email;
    
    @ApiModelProperty(value = "员工性别 {S:保密; M:男; F:女; L:变性人}")
    @ExcelProperty(index = 4)
    private String sex;

    @ApiModelProperty(value = "员工状态 {N:在职; Y:离职}")
    @ExcelProperty(index = 5)
    private String leaveStatus;
    @ApiModelProperty(value = "员工生日 格式：yyyy-MM-dd")
    @ExcelProperty(index = 6)
    private String birthday;
    @ApiModelProperty(value = "员工登录用户名")
    @ExcelProperty(index = 7)
    private String loginName;
    @ApiModelProperty(value = "员工登录密码")
    @ExcelProperty(index = 8)
    private String password;
    /**
     * 员工登录密码secret
     */
    private String secret;
    @ApiModelProperty(value = "员工入职时间 格式：yyyy-MM-dd")
    @ExcelProperty(index = 9)
    private String inductionTime;
    
    @ApiModelProperty(value = "员工职级")
    @ExcelProperty(index = 10)
    private String position;

    @ApiModelProperty(value = "员工是否已删除{Y:是; N:否}")
    private String isDeleted;

    @ApiModelProperty(value = "添加员工时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String created;
    
    @ApiModelProperty(value = "修改员工时间，新增编辑无需此参数，格式：yyyy-MM-dd HH:mm:ss")
    private String updated;
    /**
     * 普通积分
     */
    private BigDecimal ordCredit = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
    /**
     * 节日积分
     */
    private BigDecimal festCredit = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
    //已领取的优惠卷数量
    private String vouchersNum;
}
