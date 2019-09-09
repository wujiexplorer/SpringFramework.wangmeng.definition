package com.lx.benefits.bean.dto.yx.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ldr on 2017/6/5.
 */
public class ListUtil {

    public static <T> List<List<T>> separateList(List<T> list, Integer limit) {
        if (list == null || list.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        if (limit == null || limit < 1) {
            throw new IllegalArgumentException("limit less than 1");
        }

        int times = list.size() / limit;
        if (list.size() % limit > 0) {
            times++;
        }
        List<List<T>> res = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            int start = i * limit;
            int end = start + limit;
            if (end > list.size()) {
                end = list.size();
            }
            List<T> sub = list.subList(start, end);
            res.add(sub);
        }
        return res;
    }


    public static <T> String getString(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                continue;
            }
            stringBuilder.append(String.valueOf(list.get(i)));
            if (i < list.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }


}
