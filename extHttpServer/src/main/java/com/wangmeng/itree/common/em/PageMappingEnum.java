package com.wangmeng.itree.common.em;

import com.wangmeng.itree.common.config.ITreeConfig;
import lombok.Getter;


/**
 * @author idea
 * @data 2019/4/27
 */
@Getter
public enum PageMappingEnum {

    INDEX_PAGE(200, ITreeConfig.INDEX_PAGE),
    NOT_FOUND_PAGE(404, ITreeConfig.NOT_FOUND_PAGE),
    UNKOWN_EXCEPTION_PAGE(500, ITreeConfig.UNKOWN_EXCEPTION_PAGE);

    private int code;

    private String path;

    PageMappingEnum(int code, String path) {
        this.code = code;
        this.path = path;
    }

    public static String getPath(int code) {
        for (PageMappingEnum pageMappingEnum : PageMappingEnum.values()) {
            if (pageMappingEnum.getCode() == code) {
                return pageMappingEnum.getPath();
            }
        }
        return null;
    }
}
