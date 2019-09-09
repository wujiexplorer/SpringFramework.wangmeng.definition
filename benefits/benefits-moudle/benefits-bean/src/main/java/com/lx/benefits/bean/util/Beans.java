package com.lx.benefits.bean.util;

import org.springframework.beans.BeanUtils;


public class Beans {

    public static <S, T> T convert(S source, Class<T> targetClass) {
        if (source == null) return null;
        T target = null;
        try {
            target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (InstantiationException | IllegalAccessException e) {
        }
        return target;
    }

}
