package com.lx.benefits.web.controller.admin.grain;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.grain.GrainInfoConstant;
import com.lx.benefits.bean.dto.grain.GrainInfo;
import com.lx.benefits.bean.enums.grain.GrainInfoServiceStatusEnum;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Query;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.grain.GrainInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/27
 * Time:12:29
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "运营后台-管理员颗粒号信息模块")
@RestController
@RequestMapping("/admin/grain")
public class GrainInfoAdminController {

    private static final Logger log = LoggerFactory.getLogger(GrainInfoAdminController.class);

    @Autowired
    private GrainInfoService grainInfoService;

    /**
     * 修改颗粒号信息
     * @param grainInfo
     * @return
     */
    @RequestMapping(value = "/updateGrainInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateGrainInfo(@RequestBody GrainInfo grainInfo) {
        try {
            if(GrainInfoConstant.PASS_STATUS_VALUE.equals(grainInfo.getStatus())){
                grainInfo.setServiceStatus(GrainInfoConstant.NORMAL_SERVICE_STATUS_VALUE);
                grainInfo.setEstablishedTime(new Date());
            }
            if(GrainInfoConstant.SUSPEND_SERVICE_STATUS_VALUE.equals(grainInfo.getServiceStatus())){
                grainInfo.setIsSuspend(GrainInfoConstant.NO_IS_SUSPEND);
                grainInfo.setTypeList("[{'grain':false}]");
            }else if(GrainInfoConstant.NORMAL_SERVICE_STATUS_VALUE.equals(grainInfo.getServiceStatus())){
                grainInfo.setIsSuspend(GrainInfoConstant.YES_IS_SUSPEND);
                grainInfo.setTypeList("[{'grain':true}]");
            }
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
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainInfoListByServiceStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainInfoListByServiceStatus(@RequestBody Map<String,Object> params) {
        try {
            Query query = new Query(params);
            List<GrainInfo> list = grainInfoService.findGrainInfoListByServiceStatus(query);
            for(GrainInfo grainInfo : list){
                grainInfo.setServiceStatusValue(GrainInfoServiceStatusEnum.
                        getGrainInfoServiceStatus(grainInfo.getServiceStatus(),
                                GrainInfoServiceStatusEnum.BE_PENDING).getDesc());
            }
            Integer count = grainInfoService.countGrainInfoListByServiceStatus();
            PageResultBean<GrainInfo> pageResultBean = new PageResultBean<>();
            Integer page = 0;
            if (Integer.parseInt(query.get("page").toString()) == 0) {
                page = 1;
            } else {
                page = Integer.parseInt(query.get("page").toString()) / Integer.parseInt(query.get("pageSize").toString()) + 1;
            }

            pageResultBean.setPage(page);
            pageResultBean.setPageSize(Integer.parseInt(query.get("pageSize").toString()));
            pageResultBean.setList(list);
            pageResultBean.setCount(count);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list",pageResultBean);
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
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainInfoListByVerifyStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainInfoListByVerifyStatus(@RequestBody Map<String,Object> params) {
        try {
            Query query = new Query(params);
            List<GrainInfo> list = grainInfoService.findGrainInfoListByverifyStatus(query);
            Integer count = grainInfoService.countGrainInfoListByverifyStatus();
            PageResultBean<GrainInfo> pageResultBean = new PageResultBean<>();
            Integer page = 0;
            if (Integer.parseInt(query.get("page").toString()) == 0) {
                page = 1;
            } else {
                page = Integer.parseInt(query.get("page").toString()) / Integer.parseInt(query.get("pageSize").toString()) + 1;
            }

            pageResultBean.setPage(page);
            pageResultBean.setPageSize(Integer.parseInt(query.get("pageSize").toString()));
            pageResultBean.setList(list);
            pageResultBean.setCount(count);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("list",pageResultBean);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }


}
