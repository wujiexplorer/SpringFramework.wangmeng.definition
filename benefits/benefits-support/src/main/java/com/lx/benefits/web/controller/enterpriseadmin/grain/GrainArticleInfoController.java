package com.lx.benefits.web.controller.enterpriseadmin.grain;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.grain.GrainArticleInfoConstant;
import com.lx.benefits.bean.dto.grain.GrainArticleInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.grain.GrainArticleInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:15:32
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "企业平台-颗粒文章信息模块")
@RestController
@RequestMapping("/enterpriseadmin/grain")
public class GrainArticleInfoController {

    private static final Logger log = LoggerFactory.getLogger(GrainArticleInfoController.class);

    @Autowired
    private GrainArticleInfoService grainArticleInfoService;

    @Autowired
    private EnterpriseService enterpriseService;

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
            String loginName = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            GrainArticleInfo grainArticleInfoRes = new GrainArticleInfo();
            BeanUtils.copyProperties(grainArticleInfo,grainArticleInfoRes);
            grainArticleInfoRes.setReadQuantity(GrainArticleInfoConstant.READ_QUANTITY_INIT_VALUE);
            grainArticleInfoRes.setUpdated((int)(System.currentTimeMillis()/1000));
            grainArticleInfoRes.setCreated((int)(System.currentTimeMillis()/1000));
            grainArticleInfoRes.setPublishPersonId(GrainArticleInfoConstant.PUBLISH_PERSON_ID_DEFAULT_VALUE);
            grainArticleInfoRes.setStatus(GrainArticleInfoConstant.SUSPEND_AWARD_STATUS_VALUE);
            grainArticleInfoRes.setUpdateTime(new Date());
            grainArticleInfoRes.setPublishTime(new Date());
            grainArticleInfoRes.setPublishEnterprise(enterprUserInfo.getEnterprName());
            grainArticleInfoRes.setGrainId(enterprUserInfo.getGrainId());
            grainArticleInfoRes.setVerifyStatus(GrainArticleInfoConstant.NOT_READ_VERIFY_STATUS_VALUE);
            grainArticleInfoRes.setSingleGrainAwardValue(GrainArticleInfoConstant.SINGLE_GRAIN_AWARD_INIT_VALUE);
            grainArticleInfoRes.setIsHot(GrainArticleInfoConstant.NOT_HOT_VALUE);
            grainArticleInfoRes.setCumulationAwardValue(GrainArticleInfoConstant.CUMULATION_READ_TIME_INIT_VALUE);
            Map<String,Object> paramsTemp = new HashMap<>();
            paramsTemp.put("page",1);
            paramsTemp.put("pageSize", Integer.MAX_VALUE);
            paramsTemp.put("grainId",enterprUserInfo.getGrainId());
            Query queryTemp = new Query(paramsTemp);
            List<GrainArticleInfo> grainArticleInfoList = grainArticleInfoService.findGrainArticleInfoList(queryTemp);
            if(grainArticleInfoList.isEmpty()){
                grainArticleInfoRes.setArticleReadTime(GrainArticleInfoConstant.ARTICLE_READ_TIME_INIT_VALUE);
            }else{
                Integer size = grainArticleInfoList.size();
                grainArticleInfoRes.setArticleReadTime(grainArticleInfoList.get(size-1).getArticleReadTime());
            }
            GrainArticleInfo grainArticleInfoTemp = grainArticleInfoService.insert(grainArticleInfoRes);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainArticleInfo",grainArticleInfoTemp);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
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
    @Transactional
    public JSONObject updateGrainArticleInfo(@RequestBody GrainArticleInfo grainArticleInfo) {
        try {
            String loginName = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            GrainArticleInfo grainArticleInfoRes = new GrainArticleInfo();
            BeanUtils.copyProperties(grainArticleInfo,grainArticleInfoRes);
            Map<String,Object> params = new HashMap<>();
            params.put("page",1);
            params.put("pageSize",Integer.MAX_VALUE);
            Query query = new Query(params);
            List<GrainArticleInfo> grainArticleInfoList = grainArticleInfoService.findGrainArticleInfoListByOpenAwardStatus(query);
            for(GrainArticleInfo grainArticleInfoTemp : grainArticleInfoList){
                if(grainArticleInfoTemp.getIsHot().equals(grainArticleInfo.getIsHot())){
                    grainArticleInfoTemp.setIsHot(GrainArticleInfoConstant.NOT_HOT_VALUE);
                    grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfoTemp);
                }
            }
            if(GrainArticleInfoConstant.OPEN_AWARD_STATUS_VALUE.equals(grainArticleInfoRes.getStatus())){
                grainArticleInfoRes.setVerifyStatus(GrainArticleInfoConstant.NOT_READ_VERIFY_STATUS_VALUE);
            }
            if(grainArticleInfo.getArticleReadTime() != null){
                grainArticleInfoRes.setGrainId(enterprUserInfo.getGrainId());
            }
            Integer count = grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfoRes);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count",count);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 不加@RequestBody就是表单提交形式
     * 查询颗粒文章信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainArticleInfos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainArticleInfos(@RequestBody  Map<String,Object> params) {
        try {
            String loginName = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            if(GrainArticleInfoConstant.BE_PENDING_STATUS_VALUE.equals(params.get("status"))){
                params.put("grainId",0);
            }else{
                params.put("grainId",enterprUserInfo.getGrainId());
            }
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
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 不加@RequestBody就是表单提交形式
     * 根据状态查询颗粒文章信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainArticleInfoListByStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainArticleInfoListByStatus(@RequestBody  Map<String,Object> params) {
        try {
            String loginName = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            params.put("grainId",enterprUserInfo.getGrainId());
            Query query = new Query(params);
            List<GrainArticleInfo> list = grainArticleInfoService.findGrainArticleInfoListByStatus(query);
            Integer count = grainArticleInfoService.countGrainArticleInfoListByStatus(params);
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
            String loginName = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            params.put("grainId",enterprUserInfo.getGrainId());
            String enterprName = SessionContextHolder.getSessionEnterpriseInfo().getEnterprName();
            Query query = new Query(params);
            List<GrainArticleInfo> list = grainArticleInfoService.findGrainArticleInfoListByVerifyStatus(query);
            for(GrainArticleInfo grainArticleInfoTemp : list){
                grainArticleInfoTemp.setEnterprName(enterprName);
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
            return Response.fail(e.getMessage());
        }
    }


    /**
     * 根据ID查询颗粒奖励信息
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
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 查询颗粒文章信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainArticleInfoListByOpenAwardStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainArticleInfoListByOpenAwardStatus(@RequestBody Map<String,Object> params) {
        try {
            String loginName = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            params.put("grainId",enterprUserInfo.getGrainId());
            Query query = new Query(params);
            List<GrainArticleInfo> list = grainArticleInfoService.findGrainArticleInfoListByOpenAwardStatus(query);
            Integer count = grainArticleInfoService.countGrainArticleInfoListByOpenAwardStatus(params);
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
            return Response.fail(e.getMessage());
        }
    }

}
