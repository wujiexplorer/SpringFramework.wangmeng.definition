package com.lx.benefits.web.controller.enterprise.grain;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.grain.GrainArticleAward;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.grain.GrainArticleAwardService;
import io.swagger.annotations.Api;
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
@Api(tags = "企业商城-颗粒文章奖励信息模块")
@RestController
@RequestMapping("/enterprise/grain")
public class GrainArticleAwardController {

    private static final Logger log = LoggerFactory.getLogger(GrainArticleAwardController.class);

    @Autowired
    private GrainArticleAwardService grainArticleAwardService;

    /**
     * 删除颗粒文章奖励信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteGrainArticleAward", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject deleteGrainArticleAward(Long id) {
        try {
            Integer count = grainArticleAwardService.delete(id);
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
     * 新增颗粒文章奖励信息
     * @param grainArticleAward
     * @return
     */
    @RequestMapping(value = "/insertGrainArticleAward", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertGrainArticleAward(@RequestBody GrainArticleAward grainArticleAward) {
        try {
            GrainArticleAward grainArticleAwardTemp = grainArticleAwardService.insert(grainArticleAward);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainArticleAward",grainArticleAwardTemp);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

    /**
     * 修改颗粒文章奖励信息
     * @param grainArticleAward
     * @return
     */
    @RequestMapping(value = "/updateGrainArticleAward", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateGrainArticleAward(@RequestBody GrainArticleAward grainArticleAward) {
        try {
            Integer count = grainArticleAwardService.updateByPrimaryKeySelective(grainArticleAward);
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
     * 查询颗粒文章奖励信息列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/findGrainArticleAwards", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findGrainArticleAwards(@RequestBody Map<String,Object> params) {
        try {
            List<GrainArticleAward> list = grainArticleAwardService.findGrainArticleAwardList(params);
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
     * 根据ID查询颗粒文章奖励信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGrainArticleAward", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getGrainArticleAward(Long id) {
        try {
            GrainArticleAward grainArticleAward = grainArticleAwardService.selectByPrimaryKey(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grainArticleAward",grainArticleAward);
            log.info("[API接口 - 获取用信息 返回结果] = {}", JsonUtil.convertObjToStr(jsonObject));
            return Response.succ(jsonObject);
        } catch (Exception e) {
            log.error("[API接口 - 获取用信息  Exception] = {}", e.getMessage());
            log.error("[API接口 - 获取用信息 返回值] = {}", JsonUtil.convertObjToStr(e));
            return Response.fail(e.getMessage());
        }
    }

}
