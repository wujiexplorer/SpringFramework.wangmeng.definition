package com.lx.benefits.mapper.navigation;

import com.lx.benefits.bean.vo.navigation.NavigationCategory;

import java.util.List;
import java.util.Map;

public interface NavigationCategoryMapper {

    List<NavigationCategory> queryByParam(Map<String,Object> params);
}
