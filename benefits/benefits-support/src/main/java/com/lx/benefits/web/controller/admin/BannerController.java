package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.operation.BannerReqDto;
import com.lx.benefits.bean.dto.admin.operation.BannerResDto;
import com.lx.benefits.bean.entity.operation.BannerEntity;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.operation.BannerService;
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
 * 【运营】 banner控制层
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "运营后台-banner模块")
@RestController("bannerController")
@RequestMapping("/admin/banner")
public class BannerController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject addBanner(@RequestBody BannerReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }
        String name = req.getName();
        if(StringUtils.isBlank(name)){
            return Response.fail("banner名称必填");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("name", name.trim());
        List<BannerEntity> list = bannerService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            return Response.fail("banner名称重复");
        }
        BannerEntity bannerEntity = new BannerEntity();
        BeanUtils.copyProperties(req,bannerEntity);
        int insert  = bannerService.insert(bannerEntity);
        if (insert > 0) {
            bannerEntity.setSort(Integer.parseInt(bannerEntity.getId().toString()));
            bannerService.update(bannerEntity);
            return Response.succ("新增成功");
        }
        return Response.fail("新增失败");
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JSONObject modify(@RequestBody BannerReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("name", req.getName());
        List<BannerEntity> list = bannerService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            BannerResDto resDto = Beans.convert(list.get(0),BannerResDto.class);
            if (!resDto.getId().equals(req.getId())) {
                return Response.fail("banner名称重复");
            }
        }
        BannerEntity bannerEntity = new BannerEntity();
        BeanUtils.copyProperties(req,bannerEntity);
        int update = bannerService.update(bannerEntity);
        if (update > 0) {
            return Response.succ("编辑成功");
        }
        return Response.fail("编辑失败");
    }

    @RequestMapping(value = "/modifyStatus", method = RequestMethod.POST)
    public JSONObject modifyStatus(@RequestBody BannerReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }
        BannerEntity bannerEntity = new BannerEntity();
        BeanUtils.copyProperties(req,bannerEntity);
        int update = bannerService.update(bannerEntity);
        if (update > 0) {
            return Response.succ("成功");
        }
        return Response.fail("失败");
    }



    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public JSONObject addTopic(@RequestBody BannerReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }
        BannerEntity bannerEntity = new BannerEntity();
        BeanUtils.copyProperties(req,bannerEntity);
        int update = bannerService.update(bannerEntity);
        if (update > 0) {
            return Response.succ("成功");
        }
        return Response.fail("失败");
    }


    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public JSONObject del(@PathVariable String id) {
        if (null == id || StringUtils.isEmpty(id)) {
            logger.info("数据不能为空");
            return Response.fail("bannerId不能为空");
        }
        int del = bannerService.deleteByIds(id);
        if (del > 0) {
            return Response.succ("删除成功");
        }
        return Response.fail("删除失败");
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public JSONObject queryPage(@RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        Query query =  new Query(params);
        Integer count =  bannerService.selectCount(query);
        List<BannerResDto> list = new ArrayList<>();
        if (count > 0) {
            list = bannerService.selectPageList(query).stream().map(x -> Beans.convert(x, BannerResDto.class)).collect(toList());
        }
        jsonObject.put("list", list);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }


}
