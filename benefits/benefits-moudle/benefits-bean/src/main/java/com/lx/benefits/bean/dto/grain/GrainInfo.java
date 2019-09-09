package com.lx.benefits.bean.dto.grain;

import com.lx.benefits.bean.base.dto.BaseVO;
import com.lx.benefits.bean.base.dto.FLPageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User:wangmeng
 * Date:2019/5/17
 * Time:15:27
 * Verision:2.x
 * Description:TODO
 **/
@Data
@ApiModel(value = "grainInfo",description = "颗粒号基本信息")
@NoArgsConstructor
@AllArgsConstructor
public class GrainInfo implements BaseVO {

    @ApiModelProperty(value = "颗粒id")
    private Long grainId;
    @ApiModelProperty(value = "企业id")
    private Long enterprId;
    @ApiModelProperty(value = "企业名称")
    private String enterprName;
    @ApiModelProperty(value = "状态（1:审核中；2:通过；3:冻结）")
    private Integer status;
    @ApiModelProperty(value = "转入积分总额")
    private Double receiveValue;
    @ApiModelProperty(value = "累计阅读量")
    private Integer cumulationReadQuantity;
    @ApiModelProperty(value = "剩余颗粒值")
    private Double leftGrainValue;
    @ApiModelProperty(value = "权限设置返回")
    private String typeList;
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;
    @ApiModelProperty(value = "开通时间")
    private Date establishedTime;
    @ApiModelProperty(value = "服务状态（1：正常；2：暂停；3：待定）")
    private Integer serviceStatus;
    @ApiModelProperty(value = "服务状态（0：暂停；1：开启）")
    private Integer isSuspend;
    @ApiModelProperty(value = "创建时间")
    private Integer created;
    @ApiModelProperty(value = "更新时间")
    private Integer updated;
    @ApiModelProperty(value = "状态值（1:审核中；2:通过；3:冻结）")
    private String statusValue;
    @ApiModelProperty(value = "服务状态值（1：正常；2：暂停；3：待定）")
    private String serviceStatusValue;


}
