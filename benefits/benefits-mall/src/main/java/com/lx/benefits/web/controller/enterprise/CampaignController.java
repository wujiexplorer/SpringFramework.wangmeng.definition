package com.lx.benefits.web.controller.enterprise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.campaign.FestivalPacketInfoBean;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.enterprfestival.EnterprFestivalService;

import io.swagger.annotations.Api;

/**
 * @author unknow on 2018-12-26 21:03.
 */
@Api(tags = "企业商城-企业活动模块")
@RestController("enterpriseCampaignController")
@RequestMapping("/enterprise/campaign")
public class CampaignController {

	@Autowired
	private EnterprFestivalService enterprFestivalService;

	// 获取所有的节日活动商品
	@GetMapping("/festival/products")
	public Object festivalProducts(PageBean pageBean) {
		pageBean.setPage(1);// 只能获取第一页的，其他页通过获取商品列表接口获取
		return Response.succ(enterprFestivalService.getPCFestivalProducts(null, pageBean));
	}

	// 获取某个活动的商品
	@GetMapping("/festival/{campaignId}/products")
	public Object festivalCampaignProducts(@PathVariable Long campaignId, PageBean pageBean) {
		pageBean.setPage(1);// 只能获取第一页的，其他页通过获取商品列表接口获取
		List<FestivalPacketInfoBean> pcFestivalProducts = enterprFestivalService.getPCFestivalProducts(campaignId, pageBean);
		if (CollectionUtils.isEmpty(pcFestivalProducts)) {
			return Response.fail("活动不存在或已过期");
		}
		return Response.succ(pcFestivalProducts);
	}

	// 获取某个活动某个专题的商品
	@GetMapping("/festival/{campaignId}/{topicId}/products")
	public Object festivalCampaignTopicProducts(@PathVariable Long campaignId, @PathVariable Integer topicId, PageBean pageBean) {
		PageResultBean<ProductEntity> products = enterprFestivalService.getPCFestivalTopicProducts(campaignId, topicId, pageBean);
		return Response.succ(products);
	}
}
