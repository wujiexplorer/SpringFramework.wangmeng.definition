package com.lx.benefits.web.controller.enterpriseadmin.grain;

import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.constants.grain.GrainArticleInfoConstant;
import com.lx.benefits.bean.constants.grain.GrainSpecialPersonConstant;
import com.lx.benefits.bean.dto.grain.GrainArticleInfo;
import com.lx.benefits.bean.dto.grain.GrainSpecialPerson;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Query;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.enterprise.EnterpriseService;
import com.lx.benefits.service.grain.GrainArticleInfoService;
import com.lx.benefits.service.grain.GrainSpecialPersonService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:15:33
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "企业平台-颗粒奖励特殊员工模块")
@RestController
@RequestMapping("/enterpriseadmin/grain")
public class GrainSpecialPersonController {

    private static final Logger log = LoggerFactory.getLogger(GrainSpecialPersonController.class);

    @Autowired
    private GrainSpecialPersonService grainSpecialPersonService;

    @Autowired
    private GrainArticleInfoService grainArticleInfoService;

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 删除颗粒奖励特殊员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteGrainSpecialPerson", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject deleteGrainSpecialPerson(Long id) {
        try {
            Integer count = grainSpecialPersonService.delete(id);
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
     * 新增颗粒奖励特殊员工信息
     * @param grainSpecialPerson
     * @return
     */
    @RequestMapping(value = "/insertGrainSpecialPerson", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertSpecialPerson(@RequestBody GrainSpecialPerson grainSpecialPerson) {
        try {
            Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
            if(null == enterprId){
                throw new BusinessException("企业ID不能为空！");
            }
            GrainSpecialPerson grainSpecialPersonRes = new GrainSpecialPerson();
            BeanUtils.copyProperties(grainSpecialPerson,grainSpecialPersonRes);
            grainSpecialPersonRes.setCreated((int)(System.currentTimeMillis()/1000));
            grainSpecialPersonRes.setUpdated((int)(System.currentTimeMillis()/1000));
            grainSpecialPersonRes.setEnterprId(enterprId);
            grainSpecialPersonRes.setEmployeeId(GrainSpecialPersonConstant.EMPLOYEE_ID_DEFAULT_VALUE);
            GrainSpecialPerson grainSpecialPersonTemp = grainSpecialPersonService.insert(grainSpecialPersonRes);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainSpecialPersonTemp",grainSpecialPersonTemp);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 修改颗粒奖励特殊员工信息
     * @param grainSpecialPerson
     * @return
     */
    @RequestMapping(value = "/updateGrainSpecialPerson", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateGrainSpecialPerson(@RequestBody GrainSpecialPerson grainSpecialPerson) {
        try {
            Integer count = grainSpecialPersonService.updateByPrimaryKeySelective(grainSpecialPerson);
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
     * 查询颗粒奖励特殊员工信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainSpecialPersons", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainSpecialPersons(@RequestBody Map<String,Object> params) {
        try {
            String loginName = SessionContextHolder.getSessionEnterpriseInfo().getLoginName();
            EnterprUserInfo enterprUserInfo = enterpriseService.getEnterprUserInfo(loginName);
            Long enterprId = SessionContextHolder.getSessionEnterpriseInfo().getEnterprId();
            params.put("enterprId",enterprId);
            Query query = new Query(params);
            List<GrainSpecialPerson> list = grainSpecialPersonService.findGrainSpecialPersonList(query);
            params.clear();
            params.put("enterprId",enterprId);
            Integer count = grainSpecialPersonService.countGrainSpecialPersonList(params);
            Map<String,Object> paramsTemp = new HashMap<>();
            paramsTemp.put("page",1);
            paramsTemp.put("pageSize", Integer.MAX_VALUE);
            paramsTemp.put("grainId",enterprUserInfo.getGrainId());
            Query queryTemp = new Query(paramsTemp);
            List<GrainArticleInfo> grainArticleInfoList = grainArticleInfoService.findGrainArticleInfoList(queryTemp);
            Integer articleReadTime = null;
            if(grainArticleInfoList.isEmpty()){
                articleReadTime = GrainArticleInfoConstant.ARTICLE_READ_TIME_INIT_VALUE;
            }else{
                Integer size = grainArticleInfoList.size();
                articleReadTime = grainArticleInfoList.get(size-1).getArticleReadTime();
            }

            JSONObject jsonObject = new JSONObject();
            PageResultBean<GrainSpecialPerson> pageResultBean = new PageResultBean<>();
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
            jsonObject.put("articleReadTime",articleReadTime);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 根据ID查询颗粒奖励特殊员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGrainSpecialPerson", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGrainSpecialPerson(Long id) {
        try {
            GrainSpecialPerson grainSpecialPerson = grainSpecialPersonService.selectByPrimaryKey(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainSpecialPerson",grainSpecialPerson);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }
}
