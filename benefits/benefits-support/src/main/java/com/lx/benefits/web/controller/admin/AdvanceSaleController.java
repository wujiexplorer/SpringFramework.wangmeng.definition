package com.lx.benefits.web.controller.admin;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketInfoDto;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketQueryReqDto;
import com.lx.benefits.bean.dto.admin.campaign.ImportCampainReqDto;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.*;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacket;
import com.lx.benefits.bean.entity.enterprfestivalpacket.EnterprFestivalPacketExample;
import com.lx.benefits.bean.enums.CreditRecoveryRangeEnum;
import com.lx.benefits.bean.enums.CreditTypeEnum;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;
import com.lx.benefits.service.sale.AdvanceSaleItemService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @author unknow on 2018-12-11 00:27.
 */
@Api(tags = "运营后台-预售活动模块")
@RestController("advanceSaleController")
@RequestMapping("/admin/advanceSale")
public class AdvanceSaleController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(AdvanceSaleController.class);

    @Autowired
    private EnterprFestivalService enterprFestivalService;

    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;

    @Autowired
    private CreditOperateInfoService creditOperateInfoService;

    @Autowired
    private AdvanceSaleItemService advanceSaleItemService;

    @ApiOperation(value = "添加预售活动", response = Boolean.class)
    @RequestMapping(value = "/addfestivalpacket", method = RequestMethod.POST)
    public JSONObject addFestivalPacket(@ApiParam(value = "Request", name = "req") @RequestBody FestivalPacketInfoDto req) {
        if (null == req) {
            return Response.fail("预售活动信息不能为空");
        }
        req.setType(CreditTypeEnum.ADVANCESALE.getValue());
        req.setCreditType("ADVANCESALE");
        try {
            // 设置预售活动类型
            return enterprFestivalService.insert(req);
        } catch (Exception e) {
            logger.error("新建预售互动失败：{}", e);
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改预售活动", response = Boolean.class)
    @RequestMapping(value = "/modifyfestivalpacket", method = RequestMethod.POST)
    public JSONObject modifyFestivalPacket(@ApiParam(value = "Request", name = "req") @RequestBody FestivalPacketInfoDto req) {
        
        if (null == req) {
            return Response.fail("预售活动信息不能为空");
        }
        if(null == req.getCampaignId() || req.getCampaignId() == 0) {
            return Response.fail("ID为空");
        }
        try {
            // 设置预售活动类型
            req.setType(CreditTypeEnum.ADVANCESALE.getValue());
            req.setCreditType("ADVANCESALE");
            Long result = enterprFestivalService.update(req);
            return Response.succ(result);
        } catch (Exception e) {
            logger.error("额度分配错误：{}", e);
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "预售活动详情", response = FestivalPacketInfoDto.class)
    @RequestMapping(value = "/festivalpacketdetail/{campaignId}", method = RequestMethod.GET)
    public JSONObject festivalPacketDetail(@ApiParam(value = "campaignId", name = "campaignId") @PathVariable Long campaignId) {
        
        if (null == campaignId || campaignId < 1) {
            return Response.fail("无效的预售活动id");
        }
        try {
            return enterprFestivalService.findById(campaignId, false);
        } catch (Exception e) {
            return Response.fail("系统异常!");
        }
    }

    @ApiOperation(value = "预售活动列表信息", response = FestivalPacketInfoDto.class)
    @RequestMapping(value = "/festivalpacketlist", method = RequestMethod.GET)
    public JSONObject festivalPacketList(@ApiParam(value = "Request", name = "req") @ModelAttribute FestivalPacketQueryReqDto req) {
        try {
            // 设置预售活动类型
            req.setType(CreditTypeEnum.ADVANCESALE.getValue());
            return enterprFestivalService.findByName(req, false);
        } catch (Exception e) {
            return Response.fail("系统异常!");
        }
    }

    @ApiOperation(value = "预售分配列表信息", response = FestivalPacketInfoDto.class)
    @RequestMapping(value = "/festivalintegral", method = RequestMethod.GET)
    public JSONObject festivalintegral(@ApiParam(value = "Request", name = "req") @ModelAttribute FestivalPacketQueryReqDto req) {
        try {
            // 设置预售活动类型
            req.setType(CreditTypeEnum.ADVANCESALE.getValue());
            return employeeCreditInfoService.findList(req);
        } catch (Exception e) {
            return Response.fail("系统异常!");
        }
    }

    @ApiOperation(value = "预售分配历史记录", response = FestivalPacketInfoDto.class)
    @RequestMapping(value = "/festivalHistory", method = RequestMethod.GET)
    public JSONObject festivalHistory(@ApiParam(value = "Request", name = "req") @ModelAttribute CreditOperateInfo req) {
        try {
            // 设置预售活动类型
            req.setCreditType(CreditTypeEnum.ADVANCESALE.getValue());
            return creditOperateInfoService.festivalHistory(req);
        } catch (Exception e) {
            return Response.fail("系统异常!");
        }
    }


    @ApiOperation(value = "批量导入积分人员", response = Boolean.class)
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public JSONObject batchImport(@ApiParam(value = "Request", name = "req") @RequestBody ImportCampainReqDto req) {
        try {
            return enterprFestivalService.batchImport(req);
        } catch (Exception e) {
            return Response.fail("批量导入积分人员异常!");
        }
    }

    @ApiOperation(value = "积分分配", response = Boolean.class)
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    public JSONObject distribution(@ApiParam(value = "Request", name = "req") @RequestBody ImportCampainReqDto req) {
        try {
            return enterprFestivalService.saleImport(req);
        } catch (Exception e) {
            logger.error("预售活动，积分分配异常{}",e.getMessage());
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "参与活动列表", response = FestivalPacketInfoDto.class)
    @RequestMapping(value = "/joinpacketlist", method = RequestMethod.GET)
    public JSONObject joinpacketlist(@ApiParam(value = "Request", name = "req") @ModelAttribute FestivalPacketQueryReqDto req) {
        try {
            return enterprFestivalService.findByName(req, false);
        } catch (Exception e) {
            return Response.fail("系统异常!");
        }
    }



    @ApiOperation(value = "活动积分回收操作", response = Boolean.class)
    @RequestMapping(value = "/recovery", method = RequestMethod.POST)
    public JSONObject recovery(@ApiParam(value = "企业积分回收信息", name = "reqDto") @RequestBody CreditRecoveryReqDto reqDto) {

        if (null == reqDto) {
            return Response.fail("请求参数不能为空");
        }

        if (CreditRecoveryRangeEnum.PART.name().equals(reqDto.getRecoveryRange()) && StringUtils.isEmpty(reqDto.getFilePath())) {
            return Response.fail("文件地址为空");
        }

        EnterprFestivalPacketExample example = new EnterprFestivalPacketExample();
        example.createCriteria().andCampaignIdEqualTo(reqDto.getCampaignId());
        List<EnterprFestivalPacket> list =  enterprFestivalService.queryByParam(example);
        if (null == list || list.size() < 1) {
            return Response.fail("活动id不存在");
        }
        EnterprFestivalPacket enterprFestivalPacket = list.get(0);
        List<Object> data = null;
        List<CreditRecoveryDetailDto> detailDtoList = new ArrayList<>(32);
        int recoveryResult = 0;
        if (reqDto.getRecoveryRange().equals(CreditRecoveryRangeEnum.PART.name())) {
            try {
                UrlResource resource = new UrlResource(reqDto.getFilePath());
                data = EasyExcelFactory.read(resource.getInputStream(), new Sheet(1, 1, CreditRecoveryExcelDto.class));
            } catch (Exception e) {
                logger.error("预售额度回收文件解析出错：", e);
                return Response.fail("文件解析出错!");
            }

            if (CollectionUtils.isEmpty(data)) {
                return Response.fail("excel表格数据为空");
            }

            //excel表格信息组装
            for (Object datum : data) {
                CreditRecoveryExcelDto excelDto = (CreditRecoveryExcelDto) datum;
                CreditRecoveryDetailDto dto = new CreditRecoveryDetailDto();
                dto.setEmployeeNo(excelDto.getEmployeeNo());
                dto.setCreditType(dto.getCreditType());
                dto.setCredit(BigDecimal.ZERO);
                dto.setRemark(dto.getRemark());
                dto.setEnterprId(enterprFestivalPacket.getEnterprId());
                dto.setOptUserId(enterprFestivalPacket.getEnterprId());

                detailDtoList.add(dto);
            }
        }

        //数据封装
        CreditRecoveryDto dto = new CreditRecoveryDto();
        dto.setEnterprId(enterprFestivalPacket.getEnterprId());
        dto.setOptUserId(enterprFestivalPacket.getEnterprId());
        dto.setCreditType(enterprFestivalPacket.getCreditType());
        dto.setDetailDtoList(detailDtoList);
        dto.setRecoveryRange(reqDto.getRecoveryRange());
        dto.setRemark(reqDto.getRemarks());
        dto.setCampaignId(reqDto.getCampaignId());

        recoveryResult = employeeCreditInfoService.recoveryList(dto);


        return Response.succ("成功回收" + recoveryResult + "个人员积分");
    }

    @ApiOperation(value = "代下单", response = FestivalPacketInfoDto.class)
    @RequestMapping(value = "/submitOrder/{campaignId}", method = RequestMethod.GET)
    public JSONObject submitOrder(@ApiParam(value = "campaignId", name = "campaignId") @PathVariable Long campaignId) {
        if (null == campaignId || campaignId < 1) {
            return Response.fail("无效的预售活动id");
        }
        try {
            return advanceSaleItemService.submitOrder(campaignId);
        }catch (BusinessException e) {
        	  return Response.fail(e.getMessage());
		} catch (Exception e) {
            logger.error("代下单异常{}",e.getMessage());
            return Response.fail("系统异常!");
        }
    }



}