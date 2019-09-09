package com.lx.benefits.web.controller.enterprise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.dto.admin.operation.ActivityThemeProducts;
import com.lx.benefits.bean.entity.operation.ActivityThemeEntity;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.operation.ActivityThemeService;

import io.swagger.annotations.Api;

/**
 * @author unknow on 2018-12-10 23:48.
 */
@Api(tags = "企业商城-banner模块")
@RestController("themeController")
@RequestMapping("/enterprise/theme")
public class ThemeController extends BaseEnterpriseController {

	@Autowired
	private ActivityThemeService activityThemeService;

	@GetMapping("/products")
	public Object queryThemes(PageBean pageBean) {
		List<ActivityThemeProducts> products=activityThemeService.getThemeProducts(pageBean);
		return Response.succ(products);
	}

	@RequestMapping(value = "/queryThemesById/{id}", method = RequestMethod.GET)
	public JSONObject queryThemesById(@PathVariable Integer id) {
		ActivityThemeEntity activityThemeEntity = activityThemeService.selectById(id);
		if (activityThemeEntity != null) {
			JSONObject response = new JSONObject();
			response.put("info", activityThemeEntity);
			return Response.succ(response);
		} else {
			return Response.fail("专栏已下架");
		}
	}

}
