package com.lx.benefits.task;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.constants.Constant;
import com.lx.benefits.bean.constants.RedisCacheKeyConstant;
import com.lx.benefits.bean.dto.admin.customized.GoodsModuleInfoDto;
import com.lx.benefits.bean.dto.sch.Nav;
import com.lx.benefits.bean.dto.sch.NavCategoryDTO;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfoCondition;
import com.lx.benefits.bean.entity.product.CategoryEntity;
import com.lx.benefits.bean.util.BeanUtil;
import com.lx.benefits.bean.util.Beans;
import com.lx.benefits.bean.util.ImageUtil;
import com.lx.benefits.bean.vo.navigation.NavigationCategory;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.enterprcustomgoods.EnterprCustomGoodsService;
import com.lx.benefits.service.navigation.NavigationCategoryService;
import com.lx.benefits.service.product.CategoryService;
import com.lx.benefits.service.product.ProductService;
import com.lx.benefits.utils.RedisUtils;
import com.lx.benefits.web.controller.admin.AccountController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import static java.util.stream.Collectors.toList;

/**
 * Created by softw on 2019/3/15.
 */
@Component
public class CategroyTask {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductService productService;

    @Resource
    private EnterprCustomGoodsService enterprCustomGoodsService;

    @Resource
    private EnterprUserInfoMapper enterprUserInfoMapper;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private NavigationCategoryService navigationCategoryService;

    /**
     * 商品分类检测（每天零点执行）
     */
    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(cron = "0 0/3 *  * * ?")
    public void categoryCache() {
        logger.info("商品分类过滤，任务开始时间：" + sdf.format(new Date()));
        Map<Long,Long> filterMap = navigationCategoryService.getFilterSpuCategory();
        // 查询企业过滤类目
        EnterprUserInfoCondition example = new EnterprUserInfoCondition();
        example.setOffset(0);
        example.setLimit(10000);
        List<EnterprUserInfo> enterprUserInfoList = enterprUserInfoMapper.selectByExample(example);
        CountDownLatch countDownLatch = new CountDownLatch(15);
        for (EnterprUserInfo enterprUserInfo : enterprUserInfoList) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> setCacheCagetory(countDownLatch,enterprUserInfo.getEnterprId(),filterMap),threadPoolTaskExecutor);
        }
        logger.info("商品分类过滤，任务完成时间：" + sdf.format(new Date()));
    }

    private String setCacheCagetory(CountDownLatch countDownLatch ,Long enterprId,Map<Long,Long> filterMap) {
        try {
            redisUtils.delete(enterprId+"_"+ RedisCacheKeyConstant.NAV_CACHE_KEY);
            // 分类无上架商品
            NavigationCategory query = new NavigationCategory();
            query.setStatus(1);
            query.setParentId(0L);
            // 过滤分类

            GoodsModuleInfoDto goodsModuleInfoDto = enterprCustomGoodsService.findByIdWithAgent(enterprId);
            if (null != goodsModuleInfoDto)  {
                List<Long> enterCategroyList = goodsModuleInfoDto.getCategoryIdsList();
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
            redisUtils.set(enterprId+"_"+ RedisCacheKeyConstant.NAV_CACHE_KEY, nav,2*60*60);
            logger.info("企业{},分类{}" ,enterprId, JSON.toJSONString(nav));
        } catch (Exception e) {
            logger.error("过滤商品分类异常{}",e.getMessage());
        }
        return "";
    }

    private  List<NavigationCategory> queryNavByParam(Map<String,Object> params) {
        List<CategoryEntity> list = categoryService.queryByParam(params);
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


