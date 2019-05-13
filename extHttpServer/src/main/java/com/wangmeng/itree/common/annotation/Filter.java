package com.wangmeng.itree.common.annotation;

import java.lang.annotation.*;

/**
 * @author linhao
 * @date 2019/4/30
 * @Version V1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Filter {

    String name() default "";

    int order() default 0;
}
