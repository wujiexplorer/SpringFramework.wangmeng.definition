package com.lx.benefits.service.navigation.impl;

import com.lx.benefits.bean.constants.Constant;
import com.lx.benefits.bean.constants.RedisCacheKeyConstant;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.base.NavigationCategoryRange;
import com.lx.benefits.bean.dto.sch.Nav;
import com.lx.benefits.bean.dto.sch.NavBrandDTO;
import com.lx.benefits.bean.dto.sch.NavBrandSimple;
import com.lx.benefits.bean.dto.sch.NavCategoryDTO;
import com.lx.benefits.bean.dto.sch.enums.NavigationType;
import com.lx.benefits.bean.entity.product.BrandEntity;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.entity.product.ProductEntity;
import com.lx.benefits.bean.util.BeanUtil;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.vo.navigation.NavigationCategory;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.navigation.NavigationCategoryService;
import com.lx.benefits.service.product.BrandService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.utils.RedisUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lx.benefits.bean.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.*;
import static java.util.stream.Collectors.toList;

@Service
public class NavigationCategoryServiceImpl implements NavigationCategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Resource
    private BrandService brandService;

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private ProductService productService;

    @Autowired
    private EnterprCustomGoodsService enterprCustomGoodsService;

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());


	@Override
	public Nav getNav() {
		Long enterprId = SessionContextHolder.getSessionEmployeeInfo().getEnterprId();
		String cachKey = String.format(RedisCacheKeyConstant.NAV_CACHE_KEY, enterprId);
		Nav nav = redisUtils.get(cachKey, Nav.class);
		if (nav == null) {
			synchronized (Byte.valueOf(enterprId.byteValue())) {//根据企业ID加锁
				nav = redisUtils.get(cachKey, Nav.class);
				if (nav == null) {
					LOGGER.info("NAV_LOAD_FROM_DB...");
					List<NavCategoryDTO> navCateDTOs = getNavigationCategory(enterprId);
					nav = new Nav(navCateDTOs, null);
					redisUtils.set(cachKey, nav, 2 * 60 * 60);// 缓存2小时
				}
			}
		}
		return nav;
	}

    private  List<NavCategoryDTO> getNavigationCategory(Long enterprId) {
        Map<Long,Long> filterMap = getFilterSpuCategory();
        // 分类无上架商品
        NavigationCategory query = new NavigationCategory();
        query.setStatus(1);
        query.setParentId(0L);
        // 过滤分类
        // 查询企业过滤类目
        GoodsModuleInfoDto  goodsModuleInfoDto= enterprCustomGoodsService.findByIdWithAgent(enterprId);
        List<Long> enterCategroyList = goodsModuleInfoDto.getCategoryIdsList();
        if (null != enterCategroyList && enterCategroyList.size() > 0) {
            query.setCategoryIdsList(enterCategroyList);
        }
        List<NavigationCategory> firCateListAll = this.queryByParam(BeanUtil.beanMap(query));
        List<NavigationCategory> firCateList = new ArrayList<>();
        for (NavigationCategory navigationCategory : firCateListAll) {
            if (filterMap.containsKey(navigationCategory.getId())) {
                firCateList.add(navigationCategory);
            }
        }

        List<Long> list = new ArrayList<>();
        for (NavigationCategory category : firCateList) {
            if (filterMap.containsKey(category.getId())) {
                list.add(category.getId());
            }
        }
        query.setParentId(null);
        query.setIds(list);
        List<NavigationCategory> secCateListAll = this.queryByParam(BeanUtil.beanMap(query));
        List<NavigationCategory> secCateList = new ArrayList<>();
        for (NavigationCategory navigationCategory : secCateListAll) {
            if (filterMap.containsKey(navigationCategory.getId())) {
                secCateList.add(navigationCategory);
            }
        }
        List<Long> seclist = new ArrayList<>();
        for (NavigationCategory category : secCateList) {
            if (filterMap.containsKey(category.getId())) {
                seclist.add(category.getId());
            }
        }

        query.setParentId(null);
        query.setIds(seclist);
        List<NavigationCategory> thirdCateListAll = this.queryByParam(BeanUtil.beanMap(query));
        List<NavigationCategory> thirdCateList = new ArrayList<>();
        for (NavigationCategory navigationCategory : thirdCateListAll) {
            if (filterMap.containsKey(navigationCategory.getId())) {
                thirdCateList.add(navigationCategory);
            }
        }
        List<NavCategoryDTO> navCateDTOs = new ArrayList<>();
        for (NavigationCategory category : firCateList) {
            if (filterMap.containsKey(category.getId())) {
                NavCategoryDTO navCateDTO = convertToNavCategoryDTO(category);
                List<NavCategoryDTO> children = getCateChild(secCateList, category);
                for (NavCategoryDTO childCategory : children) {
                    if (filterMap.containsKey(childCategory.getId())) {
                        List<NavCategoryDTO> children2 = getCateChild(thirdCateList, childCategory);
                        childCategory.setChild(children2);
                    }
                }
                navCateDTO.setChild(children);
                navCateDTOs.add(navCateDTO);
            }
        }
        return navCateDTOs;
    }

    @Override
    public Map<Long,Long>  getFilterSpuCategory() {
        Map<Long,Long> fiterMap = new HashMap<>();
        List<ProductEntity> productEntityList = productService.selectFilterCategory();
        for (ProductEntity productEntity : productEntityList) {
            Long categoryId = productEntity.getCategoryId();
            // 一级
            if (null != categoryId && categoryId > 0) {
                if (!fiterMap.containsKey(productEntity.getCategoryId())) {
                    fiterMap.put(categoryId,categoryId);
                }
            }

            Long categoryId2 = productEntity.getCategoryId2();
            // 二级
            if (null != categoryId2 && categoryId2 > 0) {
                if (!fiterMap.containsKey(categoryId2)) {
                    fiterMap.put(categoryId2,categoryId2);
                }
            }

            Long categoryId3 = productEntity.getCategoryId3();
            // 三级
            if (null != categoryId3 && categoryId3 > 0) {
                if (!fiterMap.containsKey(categoryId3)) {
                    fiterMap.put(categoryId3,categoryId3);
                }
            }
        }

        List<ProductEntity> productEntityList2 = productService.selectFilterYxCategory();
        for (ProductEntity productEntity : productEntityList2) {
            Long categoryId = productEntity.getCategoryId();
            // 一级
            if (null != categoryId && categoryId > 0) {
                if (!fiterMap.containsKey(productEntity.getCategoryId())) {
                    fiterMap.put(categoryId,categoryId);
                }
            }

            Long categoryId2 = productEntity.getCategoryId2();
            // 二级
            if (null != categoryId2 && categoryId2 > 0) {
                if (!fiterMap.containsKey(categoryId2)) {
                    fiterMap.put(categoryId2,categoryId2);
                }
            }
        }
        return fiterMap;
    }


    @Override
    public List<NavCategoryDTO> getNavigationCategory() {
        return null;
    }

    @Override
    public List<NavBrandDTO> getNavigationBrand() {

        List<NavigationCategory> brandCategoryList = getNavigationCategories();

        if (CollectionUtils.isEmpty(brandCategoryList)) {
            return Collections.emptyList();
        }
        List<NavigationCategoryRange> rangeList = getNavigationCategoryRanges(brandCategoryList);

        List<BrandEntity> brandList = getBrands(rangeList);

        List<NavBrandDTO> navBrandDTOList = new ArrayList<>();
        for (NavigationCategory category : brandCategoryList) {
            NavBrandDTO navBrandDTO = convertToNavBrandDTO(category);

            List<NavBrandSimple> brandSimpleList = getBrands(rangeList, brandList, category);

            navBrandDTO.setBrands(brandSimpleList);

            navBrandDTOList.add(navBrandDTO);
        }
        sortNavBrand(navBrandDTOList);

        return navBrandDTOList;
    }


    private List<NavigationCategory> getNavigationCategories() {
        NavigationCategory query = new NavigationCategory();
        query.setStatus(1);
        query.setType(NavigationType.BRAND.getCode());
        return this.queryByParam(BeanUtil.beanMap(query));
    }

    private List<NavigationCategoryRange> getNavigationCategoryRanges(List<NavigationCategory> brandCategoryList) {
        if (CollectionUtils.isEmpty(brandCategoryList)) {
            return Collections.emptyList();
        }

        List<Long> categoryIds = new ArrayList<>();
        for (NavigationCategory category : brandCategoryList) {
            categoryIds.add(category.getId());
        }
        return null;

//        return navigationCategoryRangeMapper.queryByCategoryIds(categoryIds);
    }

    private List<BrandEntity> getBrands(List<NavigationCategoryRange> rangeList) {
        List<Long> brandIds = new ArrayList<>();
        for (NavigationCategoryRange range : rangeList) {
            if (NumberUtils.isNumber(range.getContent())) {
                brandIds.add(Long.parseLong(range.getContent()));
            }
        }
        if (brandIds.isEmpty()) {
            return Collections.emptyList();
        }

        return brandService.selectListBrand(brandIds, 1);
    }

    private List<NavBrandSimple> getBrands(List<NavigationCategoryRange> rangeList, List<BrandEntity> brandList, NavigationCategory category) {
        List<NavBrandSimple> brandSimpleList = new ArrayList<>();
        for (NavigationCategoryRange range : rangeList) {
            if (range.getCategoryId().equals(category.getId())) {
                if (!NumberUtils.isNumber(range.getContent())) {
                    continue;
                }
                NavBrandSimple bs = new NavBrandSimple();
                bs.setBrandId(Long.parseLong(range.getContent()));
                getBrandInfo(brandList, range,bs);
                bs.setSort(range.getSort());
                brandSimpleList.add(bs);
            }
        }
        sortNavBrandSimple(brandSimpleList);
        return brandSimpleList;
    }

    private void getBrandInfo(List<BrandEntity> brandList, NavigationCategoryRange range, NavBrandSimple bs) {
        for (BrandEntity brand : brandList) {
            if (brand.getId().equals(Long.parseLong(range.getContent()))) {
               // bs.setPic( ImageUtil.getImgFullUrl(Constant.IMAGE_URL_TYPE.basedata, brand.getLogo()));
                bs.setName(brand.getName());
            }
        }
    }

    private List<NavCategoryDTO> getCateChild(List<NavigationCategory> secCateList, NavigationCategory category) {
        List<NavCategoryDTO> children = new ArrayList<>();
        for (NavigationCategory secCate : secCateList) {
            if (secCate.getParentId().equals(category.getId())) {
                NavCategoryDTO child = convertToNavCategoryDTO(secCate);
                children.add(child);
            }
        }
        //sort(children);
        return children;
    }

    private void sort(List<NavCategoryDTO> children) {
        Collections.sort(children, new Comparator<NavCategoryDTO>() {
            @Override
            public int compare(NavCategoryDTO o1, NavCategoryDTO o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
    }

    private void sortNavBrand(List<NavBrandDTO> children) {
        Collections.sort(children, new Comparator<NavBrandDTO>() {
            @Override
            public int compare(NavBrandDTO o1, NavBrandDTO o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
    }

    private void sortNavBrandSimple(List<NavBrandSimple> children) {
        Collections.sort(children, new Comparator<NavBrandSimple>() {
            @Override
            public int compare(NavBrandSimple o1, NavBrandSimple o2) {
                int res = o1.getSort().compareTo(o2.getSort());
                return res;
            }
        });
    }

    private NavCategoryDTO convertToNavCategoryDTO(NavigationCategory category) {
        NavCategoryDTO dto = new NavCategoryDTO();
        dto.setLevel(category.getLevel());
        dto.setStatus(category.getStatus());
        dto.setParentId(category.getParentId());
        dto.setId(category.getId());
        dto.setIsHighlight(category.getIsHighlight());
        dto.setCode(category.getCode());
        dto.setSort(category.getSort());
        dto.setPic(ImageUtil.getImgFullUrl(Constant.IMAGE_URL_TYPE.basedata, category.getPic()));
        dto.setName(category.getName());
        dto.setNameEn(category.getNameEn());
        dto.setIsPublish(category.getIsPublish());


        return dto;
    }

    private NavBrandDTO convertToNavBrandDTO(NavigationCategory category) {
        NavBrandDTO dto = new NavBrandDTO();
        dto.setLevel(category.getLevel());
        dto.setStatus(category.getStatus());
        dto.setParentId(category.getParentId());
        dto.setId(category.getId());
        dto.setIsHighlight(category.getIsHighlight());
        dto.setCode(category.getCode());
        dto.setSort(category.getSort());
        dto.setPic(ImageUtil.getImgFullUrl(Constant.IMAGE_URL_TYPE.basedata, category.getPic()));
        dto.setName(category.getName());
        dto.setIsPublish(category.getIsPublish());

        return dto;
    }

    private  List<NavigationCategory> queryByParam(Map<String,Object> params) {
        List<CategoryEntity> list = categoryMapper.queryByParam(params);
        return list.stream().map(x -> Beans.convert(x, NavigationCategory.class)).collect(toList());
    }
}
