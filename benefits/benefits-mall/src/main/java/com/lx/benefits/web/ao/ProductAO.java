package com.lx.benefits.web.ao;

import com.lx.benefits.bean.base.dto.MResultVO;
import com.lx.benefits.bean.base.dto.PageForSearch;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.constants.ProductConstant;
import com.lx.benefits.bean.dto.prd.ItemSku;
import com.lx.benefits.bean.dto.product.*;
import com.lx.benefits.bean.dto.sch.SearchQuery;
import com.lx.benefits.bean.dto.sch.result.ItemResult;
import com.lx.benefits.bean.dto.yianapi.PageInfo;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.entity.product.SkuEntity;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.enums.SalesPartten;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.bean.helper.PropertiesHelper;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.bean.vo.query.SearchItemVO;
import com.lx.benefits.bean.vo.query.SearchShopVO;
import com.lx.benefits.convert.ProductConvert;
import com.lx.benefits.convert.SearchConvert;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.service.product.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 * 商品业务层
 *
 * @author zhuss
 * @2016年1月4日 下午6:54:47
 */
@Service
public class ProductAO {

    private static final Logger log = LoggerFactory.getLogger(ProductAO.class);

    @Autowired
    private PropertiesHelper propertiesHelper;

    @Resource
    private SkuService skuService;

    @Resource
    private ProductService productService;

    /**
     * 商品详情
     *
     * @param product
     * @return
     */
    public MResultVO<ProductDetailVO> getProductDetail(QueryProduct product) {
        try {
            String shareUrl = propertiesHelper.shareProductUrl.trim().replace("TID", product.getTid()).replace("SKU", product.getSku());
            InfoDetailDto itemInfo = skuService.queryItemSkuTopicInfoForAPPHaiTao(product.getTid(),product.getSku());
            if (itemInfo.getSalesPattern() != null && itemInfo.getSalesPattern().equals(SalesPartten.OFF_LINE_GROUP_BUY.getValue())) {
                shareUrl = propertiesHelper.offLineGroupbuyShareUrl.trim().replace("TID", product.getTid()).replace("SKU", product.getSku());
            }
            if (null != itemInfo) {
                ProductDetailVO vo = ProductConvert.convertProductDetail(itemInfo, shareUrl, product.getTid());
                //商品类型
                vo.setItemType(itemInfo.getItemType());
                vo.setIsvirtual(itemInfo.getItemInfo().getItemStyle().toString());
                //设置商品税率说明
                vo = setProductTaxDescription(itemInfo, vo);

                // 以下为处理京东商品详情
                if (vo.getDetail() != null) {
                    vo.setDetail(vo.getDetail().replaceAll("620", "100%"));
                    vo.setDetail(vo.getDetail().replaceAll("width", " "));
                    vo.setDetail(vo.getDetail().replaceAll("<img", "<img width=100%"));
                    vo.setDetail(vo.getDetail().replaceAll("height=", " "));
                    vo.setDetail(vo.getDetail().replaceAll("src=\"//", "src=\"https://"));
                    //屏蔽京东商品详情的外链
                    int index = vo.getDetail().indexOf("item.jd.com");
                    int indexm = vo.getDetail().indexOf("item.m.jd.com");
                    int count = 0;

                    while ((index > -1 || indexm > -1) && count < 20) {
                        count++;
                        hideJDLink(vo, index);
                        hideJDLink(vo, indexm);
                        index = vo.getDetail().indexOf("item.jd.com");
                        indexm = vo.getDetail().indexOf("item.m.jd.com");
                    }
                    //这tm是为了屏蔽严选的音频文件
                    vo.setDetail(vo.getDetail().replaceAll("<audio", "<div style=\"display:none\""));
                    vo.setDetail(vo.getDetail().replaceAll("audio>", "div>"));
                }
                return new MResultVO<>(MResultInfo.SUCCESS, vo);
            }
            return new MResultVO<>(MResultInfo.FAILED);
        } catch (MobileException e) {
            log.error("[API接口 - 商品详情  MobileException]={}", e.getMessage());
            return new MResultVO<>(e);
        } catch (Exception e) {
            log.error("[API接口 - 商品详情  Exception]={}", e);
            return new MResultVO<>(MResultInfo.CONN_ERROR);
        }
    }

    private void hideJDLink(ProductDetailVO vo, int index) {
        if (index > 0) {
            String b = vo.getDetail().substring(0, index);
            String e = vo.getDetail().substring(index);

            int hrefIndex = b.lastIndexOf("href");
            if (hrefIndex > 0) {
                b = b.substring(0, hrefIndex);
            }
            int commIndex = e.indexOf("\"");

            if (commIndex > 0) {
                e = e.substring(commIndex + 1);
            }
            if (hrefIndex > 0 && commIndex > 0) {
                vo.setDetail(b + e);
            }
        }
    }

    //设置税率描述
    private ProductDetailVO setProductTaxDescription(InfoDetailDto itemInfo, ProductDetailVO vo) {
        if (ProductConstant.RATE_TYPE.POSTAL.code.equals(itemInfo.getRateType())) {
            vo.setTaxdesc(propertiesHelper.postalTaxDesc.replaceFirst("\\{\\d\\}", StringUtil.getStrByObj(itemInfo.getTaxRate())));
        } else if (ProductConstant.RATE_TYPE.TAXFREE.code.equals(itemInfo.getRateType())) {
            vo.setTaxdesc(propertiesHelper.freeTaxDesc);
        } else {
            vo.setTaxdesc(propertiesHelper.synthesisTaxDesc.replaceFirst("\\{\\d\\}", itemInfo.getRateName())
                    .replaceFirst("\\{\\d\\}", StringUtil.getStrByObj(itemInfo.getTaxRate())));
        }
        return vo;
    }

    public List<SearchItemVO> similar(String sku) {
        try {
            SkuEntity skuEntity = skuService.queryBySkuId(Long.parseLong(sku));
            if (skuEntity == null){
                return Collections.EMPTY_LIST;
            }
            Long smallId = skuEntity.getCategoryId3();
            if (smallId == null){
                return Collections.EMPTY_LIST;
            }
            Map<String,Object> query = new HashMap<>();
            query.put("categoryId3",smallId);
            List<ProductEntity> skusList = productService.queryByParam(query);

            if (skusList == null || skusList.size() < 1) {
                return Collections.EMPTY_LIST;
            }
            List<SearchItemVO> items = SearchConvert.convertSearchItem(skusList);
            if (items == null) {
                return Collections.EMPTY_LIST;
            }

            Iterator<SearchItemVO> iterator = items.iterator();
            while (iterator.hasNext()) {
                SearchItemVO v = iterator.next();
                if (v.getSku().equals(sku)) {
                    iterator.remove();
                    break;
                }
            }

            if (items.size() > 9) {
                return items.subList(0, 9);
            }
            if(items.size()<3) {
                return Collections.EMPTY_LIST;
            }
            return items;
        } catch (Exception e) {
            log.error("ITEM_SIMILAR_ERROR", e);
            return Collections.EMPTY_LIST;
        }
    }


}
