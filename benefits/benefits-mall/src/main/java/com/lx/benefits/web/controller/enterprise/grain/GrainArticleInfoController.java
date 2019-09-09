package com.lx.benefits.web.controller.enterprise.grain;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.grain.GrainArticleInfoConstant;
import com.lx.benefits.bean.constants.grain.GrainInfoConstant;
import com.lx.benefits.bean.dto.grain.GrainArticleAward;
import com.lx.benefits.bean.dto.grain.GrainArticleInfo;
import com.lx.benefits.bean.dto.grain.GrainInfo;
import com.lx.benefits.bean.dto.grain.GrainSpecialPerson;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.OptTypeEnum;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.grain.GrainArticleAwardService;
import com.lx.benefits.service.grain.GrainArticleInfoService;
import com.lx.benefits.service.grain.GrainInfoService;
import com.lx.benefits.service.grain.GrainSpecialPersonService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:15:32
 * Verision:2.x
 * Description:TODO
 **/
@RestController
@RequestMapping("/enterprise/grain")
@Api(tags = "企业商城-颗粒文章信息模块")
public class GrainArticleInfoController {

    private static final Logger log = LoggerFactory.getLogger(GrainArticleInfoController.class);

    @Autowired
    private GrainArticleInfoService grainArticleInfoService;

    @Autowired
    private GrainSpecialPersonService grainSpecialPersonService;


    @Autowired
    private GrainArticleAwardService grainArticleAwardService;


    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;


    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private GrainInfoService grainInfoService;

    @Autowired
    private CreditOperateInfoService creditOperateInfoService;

    /**
     * 删除颗粒文章信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteGrainArticleInfo", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject deleteGrainArticleInfo(Long id) {
        try {
            Integer count = grainArticleInfoService.delete(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", count);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 新增颗粒文章信息
     *
     * @param grainArticleInfo
     * @return
     */
    @RequestMapping(value = "/insertGrainArticleInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertGrainArticleInfo(@RequestBody GrainArticleInfo grainArticleInfo) {
        try {
            GrainArticleInfo grainArticleInfoTemp = grainArticleInfoService.insert(grainArticleInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainArticleInfo", grainArticleInfoTemp);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 修改颗粒文章信息
     *
     * @param grainArticleInfo
     * @return
     */
    @RequestMapping(value = "/updateGrainArticleInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateGrainArticleInfo(@RequestBody GrainArticleInfo grainArticleInfo) {
        try {
            Integer count = grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", count);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 查询颗粒文章信息列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainArticleInfoListBySuspendOpenAwardStatus", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainArticleInfoListByOpenAwardStatus(@RequestBody Map<String, Object> params) {
        try {
            Long enterprId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprise(enterprId);
            GrainInfo grainInfoTemp = grainInfoService.selectByPrimaryKey(enterprUserInfo.getGrainId());
            if (GrainInfoConstant.NO_IS_SUSPEND.equals(grainInfoTemp.getIsSuspend())) {

            } else if (GrainInfoConstant.YES_IS_SUSPEND.equals(grainInfoTemp.getIsSuspend())) {
                Map<String,Object> paramsTemp = new HashMap<>();
                paramsTemp.put("page",1);
                paramsTemp.put("pageSize",Integer.MAX_VALUE);
                params.put("grainId", enterprUserInfo.getGrainId());
                paramsTemp.put("grainId", enterprUserInfo.getGrainId());
                Query query = new Query(paramsTemp);
                List<GrainArticleInfo> list = grainArticleInfoService.findGrainArticleInfoListBySuspendOpenAwardStatus(query);
                Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
                for (GrainArticleInfo grainArticleInfo : list) {
                    grainArticleInfo.setIsScreen(0);
                    if(GrainArticleInfoConstant.ALREADY_SCREEN_VERIFY_STATUS_VALUE.equals(grainArticleInfo.getVerifyStatus())){
                        grainArticleInfo.setIsScreen(1);
                    }
                    grainArticleInfo.setCumulationAwardValue(0.0);
                    grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfo);
                }
                params.put("isScreen",0);
                query = new Query(params);
                list = grainArticleInfoService.findGrainArticleInfoListBySuspendOpenAwardStatus(query);
                for (GrainArticleInfo grainArticleInfo : list) {
                    EmployeeCreditInfo employeeCreditInfo = new EmployeeCreditInfo();
                    employeeCreditInfo.setEmployeeId(employeeId);
                    employeeCreditInfo.setGrainId(grainArticleInfo.getGrainId());
                    EmployeeCreditInfo employeeCreditInfoTemp = employeeCreditInfoService.getByEmployeeIdAndGrainId(employeeCreditInfo);
                    Boolean flag = false;
                    grainArticleInfo.setIsScreen(0);
                    if (null == employeeCreditInfoTemp) {
                        flag = false;
                    } else {
                        List<Long> articleIdList = JsonUtil.parseObject(employeeCreditInfoTemp.getArticleIdList(), new TypeReference<List<Long>>() {
                        });
                        if (articleIdList != null && articleIdList.contains(grainArticleInfo.getArticleId())) {
                            flag = true;
                        }
                    }
                    if(GrainArticleInfoConstant.SUSPEND_AWARD_STATUS_VALUE.equals(grainArticleInfo.getStatus())){
                        flag = true;
                    }
                    if(GrainArticleInfoConstant.ALREADY_SCREEN_VERIFY_STATUS_VALUE.equals(grainArticleInfo.getVerifyStatus())){
                        grainArticleInfo.setIsScreen(1);
                    }
                    grainArticleInfo.setFlag(flag);
                    grainArticleInfo.setCumulationAwardValue(0.0);
                    grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfo);
                }
                Integer count = grainArticleInfoService.countGrainArticleInfoListBySuspendOpenAwardStatus(params);
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
                jsonObject.put("list", pageResultBean);
                log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
                return Response.succ(jsonObject);
            }
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            return Response.fail(e.getMessage());
        }
        return Response.succ();
    }


    /**
     * 根据ID查询颗粒奖励信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGrainArticleInfoNoLanding", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGrainArticleInfoNoLanding(Long id) {
        try {
            GrainArticleInfo grainArticleInfo = grainArticleInfoService.selectByPrimaryKey(id);

            if(GrainArticleInfoConstant.NOT_READ_VERIFY_STATUS_VALUE.
                    equals(grainArticleInfo.getVerifyStatus())){
                grainArticleInfo.setVerifyStatus(GrainArticleInfoConstant.ALREADY_READ_VERIFY_STATUS_VALUE);

            }
            //grainArticleInfo.setReadQuantity(grainArticleInfo.getReadQuantity() + 1);
            //解决查看颗粒文章详情时，累计阅读量加倍问题
            grainArticleInfo.setCumulationAwardValue(0.0);
            grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfo);
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
     * 根据ID查询颗粒奖励信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGrainArticleInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGrainArticleInfo(Long id,@RequestParam(value = "flagTemp",required = false) Integer flagTemp) {
        try {
            GrainArticleInfo grainArticleInfo = grainArticleInfoService.selectByPrimaryKey(id);
            Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
            EmployeeCreditInfo employeeCreditInfo = new EmployeeCreditInfo();
            employeeCreditInfo.setEmployeeId(employeeId);
            employeeCreditInfo.setGrainId(grainArticleInfo.getGrainId());
            EmployeeCreditInfo employeeCreditInfoTemp = employeeCreditInfoService.getByEmployeeIdAndGrainId(employeeCreditInfo);
            Boolean flag = false;
            if(null == employeeCreditInfoTemp){
                flag = false;
            }else {
                List<Long> list = JsonUtil.parseObject(employeeCreditInfoTemp.getArticleIdList(),new TypeReference<List<Long>>(){});
                if(list != null && list.contains(id)){
                    flag = true;
                }
            }
            if(GrainArticleInfoConstant.SUSPEND_AWARD_STATUS_VALUE.equals(grainArticleInfo.getStatus())){
                flag = true;
            }
            if(flagTemp ==  null){
                if(GrainArticleInfoConstant.NOT_READ_VERIFY_STATUS_VALUE.
                        equals(grainArticleInfo.getVerifyStatus())){
                    grainArticleInfo.setVerifyStatus(GrainArticleInfoConstant.ALREADY_READ_VERIFY_STATUS_VALUE);

                }
            }else if(flagTemp == 1){
                //no handle  flagTemp 没这个变量也为null
            }
            grainArticleInfo.setReadQuantity(grainArticleInfo.getReadQuantity() + 1);
            //解决查看颗粒文章详情时，累计阅读量加倍问题
            grainArticleInfo.setCumulationAwardValue(0.0);
            grainArticleInfo.setIsRead(0);
            grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainArticleInfo",grainArticleInfo);
            jsonObject.put("flag",flag);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 员工确认颗粒奖励信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/confirmGrainArticleAwardInfo", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public JSONObject confirmGrainArticleAwardInfo(Long id) {
        try {
            String employeeNo = SessionContextHolder.getSessionEmployeeInfo().getEmployeeNo();
            Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
            Long enterprId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
            String employeeName = SessionContextHolder.getSessionEmployeeInfo().getEmployeeName();
            GrainArticleInfo grainArticleInfo = grainArticleInfoService.selectByPrimaryKey(id);
            Double awardGrainValue = null;
            GrainArticleAward grainArticleAward = new GrainArticleAward();
            if(grainArticleInfo != null){
                awardGrainValue = grainArticleInfo.getSingleGrainAwardValue();
                Map<String,Object> params = new HashMap<>();
                params.put("enterprId",enterprId);
                params.put("page",0);
                params.put("pageSize",Integer.MAX_VALUE);
                Query query = new Query(params);
                List<GrainSpecialPerson> list = grainSpecialPersonService.findGrainSpecialPersonList(query);
                List<String> specialPersonEmployeeNos = new ArrayList<>();
                for(GrainSpecialPerson grainSpecialPerson : list){
                    String specialPersonEmployeeNo = grainSpecialPerson.getEmployeeNo();
                    specialPersonEmployeeNos.add(specialPersonEmployeeNo);
                }
                if(specialPersonEmployeeNos.contains(employeeNo)){
                    params.clear();
                    params.put("employeeNo",employeeNo);
                    params.put("page",0);
                    params.put("pageSize",Integer.MAX_VALUE);
                    List<GrainSpecialPerson> articleAwardList = grainSpecialPersonService.findGrainSpecialPersonList(params);
                    awardGrainValue = articleAwardList.get(0).getSingleAwardGrainValue();
                    grainArticleAward.setAwardGrainValue(awardGrainValue);
                    EmployeeCreditInfo employeeCreditInfo = new EmployeeCreditInfo();
                    employeeCreditInfo.setGrainValue(awardGrainValue);
                    employeeCreditInfo.setEmployeeId(employeeId);
                    employeeCreditInfo.setGrainId(grainArticleInfo.getGrainId());
                    EmployeeCreditInfo employeeCreditInfoTemp2 = employeeCreditInfoService.getEmployeeCreditInfo(employeeId,0L);
                    if(employeeCreditInfoTemp2 != null && employeeCreditInfoTemp2.getGrainId() == null){
                        EmployeeCreditInfo employeeCreditInfoTemp3 = new EmployeeCreditInfo();
                        employeeCreditInfoTemp3.setGrainId(grainArticleInfo.getGrainId());
                        employeeCreditInfoTemp3.setEmployeeId(employeeId);
                        employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp3);
                    }
                    EmployeeCreditInfo employeeCreditInfoTemp = employeeCreditInfoService.getByEmployeeIdAndGrainId(employeeCreditInfo);
                    StringBuilder stringBuilder = new StringBuilder();
                    employeeCreditInfo.setIsRead(1);
                    if(null == employeeCreditInfoTemp){
                        stringBuilder.append("["+id+"]");
                        employeeCreditInfo.setArticleIdList(stringBuilder.toString());
                        employeeCreditInfo.setCampaignId(0L);
                        employeeCreditInfo.setCreditType(1);
                        employeeCreditInfo.setCredit(new BigDecimal(awardGrainValue));
                        employeeCreditInfoService.insert(employeeCreditInfo);
                    }else{
                        if(StringUtil.isEmpty(employeeCreditInfoTemp.getArticleIdList())){
                            stringBuilder.append("["+id+"]");
                        }else{
                            String temp = employeeCreditInfoTemp.getArticleIdList().replace("]","");
                            stringBuilder.append(temp+","+id+"]");
                        }
                        employeeCreditInfo.setArticleIdList(stringBuilder.toString());
                        employeeCreditInfoService.updateByEmployeeIdSelective(employeeCreditInfo);
                    };

                }else{
                    grainArticleAward.setAwardGrainValue(grainArticleInfo.getSingleGrainAwardValue());
                    EmployeeCreditInfo employeeCreditInfo = new EmployeeCreditInfo();
                    employeeCreditInfo.setGrainValue(grainArticleInfo.getSingleGrainAwardValue());
                    employeeCreditInfo.setEmployeeId(employeeId);
                    employeeCreditInfo.setGrainId(grainArticleInfo.getGrainId());
                    EmployeeCreditInfo employeeCreditInfoTemp2 = employeeCreditInfoService.getEmployeeCreditInfo(employeeId,0L);
                    if(employeeCreditInfoTemp2 != null && employeeCreditInfoTemp2.getGrainId() == null){
                        EmployeeCreditInfo employeeCreditInfoTemp3 = new EmployeeCreditInfo();
                        employeeCreditInfoTemp3.setGrainId(grainArticleInfo.getGrainId());
                        employeeCreditInfoTemp3.setEmployeeId(employeeId);
                        employeeCreditInfoService.updateGrainIdByEmployeeIdSelective(employeeCreditInfoTemp3);
                    }
                    EmployeeCreditInfo employeeCreditInfoTemp = employeeCreditInfoService.getByEmployeeIdAndGrainId(employeeCreditInfo);
                    StringBuilder stringBuilder = new StringBuilder();
                    employeeCreditInfo.setIsRead(1);
                    if(null == employeeCreditInfoTemp){
                        stringBuilder.append("["+id+"]");
                        employeeCreditInfo.setArticleIdList(stringBuilder.toString());
                        employeeCreditInfo.setCampaignId(0L);
                        employeeCreditInfo.setCreditType(1);
                        employeeCreditInfo.setCredit(new BigDecimal(awardGrainValue));
                        employeeCreditInfoService.insert(employeeCreditInfo);
                    }else{
                        if(StringUtil.isEmpty(employeeCreditInfoTemp.getArticleIdList())){
                            stringBuilder.append("["+id+"]");
                        }else{
                            String temp = employeeCreditInfoTemp.getArticleIdList().replace("]","");
                            stringBuilder.append(temp+","+id+"]");
                        }
                        employeeCreditInfo.setArticleIdList(stringBuilder.toString());
                        employeeCreditInfoService.updateByEmployeeIdSelective(employeeCreditInfo);
                    }
                }
                grainArticleAward.setAwardGrainTime(new Date());
                grainArticleAward.setArticleId(id);
                grainArticleAward.setEmployeeName(employeeName);
                grainArticleAward.setEmployeeId(employeeId);
                grainArticleAward.setCreated((int)(System.currentTimeMillis()/1000));
                grainArticleAward.setUpdated((int)(System.currentTimeMillis()/1000));
                grainArticleAward = grainArticleAwardService.insert(grainArticleAward);
            }
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprise(enterprId);
            GrainInfo grainInfoTemp = grainInfoService.selectByPrimaryKey(enterprUserInfo.getGrainId());
            Double grainValue = grainInfoTemp.getLeftGrainValue() - awardGrainValue;
            if(grainValue <= 0.0){
                throw new BusinessException("企业剩余颗粒值不能为0！");
            }
            grainInfoTemp.setLeftGrainValue(grainValue);
            grainInfoService.updateByPrimaryKeySelective(grainInfoTemp);
            CreditOperateInfo creditOperateInfo = new CreditOperateInfo();
            creditOperateInfo.setCredit(new BigDecimal(awardGrainValue));
            creditOperateInfo.setEmployeeId(Integer.parseInt(employeeId.toString()));
            creditOperateInfo.setOptTime(System.currentTimeMillis());
            creditOperateInfo.setOwnerUserId(employeeId);
            creditOperateInfo.setOptUserId(employeeId);
            creditOperateInfo.setOptType(OptTypeEnum.HR_DISTRIBUTION_USER_ADD.getValue());
            creditOperateInfo.setRemark("阅读学习奖励积分");
            creditOperateInfoService.insert(creditOperateInfo);
            grainArticleInfo.setCumulationAwardValue(awardGrainValue);
            grainArticleInfoService.updateByPrimaryKeySelective(grainArticleInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("awardGrainValue",awardGrainValue==null?0.0:awardGrainValue);
            jsonObject.put("grainArticleAward",grainArticleAward);
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 员工确认颗粒奖励信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getSingleGrainArticleAwardInfo", method = RequestMethod.GET)
    @ResponseBody
        public JSONObject getSingleGrainArticleAwardInfo(Long id){
        try {
            String employeeNo = SessionContextHolder.getSessionEmployeeInfo().getEmployeeNo();
            Long enterprId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
            GrainArticleInfo grainArticleInfo = grainArticleInfoService.selectByPrimaryKey(id);
            Double awardGrainValue = null;
            awardGrainValue = grainArticleInfo.getSingleGrainAwardValue();
            Map<String,Object> params = new HashMap<>();
            params.put("enterprId",enterprId);
            params.put("page",0);
            params.put("pageSize",Integer.MAX_VALUE);
            Query query = new Query(params);
            List<GrainSpecialPerson> list = grainSpecialPersonService.findGrainSpecialPersonList(query);
            List<String> specialPersonEmployeeNos = new ArrayList<>();
            for(GrainSpecialPerson grainSpecialPerson : list){
                String specialPersonEmployeeNo = grainSpecialPerson.getEmployeeNo();
                specialPersonEmployeeNos.add(specialPersonEmployeeNo);
            }
            if(specialPersonEmployeeNos.contains(employeeNo)) {
                params.clear();
                params.put("employeeNo", employeeNo);
                List<GrainSpecialPerson> articleAwardList = grainSpecialPersonService.findGrainSpecialPersonList(params);
                awardGrainValue = articleAwardList.get(0).getSingleAwardGrainValue();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("awardGrainValue",awardGrainValue);
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            return Response.fail(e.getMessage());
        }
    }


}
