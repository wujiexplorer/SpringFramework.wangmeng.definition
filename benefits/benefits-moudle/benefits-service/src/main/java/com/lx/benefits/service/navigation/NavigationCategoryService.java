package com.lx.benefits.service.navigation;

import com.lx.benefits.bean.dto.sch.Nav;
import com.lx.benefits.bean.dto.sch.NavBrandDTO;
import com.lx.benefits.bean.dto.sch.NavCategoryDTO;

import java.util.List;
import java.util.Map;


public interface NavigationCategoryService {


    /**
     * 获取导航的分类信息
     * @return
     */
    List<NavCategoryDTO> getNavigationCategory();

    /**
     * 获取导航的品牌信息
     * @return
     */
    List<NavBrandDTO> getNavigationBrand();

    /**
     * 获取分类导航信息
     * @return
     */
    Nav getNav();

    Map<Long,Long> getFilterSpuCategory();

}
