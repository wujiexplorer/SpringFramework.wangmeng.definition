package com.lx.benefits.service.search.impl;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.base.dto.ResultInfo;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.sch.Element;
import com.lx.benefits.bean.dto.sch.SearchQuery;
import com.lx.benefits.bean.dto.sch.result.*;
import com.lx.benefits.bean.dto.yianapi.PageInfo;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.mapper.product.ProductMapper;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.search.SearchService;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private EnterprCustomGoodsService enterprCustomGoodsService;

    @Override
    public ResultInfo<PageInfo<ItemResult>> search(SearchQuery query) throws Exception {
        if (query == null || (StringUtils.isBlank(query.getKey()) && query.getNavCategoryId() == null
                && query.getNavBrandId() == null && query.getCouponId() == null && query.getSupplierId() == null
                &&CollectionUtils.isEmpty( query.getSmallCategoryIds()))) {
            return new ResultInfo<>(new PageInfo<>());
        }

        //分类导航
//        if (query.getNavCategoryId() != null) {
//            List<NavigationCategoryRange> list = getNavigationCategoryRanges(query);
//            if (CollectionUtils.isEmpty(list)) {
//                LOGGER.warn("NAVIGATION_CATEGORY_SEARCH:CATEGORY_ID_HAS_NO_RANGES,CATEGORY_ID:{}", query.getNavCategoryId());
//                return new ResultInfo<>(new PageInfo<>());
//            }
//           // assembleNavSearchFilter(search, list);
//        }

        //品牌导航
        if (query.getNavBrandId() != null) {
            //search.addFilter("contain(brand_id,\"" + query.getNavBrandId() + "\")");
        }


        if (StringUtils.isNoneBlank(query.getKey())) {
           // search.setFormulaName(SortFormula.SEARCH.getFormula());
        } else {
          //  search.setFormulaName(SortFormula.NAVIGATION.getFormula());
        }

       // LOGGER.debug("SEARCH_FILTER:" + search.getFilter());
        int startPage = (query.getStartPage() == null ? 1 : query.getStartPage() < 1 ? 1 : query.getStartPage());
        int hits = query.getPageSize() == null ? 20 : query.getPageSize() < 1 ? 20 : query.getPageSize();
        int startHit = (startPage - 1) * hits;
//        search.setHits(hits);
//        search.setStartHit(startHit);
//        if (startPage == 1) {
//            searchKeyCensusService.addSearchKeyCensus(query);
//        }
//        LOGGER.info("SEARCH_FILTER" + search.getFilter());
//        String result = search.search();
//        LOGGER.debug("SEARCH_RESULT:" + result);
//        ItemSearchResult searchResult = JSON.parseObject(result, ItemSearchResult.class);
//        if (!StringUtils.equalsIgnoreCase(searchResult.getStatus(), "OK")) {
//            LOGGER.error("SEARCH_ERROR,RESULT:" + result);
//            LOGGER.error("SEARCH_ERROR,FILTER:" + search.getFilter());
//            boolean ignore = isIgnore(searchResult);
//            if (!ignore) {
//                return new ResultInfo<>(new FailInfo("系统繁忙,请稍后再试", -1));
//            }
//            LOGGER.error("SEARCH_ERROR,IGNORE_THIS_ERROR.RESULT:" + result);
//        }
        PageInfo<ItemResult> page = new PageInfo();
      //  List<ItemResult> searchResultItemList = searchResult.getResult().getItems();
//        for (ItemResult item : searchResultItemList) {
//            /*查商品类型*/
//            ItemSku qrysku = new ItemSku();
//            qrysku.setSku(item.getSku());
//            ItemSku sku = itemSkuService.queryUniqueByObject(qrysku);
//            /*--------*/
//            item.setItem_img(ImageUtil.getImgFullUrl(Constant.IMAGE_URL_TYPE.item, item.getItem_img()));
//            if (null != sku && null != sku.getItemStyle()) {
//                item.setItemStyle(sku.getItemStyle());
//            } else {
//                item.setItemStyle(0);
//            }
//        }
        //page.setRows(searchResultItemList);
        page.setPage(startPage);
        page.setSize(query.getPageSize());
        //page.setRecords(searchResult.getResult().getTotal());

        return new ResultInfo<>(page);
    }

    @Override
    public ResultInfo<List<Aggregate>> aggregate(SearchQuery query) throws IOException {
        ResultInfo<List<Aggregate>> result = new ResultInfo<>();
        ResultInfo<List<Aggregate>>  resultInfo = this.aggregateAction(query);
        result.setData(resultInfo.getData());
        result.setMsg(resultInfo.getMsg());
        result.setSuccess(resultInfo.isSuccess());
        return result;
    }

    @Override
    public ResultInfo<List<Aggregate>> aggregateNav(SearchQuery query) throws IOException {
        ResultInfo<List<Aggregate>> result = new ResultInfo<>();
        ResultInfo<List<Aggregate>>  resultInfo = this.aggregateActionNav(query);
        result.setData(resultInfo.getData());
        result.setMsg(resultInfo.getMsg());
        result.setSuccess(resultInfo.isSuccess());
        return result;
    }

    private ResultInfo<List<Aggregate>> aggregateActionNav(SearchQuery query) throws IOException {
        if (query == null ||  StringUtils.isEmpty(query.getKey())) {
            LOGGER.error("AGGREGATE_SEARCH:PARAM_ERROR:PARAM=", JSON.toJSONString(query));
            return new ResultInfo<>(Collections.EMPTY_LIST);
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("gooodsState",1);
        paramMap.put("key",query.getKey());

        // 品牌
        Long enterpriseId =  SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
        GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(enterpriseId);
        List<Long> brandIdsList = goodsModuleInfoDto.getBrandIdsList();
        LinkedHashSet<Element> brandInfo = new LinkedHashSet<>();
        // 分类
        LinkedHashSet<Element> smallCategoryInfo = new LinkedHashSet<>();
        // 产地
        LinkedHashSet<Element> countryInfo = new LinkedHashSet<>();

        List<ProductEntity> skuList = productMapper.selectContion(paramMap);
        for (ProductEntity entity:skuList) {
            Long brandId = entity.getBrandId();
            if (brandIdsList == null || !brandIdsList.contains(brandId)) {
                Element element = new Element(brandId.toString(),entity.getBrandName());
                if (!brandInfo.contains(element)) {
                    brandInfo.add(element);
                }
            }
            // 分类
            if (entity.getCategoryId3() != null) {
                Element element = new Element(entity.getCategoryId3().toString(),entity.getCategoryName3());
                if (!smallCategoryInfo.contains(element)) {
                    smallCategoryInfo.add(element);
                }
            } else {
                if (entity.getCategoryId2() != null) {
                    Element element = new Element(entity.getCategoryId2().toString(),entity.getCategoryName2());
                    if (!smallCategoryInfo.contains(element)) {
                        smallCategoryInfo.add(element);
                    }
                }
            }
            if (null != entity.getPlaceOriginId()) {
                Element element = new Element(String.valueOf(entity.getPlaceOriginId()), entity.getPlaceOrigin());
                if (!countryInfo.contains(element)) {
                    countryInfo.add(element);
                }
            }
        }

        List<Aggregate> aggregateList = new ArrayList<>();
        if (!brandInfo.isEmpty()) {
            aggregateList.add(new Aggregate("brand_id", "品牌", new ArrayList<>(brandInfo)));
        }
        if (!countryInfo.isEmpty()) {
            aggregateList.add(new Aggregate("country_id", "产地", new ArrayList<>(countryInfo)));
        }
		if (!smallCategoryInfo.isEmpty()) {
			HashedMap<Long, Element> catMap = new HashedMap<>();
			List<Long> catIds = smallCategoryInfo.parallelStream().map(item -> {
				try {
					Long catId = Long.valueOf(item.getKey());
					catMap.put(catId, item);
					return catId;
				} catch (Exception e) {
					return null;
				}
			}).filter(Objects::nonNull).collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(catIds)) {
				List<CategoryEntity> nameByIds = categoryMapper.getNameByIds(catIds);
				if (!CollectionUtils.isEmpty(nameByIds)) {
					for (CategoryEntity categoryEntity : nameByIds) {
						catMap.get(categoryEntity.getId()).setValueEn(categoryEntity.getNameEn());
					}
				}
			}
			aggregateList.add(new Aggregate("small_category_id", "分类", new ArrayList<>(smallCategoryInfo)));
		}
        aggregateList.add(new Aggregate("count",skuList.size()));
        return new ResultInfo(aggregateList);
    }

    public ResultInfo<List<Aggregate>> aggregateAction(SearchQuery query) throws IOException {
        if (query == null || (StringUtils.isBlank(query.getKey()) && query.getNavCategoryId() == null && query.getNavBrandId() == null&&query.getCouponId()==null)) {
            LOGGER.error("AGGREGATE_SEARCH:PARAM_ERROR:PARAM=", JSON.toJSONString(query));
            return new ResultInfo<>(Collections.EMPTY_LIST);
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("goodsState",1);
        paramMap.put("categoryId",query.getNavCategoryId());

        // 分类
        LinkedHashSet<Element> smallCategoryInfo = new LinkedHashSet<>();
        List<ProductEntity> productEntityList = productMapper.selectContionCategory(paramMap);
        List<Long> idList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList) {

            if (!idList.contains(productEntity.getCategoryId2())) {
                if (!query.getNavCategoryId().equals(productEntity.getCategoryId2())) {
                    idList.add(productEntity.getCategoryId2());
                }
            }
            if (!idList.contains(productEntity.getCategoryId3())) {
                if (!query.getNavCategoryId().equals(productEntity.getCategoryId3())) {
                    idList.add(productEntity.getCategoryId3());
                }
            }
        }
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(idList)) {
			categoryEntityList = categoryMapper.getNameByIds(idList);
		}
		for (CategoryEntity categoryEntity : categoryEntityList) {
			if (categoryEntity.getName() == null || categoryEntity.getName().isEmpty()) {
				continue;
			}
			smallCategoryInfo.add(new Element(categoryEntity.getId().toString(), categoryEntity.getName(), categoryEntity.getNameEn()));
		}

        // 品牌
        Long enterpriseId =  SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
        GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(enterpriseId);
        List<Long> brandIdsList = goodsModuleInfoDto.getBrandIdsList();
        LinkedHashSet<Element> brandInfo = new LinkedHashSet<>();
        List<ProductEntity> brandEntityList = productMapper.selectBrandByCategoryId(paramMap);
        for (ProductEntity productEntity : brandEntityList) {
            if (productEntity == null || productEntity.getBrandName() == null || productEntity.getBrandName().isEmpty()) {
                continue;
            }
            Long brandId = productEntity.getBrandId();
            if (brandIdsList != null &&!brandIdsList.contains(brandId)) {
                brandInfo.add(new Element(brandId.toString(),productEntity.getBrandName()));
            }
        }

        // 产地
        LinkedHashSet<Element> countryInfo = new LinkedHashSet<>();
        List<ProductEntity> placeOriginList = productMapper.selectPlaceOrginByCategoryId(paramMap);
        for (ProductEntity productEntity : placeOriginList) {
            if (productEntity == null || productEntity.getPlaceOrigin() == null || productEntity.getPlaceOrigin().isEmpty()) {
                continue;
            }
            countryInfo.add(new Element(String.valueOf(productEntity.getPlaceOriginId()), productEntity.getPlaceOrigin()));
        }

        List<Aggregate> aggregateList = new ArrayList<>();
        if (!brandInfo.isEmpty()) {
            aggregateList.add(new Aggregate("brand_id", "品牌", new ArrayList<>(brandInfo)));
        }
        if (!countryInfo.isEmpty()) {
            aggregateList.add(new Aggregate("country_id", "产地", new ArrayList<>(countryInfo)));
        }
        if (!smallCategoryInfo.isEmpty()) {
            aggregateList.add(new Aggregate("small_category_id", "分类", new ArrayList<>(smallCategoryInfo)));
        }

        return new ResultInfo(aggregateList);
    }

    @Override
    public ResultInfo<ShopResult> searchShop(SearchQuery query) throws IOException {
        return null;
    }


}
