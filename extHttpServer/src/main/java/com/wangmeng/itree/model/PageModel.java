package com.wangmeng.itree.model;

import com.wangmeng.itree.common.constant.RequestConstants;
import com.wangmeng.itree.common.em.PageMappingEnum;
import lombok.Data;

/**
 * @author idea
 * @data 2019/4/27
 */
@Data
public class PageModel {

    private int code;

    private String pagePath;

    public PageModel() {
        code = RequestConstants.INDEX_CODE;
        pagePath = PageMappingEnum.getPath(RequestConstants.INDEX_CODE);
    }

    public PageModel(String pagePath) {
        this.code = RequestConstants.SUCCESS_CODE;
        this.pagePath = pagePath;
    }


}
