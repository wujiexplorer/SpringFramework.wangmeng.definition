package com.lx.benefits.web.controller.enterpriseadmin.grain;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.grain.GrainInfoConstant;
import com.lx.benefits.bean.constants.grain.GrainOptInfoConstant;
import com.lx.benefits.bean.dto.grain.GrainInfo;
import com.lx.benefits.bean.dto.grain.GrainOptInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.grain.GrainInfoServiceStatusEnum;
import com.lx.benefits.bean.enums.grain.GrainInfoStatusEnum;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Query;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.service.enterprcredit.EnterprCreditInfoService;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.grain.GrainArticleInfoService;
import com.lx.benefits.service.grain.GrainInfoService;
import com.lx.benefits.service.grain.GrainOptInfoService;
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
 * Date:2019/5/20
 * Time:15:32
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "企业平台-颗粒号信息模块")
@RestController
@RequestMapping("/enterpriseadmin/grain")
public class GrainInfoController {

    private static final Logger log = LoggerFactory.getLogger(GrainInfoController.class);

    @Autowired
    private GrainInfoService grainInfoService;

    @Autowired
    private GrainOptInfoService grainOptInfoService;


    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;

    @Autowired
    private GrainArticleInfoService grainArticleInfoService;

    @Autowired
    private EnterprCreditInfoService enterprCreditInfoService;


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
     * @param grainInfo
     * @return
     */
    @RequestMapping(value = "/insertGrainInfoTemp", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertGrainInfoTemp(@RequestBody GrainInfo grainInfo) {
        try {
            GrainInfo grainInfoTemp = grainInfoService.insert(grainInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainInfoTemp",grainInfoTemp);
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
    @Transactional
    public JSONObject insertGrainInfo() {
        try {
            Long enterpriseId =  SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
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
            //更新企业绑定的颗粒号
            updateGrainIdByEnterpriseId(enterpriseId,grainInfoRes.getGrainId());
            //根据员工ID更新企业绑定的颗粒号
            updateGrainIdByEmployeeId(enterpriseId,grainInfoRes.getGrainId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainInfoRes",grainInfoRes);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 修改颗粒号信息
     * @param grainInfo
     * @return
     */
    @RequestMapping(value = "/updateGrainInfo", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public JSONObject updateGrainInfo(@RequestBody GrainInfo grainInfo) {
        try {
            Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
            if(null == enterprId){
                throw new BusinessException("enterprId企业ID不能为空！");
            }
            grainInfo.setUpdated((int)(System.currentTimeMillis()/1000));
            grainInfo.setTypeList("[{'grain':true}]");
            if(GrainInfoConstant.PASS_STATUS_VALUE.equals(grainInfo.getStatus())){
                grainInfo.setStatus(GrainInfoConstant.PASS_STATUS_VALUE);
                grainInfo.setServiceStatus(GrainInfoConstant.NORMAL_SERVICE_STATUS_VALUE);
                grainInfo.setEstablishedTime(new Date());
            }else if(GrainInfoConstant.FREEZE_STATUS_VALUE.equals(grainInfo.getStatus())){
                grainInfo.setStatus(GrainInfoConstant.FREEZE_STATUS_VALUE);
                grainInfo.setServiceStatus(GrainInfoConstant.BE_PENDING_SERVICE_STATUS_VALUE);
            }
            if(GrainInfoServiceStatusEnum.SUSPEND.equals(grainInfo.getServiceStatus())){
                grainInfo.setServiceStatus(GrainInfoConstant.SUSPEND_SERVICE_STATUS_VALUE);
            }
            GrainInfo grainInfoTemp = grainInfoService.selectByPrimaryKey(grainInfo.getGrainId());
            if(grainInfo.getLeftGrainValue() != null && grainInfo.getLeftGrainValue() != 0){
                grainInfo.setLeftGrainValue(grainInfoTemp.getLeftGrainValue()- grainInfo.getLeftGrainValue());
            }

            Map<String,Object> params = new HashMap<>();
            params.put("enterprId",enterprId);
            params.put("receiveValue",grainInfo.getReceiveValue());
            Integer calCount = enterprCreditInfoService.updateValidCreditByEnterprId(params);

            if(grainInfo.getReceiveValue() != null && grainInfo.getReceiveValue() != 0){
                //保存颗粒号操作信息
                saveGrainOptInfo(grainInfo.getGrainId(),grainInfo.getReceiveValue());
                //两个语句不能颠倒
                grainInfo.setLeftGrainValue(grainInfoTemp.getLeftGrainValue() + grainInfo.getReceiveValue());
                grainInfo.setReceiveValue(grainInfoTemp.getReceiveValue()+grainInfo.getReceiveValue());
            }
            Integer count = grainInfoService.updateByPrimaryKeySelective(grainInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count",count);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            //用事务注解时，需要抛出异常
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查询颗粒号信息列表
     * @param grainInfo
     * @return
     */
    @RequestMapping(value = "/findGrainInfos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainInfos(@RequestBody GrainInfo grainInfo) {
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
     * 查询颗粒号信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainInfoListByStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainInfoListByStatus(@RequestBody Map<String,Object> params) {
        try {
            Query query = new Query(params);
            List<GrainInfo> list = grainInfoService.findGrainInfoListByStatus(query);
            //list指针指向的内容并没有改变，内容可以改变，不会影响list指针
            for(GrainInfo grainInfo : list){
                grainInfo.setStatusValue(GrainInfoStatusEnum.
                        getGrainInfoStatus(grainInfo.getStatus(),
                        GrainInfoStatusEnum.BE_PENDING).getDesc());
            }
            Integer count = grainInfoService.countGrainInfoListByStatus();
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

    /**
     * 根据ID查询颗粒号信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCalculateEndGrainInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getCalculateEndGrainInfo(Long id) {
        try {
            Map<String,Object> params = new HashMap<>();
            String loginName = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            params.put("grainId",enterprUserInfo.getGrainId());
            GrainInfo grainInfo = grainInfoService.selectByPrimaryKey(id);
            Integer count = grainArticleInfoService.calculateGrainArticleInfoListByOpenAwardStatus(params);
            grainInfo.setCumulationReadQuantity(count);
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

    /**
     * 保存颗粒号操作信息
     */
    private void saveGrainOptInfo(Long grainId,Double receiveCreditValue){
        GrainOptInfo grainOptInfo = new GrainOptInfo();
        grainOptInfo.setCreated((int)(System.currentTimeMillis()/1000));
        grainOptInfo.setUpdated((int)(System.currentTimeMillis()/1000));
        grainOptInfo.setParentOptId(GrainOptInfoConstant.PARENT_OPT_INFO_INIT_VALUE);
        Long enterpriseId =  SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
        if (null == enterpriseId || enterpriseId < 1) {
            throw new BusinessException("企业ID不能为空或0");
        }
        grainOptInfo.setEnterprId(enterpriseId);
        EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprise(enterpriseId);
        grainOptInfo.setEnterprName(enterprUserInfo.getEnterprName());
        grainOptInfo.setGrainId(grainId);
        grainOptInfo.setReceiveCreditValue(receiveCreditValue);
        grainOptInfo.setReceiveTime(new Date());
        grainOptInfoService.insert(grainOptInfo);
    }

    /**
     * 更新企业绑定的颗粒号
     */
    private void updateGrainIdByEnterpriseId(Long enterpriseId,Long grainId){
        EnterprUserInfo enterprUserInfo = new EnterprUserInfo();
        enterprUserInfo.setGrainId(grainId);
        enterpriseService.updateContactinfo(enterpriseId,enterprUserInfo);
    }

    private void updateGrainIdByEmployeeId(Long enterpriseId,Long grainId){
        List<EmployeeUserInfo> list = employeeUserInfoService.selectListByEnterprId(enterpriseId);
        for(EmployeeUserInfo employeeUserInfo :list){
            EmployeeCreditInfo employeeCreditInfo = new EmployeeCreditInfo();
            employeeCreditInfo.setEmployeeId(employeeUserInfo.getEmployeeId());
            employeeCreditInfo.setGrainId(grainId);
            employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfo);
        }
    }
}
