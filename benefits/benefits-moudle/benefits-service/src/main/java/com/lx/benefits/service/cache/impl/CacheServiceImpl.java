package com.lx.benefits.service.cache.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lx.benefits.bean.constants.Constant;
import com.lx.benefits.bean.constants.RedisCacheKeyConstant;
import com.lx.benefits.bean.dto.sch.Nav;
import com.lx.benefits.bean.dto.sch.NavCategoryDTO;
import com.lx.benefits.bean.entity.enterprcustomgoods.EnterprCustomGoods;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.util.BeanUtil;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.ImageUtil;
import com.lx.benefits.bean.vo.navigation.NavigationCategory;
import com.lx.benefits.mapper.enterprcustomgoods.EnterprCustomGoodsMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.mapper.product.CategoryMapper;
import com.lx.benefits.service.cache.CacheService;
import com.lx.benefits.service.navigation.NavigationCategoryService;
import com.lx.benefits.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import static java.util.stream.Collectors.toList;

/**
 * Created by softw on 2019/3/22.
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;

    @Autowired
    private EnterprCustomGoodsMapper enterprCustomGoodsMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisUtils redisUtils;

    private final static Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private NavigationCategoryService navigationCategoryService;

    @Override
    public void delNavCache() {
        logger.info("清除商品类目缓存---start");
        EnterprUserInfoCondition example = new EnterprUserInfoCondition();
        example.setOffset(0);
        example.setLimit(10000);
        List<EnterprUserInfo> enterprUserInfoList = enterprUserInfoMapper.selectByExample(example);
        for (EnterprUserInfo enterprUserInfo : enterprUserInfoList) {
            redisUtils.delete(enterprUserInfo.getEnterprId()+"_"+ RedisCacheKeyConstant.NAV_CACHE_KEY);
        }
        logger.info("清除商品类目缓存---end");
    }
    
    @Override
    public  String setCacheCagetory(CountDownLatch countDownLatch , Long enterprId, Map<Long,Long> filterMap) {
        try {
            redisUtils.delete(enterprId+"_"+ RedisCacheKeyConstant.NAV_CACHE_KEY);
            // 分类无上架商品
            NavigationCategory query = new NavigationCategory();
            query.setStatus(1);
            query.setParentId(0L);
            // 过滤分类
            EnterprCustomGoods enterprCustomGoods = enterprCustomGoodsMapper.selectByEnterprId(enterprId);
            if (null != enterprCustomGoods)  {
                List<Long> enterCategroyList = JSONArray.parseArray(enterprCustomGoods.getCategoryIdsList(),Long.class);
                if (enterCategroyList != null && enterCategroyList.size() > 0) {
                    query.setCategoryIdsList(enterCategroyList);
                }
            }
            List<NavigationCategory> firCateListAll = this.queryNavByParam(BeanUtil.beanMap(query));
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
            List<NavigationCategory> secCateListAll = this.queryNavByParam(BeanUtil.beanMap(query));
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
            List<NavigationCategory> thirdCateListAll = this.queryNavByParam(BeanUtil.beanMap(query));
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

            Nav nav = new Nav(navCateDTOs, null);
            redisUtils.set(enterprId+"_"+ RedisCacheKeyConstant.NAV_CACHE_KEY, nav);
            logger.info("企业{},分类{}" ,enterprId, JSON.toJSONString(nav));
        } catch (Exception e) {
            logger.error("过滤商品分类异常{}",e.getMessage());
        }
        return "";
    }

    private  List<NavigationCategory> queryNavByParam(Map<String,Object> params) {
        List<CategoryEntity> list = categoryMapper.queryByParam(params);
        return list.stream().map(x -> Beans.convert(x, NavigationCategory.class)).collect(toList());
    }

    private NavCategoryDTO convertToNavCategoryDTO(NavigationCategory category) {
        NavCategoryDTO dto = new NavCategoryDTO();
        dto.setLevel(category.getLevel());
        dto.setParentId(category.getParentId());
        dto.setStatus(category.getStatus());
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

    private List<NavCategoryDTO> getCateChild(List<NavigationCategory> secCateList, NavigationCategory category) {
        List<NavCategoryDTO> children = new ArrayList<>();
        for (NavigationCategory secCate : secCateList) {
            if (secCate.getParentId().equals(category.getId())) {
                NavCategoryDTO child = convertToNavCategoryDTO(secCate);
                children.add(child);
            }
        }
        return children;
    }
}
