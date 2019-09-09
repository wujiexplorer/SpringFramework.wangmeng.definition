package com.lx.benefits.web.controller.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.enterprise.GoodsInfoResDto;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.service.sale.AdvanceSaleItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author unknow on 2018-12-09 23:15.
 */
@Api(tags = "企业商城-预售模块")
@RestController
@RequestMapping("/enterprise/advance")
public class AdvanceController extends BaseEnterpriseController {

    private final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Resource
    private AdvanceSaleItemService advanceSaleItemService;

    @ApiOperation(value = "预售商品提交", response = GoodsInfoResDto.class)
    @RequestMapping(value = "/submitAdvance", method = RequestMethod.POST)
    public JSONObject submitAdvance(@RequestBody Map<String, Object> params) {
        if (null == params || params.size() < 1) {
            return Response.fail("参数不能为空");
        }
        try {
            return advanceSaleItemService.submitAdvance(params);
        } catch (Exception e) {
            log.error("预售提交异常{}",e.getMessage());
            return Response.fail("预售商品失败");
        }
    }


}