package com.wangmeng.ext.springmvc;

import com.wangmeng.spring.extannotation.ExtResource;
import com.wangmeng.spring.extannotation.ExtService;
import com.wangmeng.orm.utils.ClassUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtClassPathXmlApplicationContext {

    private String packageName;

    private ConcurrentHashMap<String,Object> beans = null;

    public ExtClassPathXmlApplicationContext(String packageName)throws Exception{
        this.packageName = packageName;
        beans = new ConcurrentHashMap<String, Object>();
        initBeans();
        initEntryField();
    }

    private void initEntryField()throws  Exception{
        for(Map.Entry<String,Object> entry : beans.entrySet()){
            Object bean = entry.getValue();
            attriAssign(bean);
        }
    }

    public Object getBean(String beanId)throws Exception{
        if(StringUtils.isEmpty(beanId)){
            throw new Exception("beanId参数不能为空");
        }
        Object object = beans.get(beanId);
        //attriAssign(object);
//        if(null == object){
//            throw new Exception("没有找到该bean对象");
//        }
        return object;
    }


    public Object newInstance(Class<?> classInfo)throws Exception{
        return classInfo.newInstance();
    }

    public void initBeans()throws Exception{
        List<Class<?>> classes = ClassUtils.getClasses(packageName);
        ConcurrentHashMap<String, Object> classExistAnnotation = findClassExistAnnotation(classes);
        if(null == classExistAnnotation || classExistAnnotation.isEmpty()){
            throw new Exception("该包下没有任何类加上注解");
        }
    }

    public ConcurrentHashMap<String,Object> findClassExistAnnotation( List<Class<?>> classes)throws Exception{
        for(Class<?> classInfo : classes){
            ExtService annotation = classInfo.getAnnotation(ExtService.class);
            if(null != annotation){
                String className = classInfo.getSimpleName();
                String beanId = toLowerCaseFirstOne(className);
                Object newInstance = newInstance(classInfo);
                beans.put(beanId,newInstance);
                //continue;
            }
        }
        return beans;
    }

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public void attriAssign(Object object)throws Exception{
        Class<?> classInfo = object.getClass();
        Field[] declaredFields = classInfo.getDeclaredFields();
        for(Field field : declaredFields){
            ExtResource extResource = field.getAnnotation(ExtResource.class);
            if( null != extResource){
                String beanId = field.getName();
                Object bean = getBean(beanId);
                if(object != null){
                    field.setAccessible(true);//允许访问私有属性
                    field.set(object,bean);
                }
            }
        }

    }

}
