package com.lx.benefits.web.controller.yianapi;


import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.dto.jd.utils.AssertUtil;
import com.lx.benefits.bean.dto.product.ProductDetailVO;
import com.lx.benefits.bean.dto.product.QueryProduct;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.vo.query.SearchItemVO;
import com.lx.benefits.utils.RequestHelper;
import com.lx.benefits.web.ao.ProductAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品控制器
 * @author zhuss
 * @2016年1月5日 下午3:33:28
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Resource
	private ProductAO productAO;

	/**
	 * 商品详情
	 * @param
	 * @return
	 */
	@RequestMapping(value="/detail.htm", method= RequestMethod.GET)
	@ResponseBody
	public String getProductDetail(QueryProduct product){
		try{
			if(log.isInfoEnabled()){
				log.info("[API接口 -商品详情 入参] = {}", JsonUtil.convertObjToStr(product));
			}
			AssertUtil.notBlank(product.getSku(), MResultInfo.ITEM_SKU_NULL.message);
			AssertUtil.notBlank(product.getTid(), MResultInfo.TOPIC_ID_NULL.message);

			MResultVO<ProductDetailVO> result = productAO.getProductDetail(product);
			// 专场不限购设置兼容app 1.0.0
			if (RequestHelper.isAPP(product.getApptype()) && "1.0.0".equals(product.getAppversion().trim())
					&&  null != result.getData() && "0".equals(result.getData().getLimitcount())) {
					result.getData().setLimitcount("99");
			}
			if(log.isInfoEnabled()){
				log.info("[API接口 -商品详情 返回值] = {}",JsonUtil.convertObjToStr(result));
			}
			return JsonUtil.convertObjToStr(result);
		}catch(MobileException me){
			log.error("[API接口 - 商品详情  MobileException] = {}",me.getMessage());
			return JsonUtil.convertObjToStr(new MResultVO<>(MResultInfo.PARAM_ERROR));
		}
	}


	/**
	 * 相似商品
	 * @param
	 * @return
	 */
	@RequestMapping(value="/similar", method= RequestMethod.GET)
	@ResponseBody
	public String getSimilar(QueryProduct product){
		try{
			List<SearchItemVO>  result = productAO.similar(product.getSku());
			if(log.isInfoEnabled()){
				log.info("[SIMILAR] = {}",JsonUtil.convertObjToStr(result));
			}
			return JsonUtil.convertObjToStr(new MResultVO<>(MResultInfo.SUCCESS,result));
		}catch(Exception me){
			log.error("[API接口 - SIMILAR ERROR  MobileException] = {}",me.getMessage());
			return JsonUtil.convertObjToStr(new MResultVO<>(MResultInfo.SUCCESS));
		}
	}

}
