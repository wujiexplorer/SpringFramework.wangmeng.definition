package com.lx.benefits.web.controller.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.entity.operation.BannerEntity;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.service.operation.BannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author unknow on 2018-12-10 23:48.
 */
@Api(tags = "企业商城-banner模块")
@RestController("bannerController")
@RequestMapping("/enterprise/banner")
public class BannerController extends BaseEnterpriseController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/queryBanners", method = RequestMethod.GET)
    public JSONObject queryBanners(@RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        params.put("status",1);
        List<BannerEntity>  list =   bannerService.queryByParam(params);
        jsonObject.put("list", list);
        return Response.succ(jsonObject);
    }

    @RequestMapping(value = "/queryBannerById/{id}", method = RequestMethod.GET)
    public JSONObject queryBanners(@PathVariable Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (null == id || id < 1) {
            return Response.fail("banner id不能为空");
        }
        BannerEntity bannerEntity = bannerService.selectById(id);
        jsonObject.put("info", bannerEntity);
        return Response.succ(jsonObject);
    }

}
