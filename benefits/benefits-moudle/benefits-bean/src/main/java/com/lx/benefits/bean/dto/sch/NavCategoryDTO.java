package com.lx.benefits.bean.dto.sch;


import com.lx.benefits.bean.vo.navigation.NavigationCategory;

import java.util.List;

/**
 * Created by ldr on 2016/2/25.Ω
 */
public class NavCategoryDTO extends NavigationCategory {

    private static final long serialVersionUID = 2560423313957929703L;

    private List<NavCategoryDTO> child;

    public List<NavCategoryDTO> getChild() {
        return child;
    }

    public void setChild(List<NavCategoryDTO> child) {
        this.child = child;
    }
}
