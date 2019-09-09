package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.category.CategoryReqDto;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.product.CategoryService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【商品】 商品分类控制层
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "运营后台-商品分类模块")
@RestController("categoryController")
@RequestMapping("/admin/category")
public class CategoryController  extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject add(@RequestBody CategoryReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }

        String name = req.getName();
        if(StringUtils.isBlank(name)){
            return Response.fail("分类名称必填");
        }

        CategoryEntity category = new CategoryEntity();
        BeanUtils.copyProperties(req,category);
        int insert  = categoryService.insert(category);
        if (insert > 0) {
            return Response.succ("新增成功");
        }
        return Response.fail("新增失败");
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JSONObject modify(@RequestBody CategoryReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }
        CategoryEntity category = new CategoryEntity();
        BeanUtils.copyProperties(req,category);
        int update = categoryService.update(category);
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
        Map<String,Object> map = new HashMap<>();
        map.put("parentId",id);
        List<CategoryEntity> list = categoryService.queryByParam(map);
        if (null != list && list.size() > 0) {
            return Response.fail("该分类有子级，请先删除子级目录");
        }
        int del = categoryService.deleteByIds(id);
        if (del > 0) {
            return Response.succ("删除成功");
        }
        return Response.fail("删除失败");
    }

}
