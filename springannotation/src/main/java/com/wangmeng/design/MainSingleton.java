package com.wangmeng.design;

import com.wangmeng.bean.Blue;
import com.wangmeng.bean.Boss;
import com.wangmeng.bean.Red;
import com.wangmeng.bean.Yellow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainSingleton {

    private static final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    static{
        singletonObjects.put("wangmeng",new Yellow("yellow"));
    }

    //本类的静态方法只能调用本类的静态方法，但是可以调用其他类的实例方法
    public static Object getSingletion(String beanName,ObjectFactoryTest<?> singletonFactory)throws Exception{
            Object object = singletonObjects.get(beanName);
            if(object == null){
                object =singletonFactory.getObject();
            }
            return object;
    }

    public static void main(String[] args)throws Exception{
        //调用的时候，实现接口方法
        Object object =getSingletion("wangmeng1", new ObjectFactoryTest<Object>() {
            public  Object getObject(){
                return new Blue("blue");
            }
        });
        System.out.println(object);
    }
}
