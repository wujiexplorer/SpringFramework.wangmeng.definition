package com.wangmeng.itree.core.handle.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author idea
 * @date 2019/4/26
 * @Version V1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseEnum {

    NO_PATH_MAPPING(404,"路径搜索不到");

    private Integer code;

    private String des;


}
