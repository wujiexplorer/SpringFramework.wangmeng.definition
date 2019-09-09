package com.lx.benefits.bean.dto.sch.result;

import java.util.List;

/**
 * Created by ldr on 2016/2/17.
 */
public class ItemSearchResultDetail extends SearchResultDetailBase {

    private static final long serialVersionUID = 1055724736843191829L;

    private List<ItemResult> items;

    public List<ItemResult> getItems() {
        return items;
    }

    public void setItems(List<ItemResult> items) {
        this.items = items;
    }
}
