package com.lx.benefits.convert;

import com.lx.benefits.bean.dto.query.QuerySearch;
import com.lx.benefits.bean.dto.sch.*;
import com.lx.benefits.bean.dto.sch.result.Aggregate;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.enums.ImgEnum;
import com.lx.benefits.bean.enums.ProductEnum;
import com.lx.benefits.bean.helper.ImgHelper;
import com.lx.benefits.bean.util.NumberUtil;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.bean.vo.query.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索封装类
 * @author zhuss
 * @2016年3月2日 上午11:56:52
 */
public class SearchConvert {


	/**
	 * 封装导航
	 * @return
	 */
	public static NavigationVO convertNavigation(Nav nav){
		NavigationVO vo = new NavigationVO();
		if(null != nav){
			List<NavCategoryDTO> categorieDTOs = nav.getCategories();
			if(CollectionUtils.isNotEmpty(categorieDTOs)){
				List<NavCategoryVO> categories = new ArrayList<>();
				for(NavCategoryDTO dto : categorieDTOs){
					categories.add(convertNavCategory(dto));
				}
				vo.setCategories(categories);
			}
			List<NavBrandDTO> brandDTOs = nav.getBrands();
			if(CollectionUtils.isNotEmpty(brandDTOs)){
				List<NavBrandVO> brands = new ArrayList<>();
				for(NavBrandDTO dto : brandDTOs){
					brands.add(convertNavBrand(dto));
				}
				vo.setBrands(brands);
			}
		}
		return vo;
	}
	
	/**
	 * 封装搜索导航的分类
	 * @param dto
	 * @return
	 */
	public static NavCategoryVO convertNavCategory(NavCategoryDTO dto){
		NavCategoryVO navc = new NavCategoryVO();
		navc.setCategoryid(StringUtil.getStrByObj(dto.getId().toString()));
//		if(dto.getLevel() != null && dto.getLevel()>1){
//			navc.setImgurl(ImgHelper.getImgUrl(dto.getPic(), ImgEnum.Width.WIDTH_210));
//		}else {
//			navc.setImgurl(ImgHelper.getImgUrl(dto.getPic(), ImgEnum.Width.WIDTH_750));
//		}
		navc.setName(dto.getName());
		navc.setNameEn(dto.getNameEn());
		navc.setIshighlight(StringUtil.getStrByObj(dto.getIsHighlight()));
		List<NavCategoryDTO> list = dto.getChild();
		if(CollectionUtils.isNotEmpty(list)){
			List<NavCategoryVO> child = new ArrayList<>();
			for(NavCategoryDTO d : list){
				child.add(convertNavCategory(d));
			}
			navc.setChild(child);
		}
		return navc;
	}
	
	/**
	 * 封装搜索导航 - 品牌
	 * @param dto
	 * @return
	 */
	public static NavBrandVO convertNavBrand(NavBrandDTO dto){
		NavBrandVO navc = new NavBrandVO();
		navc.setName(dto.getName());
		//navc.setImgurl(ImgHelper.getImgUrl(dto.getPic(), ImgEnum.Width.WIDTH_750));
		List<NavBrandSimple> list = dto.getBrands();
		if(CollectionUtils.isNotEmpty(list)){
			List<NavBrandVO> child = new ArrayList<>();
			for(NavBrandSimple d : list){
				child.add(new NavBrandVO(StringUtil.getStrByObj(d.getBrandId()),d.getName(),ImgHelper.getImgUrl(d.getPic(), ImgEnum.Width.WIDTH_180)));
			}
			navc.setChild(child);
		}
		return navc;
	}

	/**
	 * 封装搜索查询条件
	 * @return
	 */
	public static SearchQuery convertSearchParam(QuerySearch search){
		SearchQuery query = new SearchQuery();
		query.setNavBrandId(StringUtil.getLongByStr(search.getBrandid()));
		query.setNavCategoryId(StringUtil.getLongByStr(search.getCategoryid()));
		query.setKey(search.getKey());
		//query.setPlatform(RequestHelper.getPlatformByName(search.getApptype()));
		//query.setSort(StringUtil.getIntegerByStr(search.getSort()));
		//query.setStartPage(StringUtil.getCurpageDefault(search.getCurpage()));
		query.setPageSize(search.getSize());

		//query.setHasInventoryOnly(StringUtil.getIntegerByStr(search.getIsinstock()));
		//query.setSalesPattern(StringUtil.getIntegerByStr(search.getSalespattern()));
		query.setCouponId(StringUtil.getLongByStr(search.getCouponid()));

		//Double priceFrom = StringUtil.getDoubleFromString(search.getPricefrom());
		//Double priceTo = StringUtil.getDoubleFromString(search.getPriceto());
		//query.setPriceFrom(priceFrom == null? null: priceFrom <0D? 0D: priceFrom);
		//query.setPriceTo(priceTo == null ? null: priceTo< 0D? 0D: priceTo);
		//query.setSelfOnly(StringUtil.getIntegerByStr(search.getSelfonly()));

		//query.setBrandIds(getLongs(search.getBrand_id()) );
		//query.setCountryIds(getLongs(search.getCountry_id()));
		//query.setSmallCategoryIds(getLongs(search.getSmall_category_id()));
		query.setLan(search.getLan());
		return query;
	}

	/**
	 * 封装搜索结果页面中的筛选条件
	 * @return
	 */
	public static List<SearchConditionVO> convertSearchCondition(List<Aggregate> aggregates){
		List<SearchConditionVO> list = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(aggregates)){
			for(Aggregate agg : aggregates){
				SearchConditionVO vo = new SearchConditionVO();
				if (agg.getCode().equals("count")) {
					vo.setCount(agg.getCount());
					vo.setCode(agg.getCode());
				} else {
					vo.setCode(agg.getCode());
					vo.setName(agg.getName());
					List<Element> elements = agg.getElements();
					if(CollectionUtils.isNotEmpty(elements)){
						List<SearchConditionElementVO> child = new ArrayList<>();
						for (Element element : elements) {
							String key = element.getKey();
							if (StringUtils.isEmpty(key) || "null".equals(key)) {
								continue;
							}
							child.add(new SearchConditionElementVO(key, element.getValue(),element.getValueEn()));
						}
						vo.setElement(child);
					}
				}
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * 封装搜索结果数据
	 *
	 * @return
	 */
//	public static PageForSearch<SearchItemVO, List<SearchShopVO>> convertSearchItem(PageInfo<ItemResult> itemsPage, ShopResult shopResult, Long topicId) {
//		PageForSearch<SearchItemVO, List<SearchShopVO>> pages = new PageForSearch<>();
//		pages.setPagesize(itemsPage.getSize());
//		if (null != itemsPage) {
//			List<ItemResult> items = itemsPage.getRows();
//			if (CollectionUtils.isNotEmpty(items)) {
//				List<SearchItemVO> l = new ArrayList<>();
//				for (ItemResult item : items) {
//					boolean isHave = item.getInventory() == null || item.getInventory() == 0 ? false : true;
//					ProductEnum.Status statusenmu = ProductConvert.getStatusByPrdSta(StringUtil.getStrByObj(item.getItem_status()), isHave);
//					SearchItemVO vo = new SearchItemVO();
//					vo.setChannel(item.getChannel_name());
//					vo.setChannelid(StringUtil.getStrByObj(item.getChannel_id()));
//					vo.setCountryname(item.getCountry_name());
//					vo.setImgurl(ImgHelper.getImgUrl(item.getItem_img(), ImgEnum.Width.WIDTH_360));
//					vo.setName(item.getItem_name().contains("【京东超市】")?item.getItem_name().replace("【京东超市】",""):item.getItem_name());
//					vo.setOldprice(NumberUtil.sfwr(item.getSale_price().doubleValue()));
//					vo.setPrice(NumberUtil.sfwr(item.getTopic_price().doubleValue()));
//					vo.setSku(item.getSku());
//					vo.setStatus(statusenmu.code);
//					vo.setStatusdesc(statusenmu.desc);
//					vo.setTid(StringUtil.getStrByObj(item.getTopic_id()));
//					vo.setSalescount(item.getSales_count()==null?"" : item.getSales_count()<=0 ? "" :"已售"+item.getSales_count());
//					vo.setSalespattern(StringUtil.getStrByObj(item.getSales_pattern()));
//					vo.setShopname(StringUtil.getStrByObj(item.getShop_name()));
//					if(null!=item.getItemStyle()){
//						vo.setItemstyle(StringUtil.getStrByObj(item.getItemStyle()));
//					}
//					if (Double.compare(Double.valueOf(vo.getOldprice()), Double.valueOf(vo.getPrice())) > 0) {
//						// 展示Oldprice
//						vo.setShowOldprice("1");
//					} else {
//						// 不展示Oldprice
//						vo.setShowOldprice("0");
//						vo.setOldprice(vo.getPrice());
//					}
//					l.add(vo);
//				}
//				pages.setFieldTCount(l, itemsPage.getPage(), itemsPage.getRecords());
//			}
//			pages.setCurpage(itemsPage.getPage());
//		}
//		if (shopResult != null && topicId !=null) {
//			SearchShopVO searchShopVO = new SearchShopVO();
//			searchShopVO.setShopid(shopResult.getShop_id().toString());
//			searchShopVO.setShopname(shopResult.getShop_name());
//			searchShopVO.setShopbanner(ImgHelper.getImgUrl(ImageUtil.getImgFullUrl(Constant.IMAGE_URL_TYPE.item,shopResult.getShop_banner()), ImgEnum.Width.WIDTH_750));
//			searchShopVO.setShoplogo(ImgHelper.getImgUrl(ImageUtil.getImgFullUrl(Constant.IMAGE_URL_TYPE.item,shopResult.getShop_logo()), ImgEnum.Width.WIDTH_80));
//			searchShopVO.setShopintro(shopResult.getShop_intro());
//			searchShopVO.setSupplierid(shopResult.getSupplier_id());
//			searchShopVO.setTid(String.valueOf(topicId));
//			pages.setSp(Arrays.asList(searchShopVO));
//
//		}else {
//			pages.setSp(Collections.EMPTY_LIST);
//		}
//		return pages;
//	}


	/**
	 * 封装搜索结果数据
	 *
	 * @return
	 */
	public static List<SearchItemVO>  convertSearchItem(List<ProductEntity> list) {
		List<SearchItemVO> searchItemList = new ArrayList<>();
		if (null != list) {
			if (CollectionUtils.isNotEmpty(list)) {
				for (ProductEntity item : list) {
					boolean isHave = item.getStock() == null || item.getStock() == 0 ? false : true;
					ProductEnum.Status statusenmu = ProductConvert.getStatusByPrdSta(StringUtil.getStrByObj(item.getGoodsState()), isHave);
					SearchItemVO vo = new SearchItemVO();
					vo.setCountryname(item.getPlaceOrigin());
					vo.setImgurl(ImgHelper.getImgUrl(item.getGoodsImage(), ImgEnum.Width.WIDTH_360));
					vo.setName(item.getGoodsName().contains("【京东超市】")?item.getGoodsName().replace("【京东超市】",""):item.getGoodsName());
					vo.setOldprice(NumberUtil.sfwr(item.getGoodsPrice().doubleValue()));
					vo.setPrice(NumberUtil.sfwr(item.getGoodsPrice().doubleValue()));
					vo.setSku(item.getSkuCode());
					vo.setStatus(statusenmu.code);
					vo.setStatusdesc(statusenmu.desc);
					vo.setTid(StringUtil.getStrByObj(item.getTopicId()));
					if(null!=item.getGoodsType()){
						vo.setItemstyle(StringUtil.getStrByObj(item.getGoodsType()));
					}
					if (Double.compare(Double.valueOf(vo.getOldprice()), Double.valueOf(vo.getPrice())) > 0) {
						// 展示Oldprice
						vo.setShowOldprice("1");
					} else {
						// 不展示Oldprice
						vo.setShowOldprice("0");
						vo.setOldprice(vo.getPrice());
					}
					searchItemList.add(vo);
				}
			}
		}
		return searchItemList;
	}

}
