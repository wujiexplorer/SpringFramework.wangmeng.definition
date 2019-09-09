package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.label.LabelReqDto;
import com.lx.benefits.bean.dto.admin.label.LabelResDto;
import com.lx.benefits.bean.entity.product.LabelEntity;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.product.LabelService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * 【商品】 商品标签控制层
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "运营后台-商品标签模块")
@RestController("labelController")
@RequestMapping("/admin/label")
public class LabelController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(LabelController.class);

    @Autowired
    private LabelService labelService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject add(@RequestBody LabelReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }

        String name = req.getName();
        if(StringUtils.isBlank(name)){
            return Response.fail("标签名称必填");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("name", name.trim());
        List<LabelEntity> list = labelService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            return Response.fail("标签名称重复");
        }
        LabelEntity labelEntity = new LabelEntity();
        BeanUtils.copyProperties(req,labelEntity);
        int insert  = labelService.insert(labelEntity);
        if (insert > 0) {
            return Response.succ("新增成功");
        }
        return Response.fail("新增失败");
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JSONObject modify(@RequestBody LabelReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("name", req.getName());
        List<LabelEntity> list = labelService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            LabelResDto resDto = Beans.convert(list.get(0),LabelResDto.class);
            if (!resDto.getId().equals(req.getId())) {
                return Response.fail("标签名称重复");
            }
        }
        LabelEntity labelEntity = new LabelEntity();
        BeanUtils.copyProperties(req,labelEntity);
        int update = labelService.update(labelEntity);
        if (update > 0) {
            return Response.succ("编辑成功");
        }
        return Response.fail("编辑失败");
    }


    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public JSONObject del(@PathVariable String id) {
        if (null == id || StringUtils.isEmpty(id)) {
            logger.info("数据不能为空");
            return Response.fail("品牌id不能为空");
        }
        int del = labelService.deleteByIds(id);
        if (del > 0) {
            return Response.succ("删除成功");
        }
        return Response.fail("删除失败");
    }

    @RequestMapping(value = "/queryLabels", method = RequestMethod.GET)
    public JSONObject queryLabels() {
        JSONObject jsonObject = new JSONObject();
        List<LabelResDto> list = labelService.queryByParam(null).stream().map(x -> Beans.convert(x, LabelResDto.class)).collect(toList());
        jsonObject.put("list", list);
        return Response.succ(jsonObject);
    }

}
