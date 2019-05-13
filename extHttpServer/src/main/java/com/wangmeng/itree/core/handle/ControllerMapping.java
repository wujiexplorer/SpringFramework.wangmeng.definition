package com.wangmeng.itree.core.handle;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author idea
 * @date 2019/4/26
 * @Version V1.0
 */
@Data
@AllArgsConstructor
public class ControllerMapping {

    private String url;

    private String clazz;

}
