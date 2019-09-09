package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.operation.ActivityReqDto;
import com.lx.benefits.bean.dto.admin.operation.ActivityResDto;
import com.lx.benefits.bean.entity.operation.ActivityEntity;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.operation.ActivityService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * 【运营】 活动位控制层
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "运营后台-活动位模块")
@RestController("acitivityController")
@RequestMapping("/admin/activity")
public class AcitivityController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(AcitivityController.class);

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject add(@RequestBody ActivityReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }
        String name = req.getName();
        if(StringUtils.isBlank(name)){
            return Response.fail("活动位名称必填");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("name", name.trim());
        List<ActivityEntity> list = activityService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            return Response.fail("活动位名称重复");
        }
        ActivityEntity activityEntity = new ActivityEntity();
        BeanUtils.copyProperties(req,activityEntity);
        int insert  = activityService.insert(activityEntity);
        if (insert > 0) {
            return Response.succ("新增成功");
        }
        return Response.fail("新增失败");
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JSONObject modify(@RequestBody ActivityReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("name", req.getName());
        List<ActivityEntity> list = activityService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            ActivityResDto resDto =  Beans.convert(list.get(0),ActivityResDto.class);
            if (!resDto.getId().equals(req.getId())) {
                return Response.fail("活动位名称重复");
            }
        }
        ActivityEntity activityEntity = new ActivityEntity();
        BeanUtils.copyProperties(req,activityEntity);
        int update = activityService.update(activityEntity);
        if (update > 0) {
            return Response.succ("编辑成功");
        }
        return Response.fail("编辑失败");
    }


    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public JSONObject del(@PathVariable String id) {
        if (null == id || StringUtils.isEmpty(id)) {
            logger.info("数据不能为空");
            return Response.fail("活动位id不能为空");
        }
        int del = activityService.deleteByIds(id);
        if (del > 0) {
            return Response.succ("删除成功");
        }
        return Response.fail("删除失败");
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public JSONObject queryPage(@RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        Query query =  new Query(params);
        Integer count =  activityService.selectCount(query);
        List<ActivityResDto> list = new ArrayList<>();
        if (count > 0) {
            list = activityService.selectPageList(query).stream().map(x -> Beans.convert(x, ActivityResDto.class)).collect(toList());
        }
        jsonObject.put("list", list);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }
}
