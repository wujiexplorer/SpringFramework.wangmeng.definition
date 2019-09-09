package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.operation.AdvertReqDto;
import com.lx.benefits.bean.dto.admin.operation.AdvertResDto;
import com.lx.benefits.bean.entity.operation.AdvertEntity;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.operation.AdvertService;
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

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * 【运营】 广告控制层
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "运营后台-广告模块")
@RestController("advertController")
@RequestMapping("/admin/advert")
public class AdvertController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(AdvertController.class);

    @Autowired
    private AdvertService advertService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject add(@RequestBody AdvertReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }

        String advertName = req.getAdvertName();
        if(StringUtils.isBlank(advertName)){
            return Response.fail("广告名称必填");
        }

        Date beginTime = req.getBeginTime();
        if(null == beginTime){
            return Response.fail("开始时间必填");
        }

        Date endTime = req.getEndTime();
        if(null == endTime){
            return Response.fail("到期时间必填");
        }

        String advertImage = req.getAdvertImage();
        if(StringUtils.isBlank(advertImage)){
            return Response.fail("广告图片必填");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("advertName", advertName.trim());
        List<AdvertEntity> list = advertService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            return Response.fail("广告名称重复");
        }
        AdvertEntity advertEntity = new AdvertEntity();
        BeanUtils.copyProperties(req,advertEntity);
        int insert  = advertService.insert(advertEntity);
        if (insert > 0) {
            return Response.succ("新增成功");
        }
        return Response.fail("新增失败");
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JSONObject modify(@RequestBody AdvertReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("advertName", req.getAdvertName());
        List<AdvertEntity> list = advertService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            AdvertResDto resDto =  Beans.convert(list.get(0),AdvertResDto.class);
            if (!resDto.getId().equals(req.getId())) {
                return Response.fail("广告名称重复");
            }
        }
        AdvertEntity advertEntity = new AdvertEntity();
        BeanUtils.copyProperties(req,advertEntity);
        int update = advertService.update(advertEntity);
        if (update > 0) {
            return Response.succ("编辑成功");
        }
        return Response.fail("编辑失败");
    }


    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public JSONObject del(@PathVariable String id) {
        if (null == id || StringUtils.isEmpty(id)) {
            logger.info("数据不能为空");
            return Response.fail("广告id不能为空");
        }
        int del = advertService.deleteByIds(id);
        if (del > 0) {
            return Response.succ("删除成功");
        }
        return Response.fail("删除失败");
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public JSONObject queryPage(@RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        Query query =  new Query(params);
        Integer count =  advertService.selectCount(query);
        List<AdvertResDto> list = new ArrayList<>();
        if (count > 0) {
            list = advertService.selectPageList(query).stream().map(x -> Beans.convert(x, AdvertResDto.class)).collect(toList());
        }
        jsonObject.put("list", list);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }


}
