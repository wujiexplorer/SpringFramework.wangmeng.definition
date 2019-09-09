package com.lx.benefits.web.controller.enterprise;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.WelcomeModuleInfoDto;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.admin.customized.PriceDetailResDto;
import com.lx.benefits.bean.dto.enterprise.GoodsInfoResDto;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfo;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.enterprcustomprice.EnterprCustomPriceService;
import com.lx.benefits.service.enterprnoticeinfo.EnterprNoticeInfoService;
import com.lx.benefits.service.enterprwelcome.EnterprWelcomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author unknow on 2018-12-11 03:13.
 */
@Api(tags = "企业商城-企业客户定制信息模块")
@RestController("enterpriseCustomizedController")
@RequestMapping("/enterprise/customized")
public class CustomizedController extends BaseEnterpriseController {
    
    @Autowired
    private EnterprWelcomeService enterprWelcomeService;

    @Autowired
    private EnterprCustomGoodsService enterprCustomGoodsService;

    @Autowired
    private EnterprCustomPriceService enterprCustomPriceService;

    @Autowired
    private EnterprNoticeInfoService enterprNoticeInfoService;

    
    @ApiOperation(value = "企业定制欢迎页详细信息", response = WelcomeModuleInfoDto.class)
    @RequestMapping(value = "/welcomedetail", method = RequestMethod.GET)
    public JSONObject welcomeDetail(@RequestParam(value = "type",required = false) Integer type) {
        Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
        if (null == enterpriseId || enterpriseId < 1) {
            return Response.fail("获取企业信息失败");
        }
        if (type == null) {
            type = 1;
        }
        WelcomeModuleInfoDto dto = enterprWelcomeService.welcomeDetailByCustomId(enterpriseId,type, true);
        return Response.succ(dto);
    }

    @ApiOperation(value = "企业定制热门商品列表信息", response = GoodsInfoResDto.class)
    @RequestMapping(value = "/hotgoodstid", method = RequestMethod.GET)
    public JSONObject hotGoodsList() {
        Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
        if (null == enterpriseId || enterpriseId < 1) {
            return Response.fail("获取企业信息失败");
        }
        GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(enterpriseId);
        return Response.succ(goodsModuleInfoDto);
    }

    @ApiOperation(value = "企业定制特惠商品列表信息", response = GoodsInfoResDto.class)
    @RequestMapping(value = "/preferentialtid", method = RequestMethod.GET)
    public JSONObject preferentialGoodsList() {
        Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
        if (null == enterpriseId || enterpriseId < 1) {
            return Response.fail("获取企业客户信息失败");
        }
        PriceDetailResDto priceDetailResDto = enterprCustomPriceService.selectByEnterprId(enterpriseId);
        return Response.succ(priceDetailResDto);
    }

    @ApiOperation(value = "企业提醒详细信息", response = WelcomeModuleInfoDto.class)
    @RequestMapping(value = "/noticedetail", method = RequestMethod.GET)
    public JSONObject noticedetail() {
        Long enterpriseId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
        if (null == enterpriseId || enterpriseId < 1) {
            return Response.fail("获取企业信息失败");
        }
        EnterprNoticeInfo dto = enterprNoticeInfoService.findById(enterpriseId, true);
        return Response.succ(dto);
    }


}
