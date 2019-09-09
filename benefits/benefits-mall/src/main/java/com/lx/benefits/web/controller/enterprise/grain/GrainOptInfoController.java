package com.lx.benefits.web.controller.enterprise.grain;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.grain.GrainOptInfo;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.grain.GrainOptInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:15:32
 * Verision:2.x
 * Description:TODO
 **/
@RestController
@RequestMapping("/enterprise/grain")
public class GrainOptInfoController {

    private static final Logger log = LoggerFactory.getLogger(GrainOptInfoController.class);

    @Autowired
    private GrainOptInfoService grainOptInfoService;

    /**
     * 删除颗粒号操作信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteGrainOptInfo", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject deleteGrainOptInfo(Long id) {
        try {
            Integer count = grainOptInfoService.delete(id);
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
     * 新增颗粒号操作信息
     * @param grainOptInfo
     * @return
     */
    @RequestMapping(value = "/insertGrainOptInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertGrainOptInfo(@RequestBody GrainOptInfo grainOptInfo) {
        try {
            GrainOptInfo grainOptInfoTemp = grainOptInfoService.insert(grainOptInfo);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainOptInfoTemp",grainOptInfoTemp);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 修改颗粒号操作信息
     * @param grainOptInfo
     * @return
     */
    @RequestMapping(value = "/updateGrainOptInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateGrainOptInfo(@RequestBody GrainOptInfo grainOptInfo) {
        try {
            Integer count = grainOptInfoService.updateByPrimaryKeySelective(grainOptInfo);
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
     * 查询颗粒号操作信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainOptInfos", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainOptInfos(@RequestBody Map<String,Object> params) {
        try {
            List<GrainOptInfo> list = grainOptInfoService.findGrainOptInfoList(params);
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
     * 根据ID查询颗粒号操作信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGrainOptInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGrainOptInfo(Long id) {
        try {
            GrainOptInfo grainOptInfo = grainOptInfoService.selectByPrimaryKey(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainOptInfo",grainOptInfo);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

}
