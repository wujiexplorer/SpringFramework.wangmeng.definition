package com.lx.benefits.web.controller.admin.grain;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.grain.GrainOptInfo;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Query;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.grain.GrainOptInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/29
 * Time:17:06
 * Verision:2.x
 * Description:TODO
 **/
@Api(tags = "运营后台-管理员颗粒号操作信息模块")
@RestController
@RequestMapping("/admin/grain")
public class GrainOptInfoAdminController {

    private static final Logger log = LoggerFactory.getLogger(GrainOptInfoAdminController.class);

    @Autowired
    private GrainOptInfoService grainOptInfoService;

    /**
     * 查询颗粒号操作信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainOptInfos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainOptInfos(@RequestBody Map<String,Object> params) {
        try {
            Query query = new Query(params);
            List<GrainOptInfo> list = grainOptInfoService.findGrainOptInfoList(query);
            Integer count = grainOptInfoService.countGrainOptInfoList(params);
            Integer totalReceiveCreditValue = grainOptInfoService.countReceiveCreditValue(params);
            JSONObject jsonObject = new JSONObject();
            PageResultBean<GrainOptInfo> pageResultBean = new PageResultBean<>();
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
            jsonObject.put("receiveCreditValue",totalReceiveCreditValue);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

}
