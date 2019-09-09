package com.lx.benefits.bean.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * User: fan
 * Date: 2019/02/20
 * Time: 18:45
 */
public class BeansUtils {

    private static final Logger log = LoggerFactory.getLogger(BeansUtils.class);

    public static <T> T copyProperties(Object source, Class<T> t) {
        try {
            if (source != null) {
                T t1 = t.newInstance();
                BeanUtils.copyProperties(source, t1);
                return t1;
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public static <T> List<T> copyArrayProperties(List<?> source, Class<T> t) {
        try {
            if (source != null && source.size() > 0) {
                return source.stream().map(x -> Beans.convert(x, t)).collect(toList());
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

}
