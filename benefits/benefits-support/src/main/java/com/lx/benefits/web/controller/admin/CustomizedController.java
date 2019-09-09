package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.WelcomeModuleInfoDto;
import com.lx.benefits.bean.dto.admin.customized.*;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.enterprcustomprice.EnterprCustomPriceService;
import com.lx.benefits.service.enterprnoticeinfo.EnterprNoticeInfoService;
import com.lx.benefits.service.enterprwelcome.EnterprWelcomeService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author unknow on 2018-12-06 01:55.
 */
@Api(tags = "运营后台-企业客户定制模块")
@RestController("adminCustomizedController")
@RequestMapping("/admin/customized")
public class CustomizedController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(CustomizedController.class);

    @Autowired
    private EnterprWelcomeService enterprWelcomeService;

    @Autowired
    private EnterprCustomGoodsService enterprCustomGoodsService;

    @Autowired
    private EnterprCustomPriceService enterprCustomPriceService;

    @Autowired
    private EnterprNoticeInfoService enterprNoticeInfoService;
    
    @ApiOperation(value = "设置企业定制欢迎页信息", response = Boolean.class)
    @RequestMapping(value = "/setwelcome", method = RequestMethod.POST)
    public JSONObject setWelcome(@ApiParam(value = "Request", name = "req") @RequestBody WelcomeModuleInfoDto req) {
        if (null == req) {
            return Response.fail("定制欢迎页信息不能为空");
        }
        if (null == req.getEnterpriseId() || req.getEnterpriseId() < 1) {
            return Response.fail("企业id不能为空");
        }
        if (null == req.getBannerInfoDtoList() || req.getBannerInfoDtoList().isEmpty()) {
            return Response.fail("Banner信息不能为空");
        }
        if (null == req.getModuleOptionInfoDtoList() || req.getModuleOptionInfoDtoList().isEmpty()) {
            return Response.fail("欢迎页模块设置信息不能为空");
        }
        if (null == req.getContactEmail() || req.getContactEmail().isEmpty()) {
            return Response.fail("企业联系E-Mail信息不能为空");
        }
        if (null == req.getContactPhone() || req.getContactPhone().isEmpty()) {
            return Response.fail("企业联系电话信息不能为空");
        }
        if (null == req.getLogoPath() || req.getLogoPath().isEmpty()) {
            return Response.fail("Logo图片地址信息不能为空");
        }
        if (null == req.getNavBackground() || req.getNavBackground().isEmpty()) {
            return Response.fail("导航栏背景色不能为空");
        }
        if (null == req.getPageBackground() || req.getPageBackground().isEmpty()) {
            return Response.fail("页面背景色不能为空");
        }
        if(null == req.getWxacode()) {
        	return Response.fail("小程序二维码设置不能为空");
        }
        List<BannerInfoDto> bannerInfoDtoList = req.getBannerInfoDtoList();
        for (BannerInfoDto bannerInfoDto: bannerInfoDtoList) {
            if (null == bannerInfoDto) {
                return Response.fail("banner信息不能为空");
            }
//            if (null == bannerInfoDto.getBannerPath() || bannerInfoDto.getBannerPath().isEmpty()) {
//                return Response.fail("banner图片路径不能为空");
//            }
//            if (null == bannerInfoDto.getBannerTitle() || bannerInfoDto.getBannerTitle().isEmpty()) {
//                return Response.fail("banner图片title不能为空");
//            }
        }

        List<ModuleOptionInfoDto> moduleOptionInfoDtoList = req.getModuleOptionInfoDtoList();
        for (ModuleOptionInfoDto moduleOptionInfoDto: moduleOptionInfoDtoList) {
            if (null == moduleOptionInfoDto) {
                return Response.fail("模块信息不能为空");
            }
            if (null == moduleOptionInfoDto.getModuleName() || moduleOptionInfoDto.getModuleName().isEmpty()) {
                return Response.fail("模块名称不能为空");
            }
            if (null == moduleOptionInfoDto.getModulePic() || moduleOptionInfoDto.getModulePic().isEmpty()) {
                return Response.fail("模块图片不能为空");
            }
            if (null == moduleOptionInfoDto.getModuleLink() || moduleOptionInfoDto.getModuleLink().isEmpty()) {
                return Response.fail("模块链接地址不能为空");
            }
//            if (null == moduleOptionInfoDto.getBackground() || moduleOptionInfoDto.getBackground().isEmpty()) {
//                return Response.fail("模块背景色不能为空");
//            }
//            if (null == moduleOptionInfoDto.getSelectedBackground() || moduleOptionInfoDto.getSelectedBackground().isEmpty()) {
//                return Response.fail("模块选中背景色不能为空");
//            }
        }
        try {
            req.setType(1);
            Boolean flag = enterprWelcomeService.setWelcome(req);
            return Response.succ(flag);
        } catch (Exception e) {
            logger.error("欢迎页定制失败,req={} msg={}", req, e);
            return Response.fail("欢迎页定制失败");
        }
    }

    @ApiOperation(value = "设置手机端企业定制欢迎页信息", response = Boolean.class)
    @RequestMapping(value = "/setMobileWelcome", method = RequestMethod.POST)
    public JSONObject setMobileWelcome(@ApiParam(value = "Request", name = "reqDto") @RequestBody WelcomeModuleInfoDto reqDto) {
        if (null == reqDto) {
            return Response.fail("定制欢迎页信息不能为空");
        }
        if (null == reqDto.getEnterpriseId() || reqDto.getEnterpriseId() < 1) {
            return Response.fail("企业id不能为空");
        }
        if (null == reqDto.getBannerInfoDtoList() || reqDto.getBannerInfoDtoList().isEmpty()) {
            return Response.fail("Banner信息不能为空");
        }
        if (null == reqDto.getModuleOptionInfoDtoList() || reqDto.getModuleOptionInfoDtoList().isEmpty()) {
            return Response.fail("欢迎页模块设置信息不能为空");
        }
//        if (null == reqDto.getContactEmail() || reqDto.getContactEmail().isEmpty()) {
//            return Response.fail("企业联系E-Mail信息不能为空");
//        }
//        if (null == reqDto.getContactPhone() || reqDto.getContactPhone().isEmpty()) {
//            return Response.fail("企业联系电话信息不能为空");
//        }
        if (null == reqDto.getLogoPath() || reqDto.getLogoPath().isEmpty()) {
            return Response.fail("Logo图片地址信息不能为空");
        }
        if (null == reqDto.getNavBackground() || reqDto.getNavBackground().isEmpty()) {
            return Response.fail("导航栏背景色不能为空");
        }
        if (null == reqDto.getPageBackground() || reqDto.getPageBackground().isEmpty()) {
            return Response.fail("页面背景色不能为空");
        }
        List<ModuleOptionInfoDto> moduleOptionInfoDtoList = reqDto.getModuleOptionInfoDtoList();
        for (ModuleOptionInfoDto moduleOptionInfoDto: moduleOptionInfoDtoList) {
            if (null == moduleOptionInfoDto) {
                return Response.fail("模块信息不能为空");
            }
            if (null == moduleOptionInfoDto.getModuleName() || moduleOptionInfoDto.getModuleName().isEmpty()) {
                return Response.fail("模块名称不能为空");
            }
            if (null == moduleOptionInfoDto.getModulePic() || moduleOptionInfoDto.getModulePic().isEmpty()) {
                return Response.fail("模块图片不能为空");
            }
            if (null == moduleOptionInfoDto.getModuleLink() || moduleOptionInfoDto.getModuleLink().isEmpty()) {
                return Response.fail("模块链接地址不能为空");
            }
//            if (null == moduleOptionInfoDto.getBackground() || moduleOptionInfoDto.getBackground().isEmpty()) {
//                return Response.fail("模块背景色不能为空");
//            }
//            if (null == moduleOptionInfoDto.getSelectedBackground() || moduleOptionInfoDto.getSelectedBackground().isEmpty()) {
//                return Response.fail("手机定制模块选中背景色不能为空");
//            }
        }
        try {
            reqDto.setType(2);
            Boolean flag = enterprWelcomeService.setWelcome(reqDto);
            return Response.succ(flag);
        } catch (Exception e) {
            logger.error("手机欢迎页定制失败,req={} msg={}", reqDto, e);
            return Response.fail("手机欢迎页定制失败");
        }
    }

    @ApiOperation(value = "企业定制欢迎页详细信息", response = WelcomeModuleInfoDto.class)
    @RequestMapping(value = "/welcomedetail/{enterprId}", method = RequestMethod.GET)
    public JSONObject welcomeDetail( @PathVariable String enterprId,@RequestParam(value = "type",required = false) Integer type) {
        if(!StringUtils.isNumeric(enterprId)){
            return Response.fail("请求无效，无企业id，请重新登录");
        }
        // 1:pc  2:手机端
        if ( null == type) {
            type = 1;
        }
        Long id = Long.parseLong(enterprId);
        if (null == id || id < 1) {
            return Response.fail("企业id不能为空");
        }
        WelcomeModuleInfoDto  welcomeModuleInfoDto = enterprWelcomeService.welcomeDetailByCustomId(id,type, false);
        return Response.succ(welcomeModuleInfoDto);
    }

    /**
     * 定制商品
     * @param req
     * @return
     */
    @ApiOperation(value = "设置企业定制商品信息", response = Boolean.class)
    @RequestMapping(value = "/setgoods", method = RequestMethod.POST)
    public JSONObject setGoods(@ApiParam(value = "Request", name = "req") @RequestBody GoodsModuleInfoDto req) {
        if (null == req) {
            return Response.fail("定制商品信息不能为空");
        }
        if (null == req.getEnterprId() || req.getEnterprId() < 1) {
            return Response.fail("企业id不能为空");
        }
        Long customId = enterprCustomGoodsService.insertGoods(req);
        if(customId > 0) {
            return Response.succ(true);
        }
        return Response.fail("设置企业定制商品信息失败");
    }

    @ApiOperation(value = "企业定制商品详细信息", response = GoodsModuleInfoDto.class)
    @RequestMapping(value = "/goodsdetail/{enterprId}", method = RequestMethod.GET)
    public JSONObject goodsDetail(@ApiParam(value = "企业客户id", name = "enterprId") @PathVariable Long enterprId) {
        
        if (null == enterprId || enterprId < 1) {
            return Response.fail("企业id不能为空");
        }
        GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findById(enterprId);
        return Response.succ(goodsModuleInfoDto);
    }

    @ApiOperation(value = "设置企业定制价格信息", response = Boolean.class)
    @RequestMapping(value = "/setprice", method = RequestMethod.POST)
    public JSONObject setPrice(@ApiParam(value = "Request", name = "req") @RequestBody PriceModuleInfoDto req) {
        if (null == req) {
            return Response.fail("定制价格信息不能为空");
        }
        if (null == req.getCustomId() || req.getCustomId() < 1) {
            return Response.fail("id不能为空");
        }
        return Response.succ(enterprCustomPriceService.insertSelective(req));
    }

    @ApiOperation(value = " 批量导入企业定制价格信息", response = Boolean.class)
    @RequestMapping(value = "/batchImport", method = RequestMethod.POST)
    public JSONObject batchImport(@ApiParam(value = "Request", name = "req") @RequestBody PriceModuleInfoDto req) {
        if (null == req) {
            return Response.fail("定制价格信息不能为空");
        }
        if (null == req.getEnterprId() || req.getEnterprId() < 1) {
            return Response.fail("企业id不能为空");
        }
        if (null == req.getFilePath() || StringUtils.isEmpty(req.getFilePath())) {
            return Response.fail("企业定制价格文件不能为空");
        }

        return Response.succ(enterprCustomPriceService.batchImport(req));
    }

    @ApiOperation(value = "企业定制价格详细信息", response = PriceDetailResDto.class)
    @RequestMapping(value = "/pricedetail/{customId}", method = RequestMethod.GET)
    public JSONObject priceDetail(@ApiParam(value = "定制价格模块id", name = "customId") @PathVariable Long customId) {
        if (null == customId || customId < 1) {
            return Response.fail("定制价格信息id不能为空");
        }
        PriceDetailResDto priceDetailResDto = enterprCustomPriceService.selectByPrimaryKey(customId);
        return Response.succ(priceDetailResDto);
    }

    @ApiOperation(value = "根据企业ID获取企业定制价格详细信息", response = PriceDetailResDto.class)
    @RequestMapping(value = "/enterprpricedetail/{enterprId}", method = RequestMethod.GET)
    public JSONObject enterprPriceDetail(@ApiParam(value = "企业客户id", name = "enterprId") @PathVariable Long enterprId) {
        if (null == enterprId || enterprId < 1) {
            return Response.fail("定制价格信息id不能为空");
        }
        PriceDetailResDto priceDetailResDto = enterprCustomPriceService.selectByEnterprId(enterprId);
        return Response.succ(priceDetailResDto);
    }

    @ApiOperation(value = "企业定制价格列表详细信息", response = PriceDetailResDto.class)
    @RequestMapping(value = "/pricelist", method = RequestMethod.GET)
    public JSONObject priceList(@ApiParam(value = "Request", name = "req") @ModelAttribute PriceQueryReqDto req) {
        req = null == req ? new PriceQueryReqDto() : req;
        return enterprCustomPriceService.selectByExample(req);
    }

    @ApiOperation(value = "企业定制价格信息删除", response = Boolean.class)
    @RequestMapping(value = "/pricedelete/{customId}", method = RequestMethod.GET)
    public JSONObject priceDelete(@ApiParam(value = "定制价格模块id", name = "customId") @PathVariable Long customId) {
        
        if (null == customId || customId < 1) {
            return Response.fail("定制价格信息id不能为空");
        }
        Boolean isSuccess = enterprCustomPriceService.delEnterprCustomPrice(customId);
        if (isSuccess) {
            return Response.succ(true);
        }
        return Response.fail("企业定制价格信息删除失败");
    }


    @ApiOperation(value = "设置企业提醒信息", response = Boolean.class)
    @RequestMapping(value = "/setNotice", method = RequestMethod.POST)
    public JSONObject setNotice(@ApiParam(value = "Request", name = "req") @RequestBody EnterprNoticeReqDto req) {

        if (null == req) {
            return Response.fail("定制提醒信息不能为空");
        }

        if (null == req.getIsLoginRemind()) {
            return Response.fail("登录设置不能为空");
        }
        if (null == req.getLoginAttach() || req.getLoginAttach().isEmpty()) {
            return Response.fail("登录提醒不能为空");
        }
        if (null == req.getIsUserRemind()) {
            return Response.fail("用户设置不能为空");
        }
        if (null == req.getUserAttach() || req.getUserAttach().isEmpty()) {
            return Response.fail("用户中心提醒不能为空");
        }

        try {

            Boolean flag = enterprNoticeInfoService.setNotice(req);
            return Response.succ(flag);
        } catch (Exception e) {
            logger.error("企业提醒定制失败,req={} msg={}", req, e);
            return Response.fail("企业提醒定制失败");
        }
    }

}