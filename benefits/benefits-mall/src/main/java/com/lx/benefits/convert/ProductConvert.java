package com.lx.benefits.convert;

import com.alibaba.fastjson.JSONArray;
import com.lx.benefits.bean.constants.DummyItemConstant;
import com.lx.benefits.bean.dto.product.*;
import com.lx.benefits.bean.enums.ImgEnum;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.enums.ProductEnum;
import com.lx.benefits.bean.enums.SalesPartten;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.bean.helper.ImgHelper;
import com.lx.benefits.bean.helper.SwitchHelper;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.JsonUtil;
import com.lx.benefits.bean.util.NumberUtil;
import com.lx.benefits.bean.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.*;


/**
 * 商品封装类
 * @author zhuss
 * @2016年1月5日 下午3:37:46
 */
public class ProductConvert {

	
	/**
	 * 封装商品的状态
	 * @param status: 0下架1上架2商品作废(上架包括正常的和已抢光的) isHava是否有库存
	 * @return
	 */
	public static ProductEnum.Status getStatusByPrdSta(String status, boolean isHava){
		if(StringUtil.isBlank(status)) throw new MobileException(MResultInfo.ITEM_ERROR);
		if(StringUtil.equals(status, ProductEnum.Status.NORMAL.code)&&isHava)return ProductEnum.Status.NORMAL; //已上架并有库存
		if(StringUtil.equals(status, ProductEnum.Status.TOPIC_BACKORDERED.code))return ProductEnum.Status.TOPIC_BACKORDERED; //备货中
		//if(StringUtil.equals(status, ItemComonConstant.ITEM_UNDERCARRIAGE))return ProductEnum.Status.ITEM_UNDERCARRIAGE; //已下架 - 有商详
		if(StringUtil.equals(status, ProductEnum.Status.NORMAL.code)&&!isHava)return ProductEnum.Status.ITEM_OUT_OF_STOCK; //已上架并没有库存- 有商详
		//if(StringUtil.equals(status, ItemComonConstant.ITEM_NO_USE))return ProductEnum.Status.ITEM_NO_USE; //已作废 - 没有商详
		return ProductEnum.Status.NORMAL;
	}

	/**
	 * 封装商品详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ProductDetailVO convertProductDetail(InfoDetailDto itemInfo, String shareUrl, String tid){
		ProductDetailVO vo = new ProductDetailVO();
		ProductEnum.Status statusenmu = getStatusByPrdSta(itemInfo.getStatus(),
				itemInfo.getOutOfStock() == null ? true : false);
		//未开始
		if(null != itemInfo.getStoretime() && itemInfo.getStoretime() > DateUtil.getTimestamp()){
			statusenmu = ProductEnum.Status.TOPIC_NO_START;
		}
		//已结束
		if(null != itemInfo.getEndtime() && itemInfo.getEndtime() < DateUtil.getTimestamp()){
			statusenmu = ProductEnum.Status.TOPIC_NO_END;
		}
		vo.setStatus(statusenmu.code);
		vo.setStatusdesc(statusenmu.desc);
		// 当商品的状态为已作废则 商品信息为空
		if (StringUtil.equals(statusenmu.code,ProductEnum.Status.ITEM_NO_USE.code))return vo;
		SkuDto skuDto = itemInfo.getSkuDto();
		if(null != skuDto){
			vo.setStock(StringUtil.getStrByObj(skuDto.getQuantity()));
			int stock = skuDto.getQuantity() == null?0:skuDto.getQuantity();
			if(stock == 0 && !ProductEnum.Status.TOPIC_BACKORDERED.code.equals(vo.getStatus())) {
				vo.setStatus(ProductEnum.Status.ITEM_OUT_OF_STOCK.code);
				vo.setStatusdesc(ProductEnum.Status.ITEM_OUT_OF_STOCK.desc);
			}

		}
		vo.setFeature(StringUtils.equals(itemInfo.getMainTitle(),itemInfo.getSubTitle())? "":itemInfo.getSubTitle());
		vo.setSalespattern(StringUtil.getStrByObj(itemInfo.getSalesPattern()));
		vo.setShareurl(shareUrl);
		vo.setCount(StringUtil.getStrByObj(itemInfo.getQuantity()));
		vo.setCountryimg(ImgHelper.getImgUrl(itemInfo.getCountryImagePath(), ImgEnum.Width.WIDTH_30));
		vo.setCountryname(itemInfo.getCountryName());
		vo.setDetail(ImgHelper.replaceImgInHTML(itemInfo.getDetailDesc(), ImgEnum.Width.WIDTH_750));
		List<String> imglist = JSONArray.parseArray(itemInfo.getListPictures(),String.class);
		if(CollectionUtils.isNotEmpty(imglist)){
			vo.setImgurl(ImgHelper.getImgUrl(imglist.get(0), ImgEnum.Width.WIDTH_75));
			vo.setImglist(imglist);
		}
		vo.setLimitcount(StringUtil.getStrByObj(itemInfo.getLimitCount()));
		vo.setName(itemInfo.getMainTitle());
		if(itemInfo.getBasicPrice() == null || itemInfo.getBasicPrice() <0D){
			vo.setOldprice("暂无报价");
		}else {
			vo.setOldprice(NumberUtil.sfwr(itemInfo.getBasicPrice()));
		}
		if(itemInfo.getXgPrice() == null || itemInfo.getXgPrice()<0D){
			vo.setPrice("暂无报价");
		}else {
			//当商品已下架，商品价格就是原价
			vo.setPrice(NumberUtil.sfwr(itemInfo.getXgPrice()));

		}
		// 展示Oldprice
		vo.setShowOldprice("1");
		if (!"暂无报价".equals(vo.getPrice()) && !"暂无报价".equals(vo.getOldprice())) {
			if(Double.compare(itemInfo.getBasicPrice(), itemInfo.getXgPrice()) <= 0) {
				// 不展示Oldprice
				vo.setShowOldprice("0");
				vo.setOldprice(vo.getPrice());
			}
		}
		vo.setCommision( NumberUtil.sfwr(itemInfo.getCommision()) );
		vo.setChannel(itemInfo.getSendType());
		vo.setSku(itemInfo.getSku());
		vo.setTaxrate(StringUtil.getStrByObj(itemInfo.getTaxRate()));
		vo.setTaxfee(StringUtil.getStrByObj(itemInfo.getRateFee()));
		vo.setTaxdesc(SwitchHelper.taxDesc().replaceFirst("\\{\\d\\}", itemInfo.getRateName()).replaceFirst("\\{\\d\\}", StringUtil.getStrByObj(itemInfo.getTaxRate())));
		String skuJsonStr = itemInfo.getListSkus();
		if(StringUtil.isNotBlank(skuJsonStr)){
			vo.setSkus(convertSkus(skuJsonStr));
		}
		vo.setTid(tid);
		//立即购买和加入购物车按钮展示
		vo.setPurchasepage(itemInfo.getPurchasePage());
		//解析标签
		if(StringUtil.isNotBlank(itemInfo.getItemTags())){
			List<TagTO> tags = new ArrayList<>();
			tags = (List<TagTO>) JsonUtil.convListByJsonStr(itemInfo.getItemTags(),TagTO.class);
			vo.setTags(tags);
		}
		//已售数量
		if(0 != itemInfo.getSalesCount())vo.setSalescountdesc("已售"+itemInfo.getSalesCount());

		vo.setUnit( itemInfo.getItemInfo() !=null ? itemInfo.getItemInfo().getUnitName() !=null ? itemInfo.getItemInfo().getUnitName():"":"");

		//处理服务商品的属性
		List<DummyProductAttr> dummyProductAttrs = new ArrayList<>();
		if(itemInfo.getSalesPattern() !=null && itemInfo.getSalesPattern().equals(SalesPartten.OFF_LINE_GROUP_BUY.getValue())){

			List<ItemAttribute> itemAttributeList = itemInfo.getItemAttribute();

			Iterator<ItemAttribute> itemAttributeIterator = itemAttributeList.iterator();
			String s = null;
			String e = null;
			boolean ie = false;
			while (itemAttributeIterator.hasNext()){
				ItemAttribute itemAttribute = itemAttributeIterator.next();
				if(itemAttribute.getCustom() == null || itemAttribute.getCustom() != 2){
					itemAttributeIterator.remove();
				}
				if(itemAttribute.getAttrKey().equals(DummyItemConstant.effecttimestart)) {
					s =  itemAttribute.getAttrValue() ;
					itemAttributeIterator.remove();
				}else if(itemAttribute.getAttrKey().equals(DummyItemConstant.effectTimeEnd)){
					e =  itemAttribute.getAttrValue() ;
					itemAttributeIterator.remove();
				}else if(itemAttribute.getAttrKey().equals(DummyItemConstant.includeFestival)){
					ie = StringUtils.equals(itemAttribute.getAttrValue(),"1")? true:false;
					itemAttributeIterator.remove();
				}

			}
			if(s !=null && e != null){
				StringBuilder builder = new StringBuilder().append(s).append("到").append(e).append(ie?"(节假日通用)":"");
				addList(dummyProductAttrs,"有效期",builder.toString());
				Date date = DateUtil.getDate(e, DateUtil.DATE_FORMAT);
				if(date == null){
					vo.setStatus(ProductEnum.Status.ITEM_NO_USE.code);
					vo.setStatusdesc(ProductEnum.Status.ITEM_NO_USE.desc);
				}else if(date.before(new Date())){
					vo.setStatus(ProductEnum.Status.TOPIC_NO_END.code);
					vo.setStatusdesc(ProductEnum.Status.TOPIC_NO_END.desc);
				}
			}


			for(ItemAttribute itemAttribute: itemAttributeList){
				if(StringUtils.isBlank(itemAttribute.getAttrValue())) continue;
				addList(dummyProductAttrs,itemAttribute.getAttrKey(),itemAttribute.getAttrValue());
			}
			vo.setDummyattr(dummyProductAttrs);
		}
		return vo;
	}


	/**
	 * 封装SKU集合
	 * @return
	 */
	public static List<SkuTO> convertSkus(String skuJsonStr){
		List<SkuTO> skulist = new ArrayList<>();
		List<SkuDto> skuArray = JSONArray.parseArray(skuJsonStr, SkuDto.class);
		if(CollectionUtils.isNotEmpty(skuArray)){
			//sku
			for(SkuDto dto : skuArray){
				SkuTO sku = new SkuTO();
				sku.setSku(dto.getSku());
				String specsStr= dto.getListSpec();
				if(StringUtil.isNotBlank(specsStr)){
					sku.setSkudetails(convertSkuDetails(specsStr));
				}
				skulist.add(sku);
			}
		}
		return skulist;
	}

	/**
	 * 封装SKU下的规格集合
	 * @param skuSpecJsonStr
	 * @return
	 */
	public static List<SkuDetailTO> convertSkuDetails(String skuSpecJsonStr){
		List<SkuDetailTO> skudetails = new ArrayList<>();
		List<ItemDetailSpec> specList = JSONArray.parseArray(skuSpecJsonStr, ItemDetailSpec.class);
		if(CollectionUtils.isNotEmpty(specList)){
			//sku对应的规格信息
			for(ItemDetailSpec spe : specList){
				skudetails.add(new SkuDetailTO(StringUtil.getStrByObj(spe.getSpecGroupId()),StringUtil.getStrByObj(spe.getSpecId())));
			}
			Collections.sort(skudetails, new Comparator<SkuDetailTO>() {
				@Override
				public int compare(SkuDetailTO sku1, SkuDetailTO sku2) {
					return sku1.getGroupid().compareTo(sku2.getGroupid());
				}
			});
		}
		return skudetails;
	}

	private static void addList(List<DummyProductAttr> list, String key, String val){
		for(DummyProductAttr attr: list){
			if(attr.getName().equals(key)){
				if(attr.getCols()==null) attr.setCols(new ArrayList<String>());
				attr.getCols().add(val);
				return;
			}
		}
		List<String> listF = new ArrayList<>();
		listF.add(val);
		list.add(new DummyProductAttr(key,listF));
	}
}
