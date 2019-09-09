package com.lx.benefits.web.controller.common;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.admin.product.DictionaryReqDto;
import com.lx.benefits.bean.dto.admin.product.DictionaryResDto;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfo;
import com.lx.benefits.bean.entity.basedistrictinfo.DistrictInfoExample;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionSupplierInfo;
import com.lx.benefits.mapper.express.entity.ExpressInfo;
import com.lx.benefits.service.basedistrictinfo.DistricitInfoService;
import com.lx.benefits.service.express.ExpressService;
import com.lx.benefits.service.product.DictionaryService;
import com.lx.benefits.service.product.ProductSpecService;
import com.lx.benefits.web.controller.base.BaseAdminController;

/**
 * 基本数据控制层
 *
 * @author gaosong
 * @date 2019/1/28.
 */
@RestController("dataController")
@RequestMapping({ "/admin/data", "/supplieradmin/data" }) // 接口工运营平台和供应商平台调用
public class DataController extends BaseAdminController {

	@Autowired
	private ExpressService expressService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DistricitInfoService districitInfoService;
	@Autowired
	private ProductSpecService productSpecService;

	@RequestMapping(value = "/queryPlaceOrigins", method = RequestMethod.GET)
	public JSONObject queryPlaceOrigins() {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("type", 2);
		List<DictionaryResDto> list = dictionaryService.queryByParam(null).stream().map(x -> Beans.convert(x, DictionaryResDto.class)).collect(toList());
		jsonObject.put("list", list);
		return Response.succ(jsonObject);
	}

	@RequestMapping(value = "/querySpecNames", method = RequestMethod.GET)
	public Object querySpecNames() {
		SessionSupplierInfo sessionSupplierInfo = SessionContextHolder.getSessionSupplierInfo();
		Integer supplierId;
		if(sessionSupplierInfo!=null) {
			supplierId=sessionSupplierInfo.getSupplierId().intValue();
		}else {
			supplierId=-1;
		}
		return Response.succ(productSpecService.getBaseProductSpecName(supplierId));
	}

	/**
	 * 国别、颜色、产地、性别
	 *
	 * @return
	 */
	@RequestMapping(value = "/queryDictionary", method = RequestMethod.POST)
	public JSONObject queryDictionary(@RequestBody DictionaryReqDto req) {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = JSON.parseObject(JSON.toJSONString(req));
		List<DictionaryResDto> list;
		if (req.getType() == 5) {
			DistrictInfoExample example = new DistrictInfoExample();
			example.createCriteria().andTypeEqualTo(2);
			List<DistrictInfo> districtInfos = districitInfoService.selectCacheList(example);
			list = districtInfos.stream().map(x -> Beans.convert(x, DictionaryResDto.class)).collect(toList());
		} else {
			list = dictionaryService.queryByParam(params).stream().map(x -> Beans.convert(x, DictionaryResDto.class)).collect(toList());
		}
		jsonObject.put("list", list);
		return Response.succ(jsonObject);
	}

	/**
	 * 物流信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/queryExpress", method = RequestMethod.GET)
	public JSONObject queryExpress() {
		JSONObject jsonObject = new JSONObject();
		List<ExpressInfo> list = expressService.selectByExample(null);
		jsonObject.put("list", list);
		return Response.succ(jsonObject);
	}

}
