package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.operation.ActivityThemeReqDto;
import com.lx.benefits.bean.dto.admin.operation.ActivityThemeResDto;
import com.lx.benefits.bean.entity.operation.ActivityThemeEntity;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.operation.ActivityThemeService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import com.lx.benefits.web.util.Query;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【运营】 专栏控制层
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "运营后台-专栏模块")
@RestController("acitivityThemeController")
@RequestMapping("/admin/activityTheme")
public class AcitivityThemeController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(AcitivityThemeController.class);

    @Autowired
    private ActivityThemeService activityThemeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject add(@RequestBody ActivityThemeReqDto req) {
        String themeName = req.getThemeName();
        if(StringUtils.isBlank(themeName)){
            return Response.fail("专题活动名称必填");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("themeName", themeName.trim());
        List<ActivityThemeEntity> list = activityThemeService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            return Response.fail("专题活动名称重复");
        }
        ActivityThemeEntity activityEntity = new ActivityThemeEntity();
        BeanUtils.copyProperties(req,activityEntity);
        int insert  = activityThemeService.insert(activityEntity);
        if (insert > 0) {
            return Response.succ("新增成功");
        }
        return Response.fail("新增失败");
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JSONObject modify(@RequestBody ActivityThemeReqDto req) {
        Map<String,Object> params = new HashMap<>();
        params.put("themeName", req.getThemeName());
        List<ActivityThemeEntity> list = activityThemeService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            ActivityThemeResDto resDto =  Beans.convert(list.get(0),ActivityThemeResDto.class);
            if (!resDto.getId().equals(req.getId())) {
                return Response.fail("专题活动名称重复");
            }
        }
        ActivityThemeEntity entity = new ActivityThemeEntity();
        BeanUtils.copyProperties(req,entity);
        int update = activityThemeService.update(entity);
        if (update > 0) {
            return Response.succ("编辑成功");
        }
        return Response.fail("编辑失败");
    }

    @RequestMapping(value = "/modifyStatus", method = RequestMethod.POST)
    public JSONObject modifyStatus(@RequestBody ActivityThemeReqDto req) {
		Integer id = req.getId();
		Integer status = req.getStatus();
		if (id == null || status == null) {
			Response.fail("参数错误！");
		}
		ActivityThemeEntity entity = new ActivityThemeEntity();
		entity.setId(req.getId());
		entity.setStatus(req.getStatus());
		int update = activityThemeService.update(entity);
		if (update > 0) {
			return Response.succ("更新成功");
		}
		return Response.fail("更新失败");
    }

    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public JSONObject addTopic(@RequestBody ActivityThemeReqDto req) {
        ActivityThemeEntity entity = new ActivityThemeEntity();
        BeanUtils.copyProperties(req,entity);
        int update = activityThemeService.update(entity);
        if (update > 0) {
            return Response.succ("成功");
        }
        return Response.fail("失败");
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public JSONObject del(@PathVariable String id) {
        if (null == id || StringUtils.isEmpty(id)) {
            logger.info("数据不能为空");
            return Response.fail("活动位id不能为空");
        }
        int del = activityThemeService.deleteByIds(id);
        if (del > 0) {
            return Response.succ("删除成功");
        }
        return Response.fail("删除失败");
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(@RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        Query query =  new Query(params);
        Integer count =  activityThemeService.selectCount(query);
        if (count > 0) {
        	jsonObject.put("list",  activityThemeService.selectPageList(query));	
        }else {
        	 jsonObject.put("list", Collections.emptyList());	
        }
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }
}
