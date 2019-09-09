package com.lx.benefits.web.controller.enterprise.grain;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.grain.GrainSpecialPerson;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.grain.GrainSpecialPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:15:33
 * Verision:2.x
 * Description:TODO
 **/
@RestController
@RequestMapping("/enterprise/grain")
public class GrainSpecialPersonController {

    private static final Logger log = LoggerFactory.getLogger(GrainSpecialPersonController.class);

    @Autowired
    private GrainSpecialPersonService grainSpecialPersonService;

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
            GrainSpecialPerson grainSpecialPersonTemp = grainSpecialPersonService.insert(grainSpecialPerson);
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
            List<GrainSpecialPerson> list = grainSpecialPersonService.findGrainSpecialPersonList(params);
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
