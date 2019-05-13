package com.wangmeng.itree.common.annotation;

import java.lang.annotation.*;

/**
 * 主要提供用于做url判断
 *
 * @author idea
 * @date 2019/4/28
 * @Version V1.0
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ControllerMapping {

     String url() default "";

}
