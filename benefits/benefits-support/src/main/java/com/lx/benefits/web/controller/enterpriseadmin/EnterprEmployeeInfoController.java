package com.lx.benefits.web.controller.enterpriseadmin;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.employeeuserinfo.EmployeeUserInfo;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/23
 * Time:16:08
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "企业平台-绑定颗粒号企业信息模块")
@RestController
@RequestMapping("/enterpriseadmin/employee")
public class EnterprEmployeeInfoController {

    private static final Logger log = LoggerFactory.getLogger(EnterprEmployeeInfoController.class);

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    /**
     * 根据企业ID查询员工信息
     * @param
     * @return
     */
    @RequestMapping(value = "/findEnterpriseEmployeeInfosByEnterprId", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject findEnterpriseEmployeeInfosByEnterprId() {
        try {
            Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
            if(null == enterprId){
                return Response.fail("企业ID不能为空！");
            }
            List<EmployeeUserInfo> list = employeeUserInfoService.findEmployeeUserInfosByEnterprId(enterprId);
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
     * 根据企业员工名称查询员工信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/findEnterpriseEmployeeInfosByEmployeeName", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject findEnterpriseEmployeeInfosByEmployeeName(@RequestBody Map<String,Object> params) {
        try {
            Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
            if(null == enterprId){
                Response.fail("企业ID不能为空！" );
            }
            params.put("enterprId",enterprId);
            List<EmployeeUserInfo> list = employeeUserInfoService.findEmployeeUserInfosByName(params);
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
     * 根据企业员工名称查询员工信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/validateEnterpriseEmployeeInfos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject validateEnterpriseEmployeeInfos(@RequestBody Map<String,Object> params) {
        try {
            Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
            if(null == enterprId){
                Response.fail("企业ID不能为空！" );
            }
            String employeeNo = params.get("employeeNo").toString();
            String employeeName = params.get("employeeName").toString();
            List<EmployeeUserInfo> list = employeeUserInfoService.findEmployeeUserInfosByEnterprId(enterprId);
            List<String> listStr = new ArrayList<>();
            for(EmployeeUserInfo employeeUserInfo : list){
                listStr.add(employeeUserInfo.getEmployeeNo());
            }
            if(! listStr.contains(employeeNo)){
                throw new BusinessException("企业不存在该员工工号，请重新输入！");
            }
            Map<String,Object> paramsTemp = new HashMap<>();
            paramsTemp.put("enterprId",enterprId);
            paramsTemp.put("employeeNo",params.get("employeeNo").toString());
            List<EmployeeUserInfo> employNamelist = employeeUserInfoService.findEmployeeUserInfosByName(paramsTemp);
            if(!employeeName.equals(employNamelist.get(0).getEmployeeName())){
                throw new BusinessException("该企业员工姓名与工号不匹配，请重新输入！");
            }
            return Response.succ();
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }
}
