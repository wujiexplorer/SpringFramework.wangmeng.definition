package com.lx.benefits.web.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.product.BrandQueryReqDto;
import com.lx.benefits.bean.dto.admin.product.BrandResDto;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.product.BrandService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import com.lx.benefits.web.util.Query;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * 【商品】 品牌控制层
 * @author gaosong
 * @date 2019/1/28.
 */
@Api(tags = "运营后台-品牌模块")
@RestController("brandController")
@RequestMapping("/admin/brand")
public class BrandController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(BrandController.class);


    @Value("${file.brand.url}")
    private String brandUrl;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject add(@RequestBody BrandQueryReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }
        String logoAddress = req.getLogoAddress();
        if(null == logoAddress || StringUtils.isEmpty(logoAddress) ){
            return Response.fail("必须上传品牌logo图片");
        }
        String name = req.getName();
        if(StringUtils.isBlank(name)){
            return Response.fail("中文名称必填");
        }
        String headAddress = req.getHeadAddress();
        if(null == headAddress || StringUtils.isEmpty(headAddress)){
            return Response.fail("必须上传banner图片");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("name", name.trim());
        List<BrandEntity> list = brandService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            return Response.fail("品牌中文名称重复");
        }
        BrandEntity brand = new BrandEntity();
        BeanUtils.copyProperties(req,brand);
        int insert  = brandService.insertBrand(brand);
        if (insert > 0) {
            return Response.succ("新增成功");
        }
        return Response.fail("新增失败");
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JSONObject modify(@RequestBody BrandQueryReqDto req) {
        if (null == req) {
            logger.info("数据不能为空");
            return Response.fail("数据异常");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("name", req.getName());
        List<BrandEntity> list = brandService.queryByParam(params);
        if(CollectionUtils.isNotEmpty(list)){
            BrandResDto resDto = Beans.convert(list.get(0),BrandResDto.class);
            if (!resDto.getId().equals(req.getId())) {
                return Response.fail("品牌中文名称重复");
            }
        }
        String logoAddress = req.getLogoAddress();
        if(null == logoAddress || StringUtils.isEmpty(logoAddress)){
            return Response.fail("必须上传品牌logo图片");
        }
        String headAddress = req.getHeadAddress();
        if(null == headAddress ||  StringUtils.isEmpty(headAddress)){
            return Response.fail("必须上传banner图片");
        }
        BrandEntity brand = new BrandEntity();
        BeanUtils.copyProperties(req,brand);
        int update = brandService.updateBrand(brand);
        if (update > 0) {
            ProductEntity product = new ProductEntity();
            product.setBrandId(brand.getId());
            product.setBrandName(brand.getName());
            productService.updateProduct(product);
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
        int del = brandService.deleteBrandByIds(id);
        if (del > 0) {
            return Response.succ("删除成功");
        }
        return Response.fail("删除失败");
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public JSONObject queryPage(@RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        Query query =  new Query(params);
        params.put("orderByClause" ,"created_time desc");

        int count =  brandService.selectCount(query);
        List<BrandResDto> list = new ArrayList<>();
        if (count > 0) {
           List<BrandEntity> entityList = brandService.selectPageList(query);
            for (BrandEntity entity:entityList) {
                BrandResDto brand = new BrandResDto();
                BeanUtils.copyProperties(entity,brand);
                if (entity.getLogoAddress()!= null && !StringUtils.isEmpty(entity.getLogoAddress())) {
                    if (entity.getLogoAddress().contains("http://")) {
                        brand.setLogoAddress(entity.getLogoAddress());
                    } else {
                        brand.setLogoAddress(brandUrl+"/"+entity.getLogoAddress());
                    }
                }
                list.add(brand);
            }
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
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object getBrandList(@RequestParam(required = false) String name, PageBean pageBean) {
		PageResultBean<BrandEntity> result = brandService.selectBrandList(name, pageBean);
		return Response.succ(result);
	}
}

