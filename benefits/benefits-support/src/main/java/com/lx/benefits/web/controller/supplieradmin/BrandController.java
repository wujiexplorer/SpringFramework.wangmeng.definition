package com.lx.benefits.web.controller.supplieradmin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.product.BrandResDto;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.product.BrandService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import com.lx.benefits.web.util.Query;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * 【商品】 品牌控制层
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "运营后台-品牌模块")
@RestController("supplieradminBrandController")
@RequestMapping("/supplieradmin/brand")
public class BrandController extends BaseAdminController {

    @Autowired
    private BrandService brandService;


    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public JSONObject queryPage(@RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        Query query =  new Query(params);
        params.put("orderByClause" ,"created_time");

        int count =  brandService.selectCount(query);
        List<BrandResDto> list = new ArrayList<>();
        if (count > 0) {
            list = brandService.selectPageList(query).stream().map(x -> Beans.convert(x, BrandResDto.class)).collect(toList());
        }
        jsonObject.put("list", list);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }

    @RequestMapping(value = "/queryBrands", method = RequestMethod.GET)
    public JSONObject queryBrands(@RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        List<BrandResDto> list = brandService.selectPageList(params).stream().map(x -> Beans.convert(x, BrandResDto.class)).collect(toList());
        jsonObject.put("list", list);
        return Response.succ(jsonObject);
    }
}

