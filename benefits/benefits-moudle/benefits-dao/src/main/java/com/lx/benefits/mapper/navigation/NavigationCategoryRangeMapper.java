package com.lx.benefits.mapper.navigation;

import com.lx.benefits.bean.dto.base.NavigationCategoryRange;

import java.util.List;

public interface NavigationCategoryRangeMapper {

    List<NavigationCategoryRange> queryByCategoryIds(List<Long> categoryIds);
}
