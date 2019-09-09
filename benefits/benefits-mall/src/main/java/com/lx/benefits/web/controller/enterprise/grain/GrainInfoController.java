package com.lx.benefits.web.controller.enterprise.grain;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.constants.grain.GrainInfoConstant;
import com.lx.benefits.bean.dto.grain.GrainInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.grain.GrainInfoServiceStatusEnum;
import com.lx.benefits.bean.enums.grain.GrainInfoStatusEnum;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.grain.GrainInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:15:32
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "企业商城-颗粒号信息模块")
@RestController
@RequestMapping("/enterprise/grain")
public class GrainInfoController {

    private static final Logger log = LoggerFactory.getLogger(GrainInfoController.class);

    @Autowired
    private GrainInfoService grainInfoService;

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 删除颗粒号信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteGrainInfo", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject deleteGrainInfo(Long id) {
        try {
            Integer count = grainInfoService.delete(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count",count);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 新增颗粒号信息
     * @param
     * @return
     */
    @RequestMapping(value = "/insertGrainInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertGrainInfo() {
        try {
            Long enterpriseId =  SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
            if (null == enterpriseId || enterpriseId < 1) {
                return Response.fail("企业id不能为空");
            }
            GrainInfo grainInfoTemp = new GrainInfo();
            grainInfoTemp.setEnterprId(enterpriseId);
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprise(enterpriseId);
            grainInfoTemp.setEnterprName(enterprUserInfo.getEnterprName());
            grainInfoTemp.setStatus(GrainInfoConstant.UNDER_VIEW_STATUS_VALUE);
            grainInfoTemp.setStatusValue(GrainInfoStatusEnum.
                    getGrainInfoStatus(grainInfoTemp.getStatus(), GrainInfoStatusEnum.BE_PENDING).getDesc());
            grainInfoTemp.setServiceStatus(GrainInfoConstant.BE_PENDING_SERVICE_STATUS_VALUE);
            grainInfoTemp.setServiceStatusValue(GrainInfoServiceStatusEnum.
                    getGrainInfoServiceStatus(grainInfoTemp.getServiceStatus(), GrainInfoServiceStatusEnum.BE_PENDING).getDesc());
            grainInfoTemp.setApplyTime(new Date());
            grainInfoTemp.setCumulationReadQuantity(GrainInfoConstant.CUMULATION_READ_QUANTITY_INIT_VALUE);
            grainInfoTemp.setEstablishedTime(new Date(GrainInfoConstant.ESTABLISHED_TIME_INIT_VALUE));
            grainInfoTemp.setLeftGrainValue(GrainInfoConstant.LEFT_GRAIN_INIT_VALUE);
            grainInfoTemp.setReceiveValue(GrainInfoConstant.RECEIVE_VALUE_INIT_VALUE);
            grainInfoTemp.setTypeList("[{'grain':false}]");
            grainInfoTemp.setCreated((int)(System.currentTimeMillis()/1000));
            grainInfoTemp.setUpdated((int)(System.currentTimeMillis()/1000));
            GrainInfo grainInfoRes = grainInfoService.insert(grainInfoTemp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainInfoRes",grainInfoRes);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 修改颗粒号信息
     * @param grainInfo
     * @return
     */
    @RequestMapping(value = "/updateGrainInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateGrainInfo(@RequestBody GrainInfo grainInfo) {
        try {
            Integer count = grainInfoService.updateByPrimaryKeySelective(grainInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count",count);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 查询颗粒号信息列表
     * @param grainInfo
     * @return
     */
    @RequestMapping(value = "/findGrainInfos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainInfos(@RequestBody(required = false) GrainInfo grainInfo) {
        try {
            List<GrainInfo> list = grainInfoService.findGrainInfoList(grainInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list",list);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 根据ID查询颗粒号信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGrainInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGrainInfo(Long id) {
        try {
            GrainInfo grainInfo = grainInfoService.selectByPrimaryKey(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainInfo",grainInfo);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }
}
