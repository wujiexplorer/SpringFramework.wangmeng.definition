package com.lx.benefits.bean.enums.yianapi.util;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lx.benefits.bean.dto.jd.utils.MD5Util;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lidongri on 2018/12/4.
 */
public class ClientSignUtil {

    public static String getSign(Object o, String clientSecret) throws NoSuchFieldException, IllegalAccessException {

        List<Field> fields = new ArrayList<>();

        fields.addAll(Arrays.asList(o.getClass().getDeclaredFields()));

        Class c = o.getClass().getSuperclass();
        while ( c!= Object.class){
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
            c = c.getSuperclass();
        }

        for(Field f: fields){
            if(f.getName().equals("timestamp") || f.getName().equals("sign")){
                f.setAccessible(true);
                f.set(o,null);
            }
        }
        String baseJson = JsonUtil.toString(o, SerializerFeature.MapSortField, SerializerFeature.IgnoreNonFieldGetter);
        String toSign = baseJson + clientSecret;
        System.out.println(toSign);
        return MD5Util.MD5(toSign);
    }
}
