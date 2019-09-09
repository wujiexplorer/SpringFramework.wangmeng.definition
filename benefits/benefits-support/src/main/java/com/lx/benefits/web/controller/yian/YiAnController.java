package com.lx.benefits.web.controller.yian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.campaign.ImportCampainReqDto;
import com.lx.benefits.bean.dto.admin.product.ProductResDto;
import com.lx.benefits.bean.dto.yianapi.YianImportOrderDto;
import com.lx.benefits.bean.entity.ent.YianOrderInfo;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.util.BeansUtils;
import com.lx.benefits.bean.util.ProductUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.yianapi.YianOrderInfoService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import com.lx.benefits.web.util.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by softw on 2019/3/13.
 */
@Api(tags = "运营后台-管理员用户模块")
@RestController("yiAnController")
@RequestMapping("/admin/yiAnOrder")
public class YiAnController  extends BaseAdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private YianOrderInfoService yianOrderInfoService;

    @ApiOperation(value = "导入怡安订单", response = Boolean.class)
    @RequestMapping(value = "/importOrder", method = RequestMethod.POST)
    public JSONObject importOrder(@ApiParam(value = "Request", name = "req") @RequestBody YianImportOrderDto req) {
        try {
            return yianOrderInfoService.importOrder(req);
        } catch (Exception e) {
            logger.error("导入订单异常{}",e.getMessage());
            return Response.fail("导入订单员异常!");
        }
    }

    @ApiOperation(value = "导入怡安发货订单", response = Boolean.class)
    @RequestMapping(value = "/importOrderDelivery", method = RequestMethod.POST)
    public JSONObject importOrderDelivery(@ApiParam(value = "Request", name = "req") @RequestBody YianImportOrderDto req) {
        try {
            return yianOrderInfoService.importOrderDelivery(req);
        } catch (Exception e) {
            logger.error("导入发货单异常{}",e.getMessage());
            return Response.fail("导入发货单异常!");
        }
    }



    /**
     * 怡安订单列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public JSONObject queryPage(@RequestBody Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        Query query = new Query(params);
        Integer count = yianOrderInfoService.selectCount(query);
        List<YianOrderInfo> list = new ArrayList<>();
        if (count > 0) {
            list = yianOrderInfoService.selectPageList(query);
        }
        jsonObject.put("list", list);
        jsonObject.put("count", count);
        return Response.succ(jsonObject);
    }
}
