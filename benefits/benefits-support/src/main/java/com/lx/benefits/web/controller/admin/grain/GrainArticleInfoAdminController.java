package com.lx.benefits.web.controller.admin.grain;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.grain.GrainArticleInfoConstant;
import com.lx.benefits.bean.dto.grain.GrainArticleInfo;
import com.lx.benefits.bean.dto.grain.GrainInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.grain.GrainArticleInfoStatusEnum;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.grain.GrainArticleInfoService;
import com.lx.benefits.service.grain.GrainInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:15:32
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "运营后台-管理员颗粒文章信息模块")
@RestController
@RequestMapping("/admin/grain")
public class GrainArticleInfoAdminController {

    private static final Logger log = LoggerFactory.getLogger(GrainArticleInfoAdminController.class);

    @Autowired
    private GrainArticleInfoService grainArticleInfoService;

    @Autowired
    private GrainInfoService grainInfoService;

    /**
     * 删除颗粒文章信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteGrainArticleInfo", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject deleteGrainArticleInfo(Long id) {
        try {
            Integer count = grainArticleInfoService.delete(id);
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
     * 新增颗粒文章信息
     * @param grainArticleInfo
     * @return
     */
    @RequestMapping(value = "/insertGrainArticleInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertGrainArticleInfo(@RequestBody GrainArticleInfo grainArticleInfo) {
        try {
            //String loginName = SessionContextHolder.getSessionFuliInfo().getLoginName();
            Long adminId = SessionContextHolder.getSessionFuliInfo().getAdminId();
            GrainArticleInfo grainArticleInfoRes = new GrainArticleInfo();
            //copy grainArticleInfo的参数值到grainArticleInfoRes
            BeanUtils.copyProperties(grainArticleInfo,grainArticleInfoRes);
            grainArticleInfoRes.setPublishTime(new Date());
            grainArticleInfoRes.setReadQuantity(GrainArticleInfoConstant.READ_QUANTITY_INIT_VALUE);
            grainArticleInfoRes.setArticleReadTime(GrainArticleInfoConstant.ARTICLE_READ_TIME_INIT_VALUE);
            grainArticleInfoRes.setCreated((int)(System.currentTimeMillis()/1000));
            grainArticleInfoRes.setUpdated((int)(System.currentTimeMillis()/1000));
            grainArticleInfoRes.setCumulationAwardValue(GrainArticleInfoConstant.CUMULATION_READ_TIME_INIT_VALUE);
            grainArticleInfoRes.setIsCustom(GrainArticleInfoConstant.NOT_CUSTOM_VALUE);
            grainArticleInfoRes.setIsHot(GrainArticleInfoConstant.NOT_HOT_VALUE);
            grainArticleInfoRes.setGrainId(0L);
            grainArticleInfoRes.setPublishPersonId(adminId);
            grainArticleInfoRes.setSingleGrainAwardValue(GrainArticleInfoConstant.SINGLE_GRAIN_AWARD_INIT_VALUE);
            grainArticleInfoRes.setStatus(GrainArticleInfoConstant.BE_PENDING_STATUS_VALUE);
            grainArticleInfoRes.setStatusValue(GrainArticleInfoStatusEnum.
                    getGrainArticleInfoStatusEnum(grainArticleInfoRes.getStatus(),
                            GrainArticleInfoStatusEnum.BE_PENDING).getDesc());
            grainArticleInfoRes.setUpdateTime(new Date());
            GrainArticleInfo grainArticleInfoTemp = grainArticleInfoService.insert(grainArticleInfoRes);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainArticleInfo",grainArticleInfoTemp);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 修改颗粒文章信息
     * @param grainArticleInfo
     * @return
     */
    @RequestMapping(value = "/updateGrainArticleInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateGrainArticleInfo(@RequestBody GrainArticleInfo grainArticleInfo) {
        try {
            GrainArticleInfo grainArticleInfoRes = new GrainArticleInfo();
            BeanUtils.copyProperties(grainArticleInfo,grainArticleInfoRes);
            grainArticleInfoRes.setUpdateTime(new Date());
            grainArticleInfoRes.setUpdated((int)(System.currentTimeMillis()/1000));
            Integer count = grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfoRes);
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
     * 不加@RequestBody就是表单提交形式
     * 根据审核状态查询颗粒文章信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainArticleInfoListByVerifyStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainArticleInfoListByVerifyStatus(@RequestBody  Map<String,Object> params) {
        try {
            Query query = new Query(params);
            List<GrainArticleInfo> list = grainArticleInfoService.findGrainArticleInfoListByVerifyStatus(query);
            for(GrainArticleInfo grainArticleInfoTemp : list){
                GrainInfo grainInfoTemp = grainInfoService.selectByPrimaryKey(grainArticleInfoTemp.getGrainId());
                if(grainInfoTemp != null){
                    if(params.get("publishEnterprise")== null){
                        grainArticleInfoTemp.setEnterprName(grainInfoTemp.getEnterprName());
                    }else if(grainInfoTemp.getEnterprName().contains(params.get("publishEnterprise").toString())){
                        grainArticleInfoTemp.setEnterprName(grainInfoTemp.getEnterprName());
                    }else{
                        grainArticleInfoTemp.setEnterprName(null);
                    }

                }
            }
            Integer count = grainArticleInfoService.countGrainArticleInfoListByVerifyStatus(params);
            JSONObject jsonObject = new JSONObject();
            PageResultBean<GrainArticleInfo> pageResultBean = new PageResultBean<>();
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
     * 查询颗粒文章信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainArticleInfos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainArticleInfos(@RequestBody Map<String,Object> params) {
        try {
            Query query = new Query(params);
            List<GrainArticleInfo> list = grainArticleInfoService.findGrainArticleInfoList(query);
            Integer count = grainArticleInfoService.countGrainArticleInfoList(params);
            JSONObject jsonObject = new JSONObject();
            PageResultBean<GrainArticleInfo> pageResultBean = new PageResultBean<>();
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
     * 根据ID查询颗粒文章信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGrainArticleInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGrainArticleInfo(Long id) {
        try {
            GrainArticleInfo grainArticleInfo = grainArticleInfoService.selectByPrimaryKey(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainArticleInfo",grainArticleInfo);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

}
