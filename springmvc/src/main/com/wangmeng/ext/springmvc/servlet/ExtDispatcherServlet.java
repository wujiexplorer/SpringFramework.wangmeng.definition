package com.wangmeng.ext.springmvc.servlet;

import com.wangmeng.ext.springmvc.extannotation.ExtController;
import com.wangmeng.ext.springmvc.extannotation.ExtRequestMapping;
import com.wangmeng.orm.utils.ClassUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtDispatcherServlet extends HttpServlet {

    private ConcurrentHashMap<String,Object> springmvcBeans = new ConcurrentHashMap<String,Object>();

    private ConcurrentHashMap<String,Object>  urlBeans = new ConcurrentHashMap<String,Object>();

    private ConcurrentHashMap<String,String>  urlMethods = new ConcurrentHashMap<String,String>();

    @Override
    public void init() throws ServletException {
        List<Class<?>> classes = ClassUtils.getClasses("com.wangmeng.controller");
        try{
            findClassMVCAnnotation(classes);
        }catch (Exception e){
            e.getMessage();
        }
        handleMapping();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        if(StringUtils.isEmpty(requestURI)){
            return;
        }
        Object object = urlBeans.get(requestURI);
        if(object == null){
            resp.getWriter().write("not found 404 url");
            return;
        }
        String methodName = urlMethods.get(requestURI);
        if(StringUtils.isEmpty(methodName)){
            resp.getWriter().write("not found method");
            return;
        }
        String resultPage = (String)methodInvoke(object, methodName);
        //resp.getWriter().write(resultPage);
        try {
            extResourceViewResolver(resultPage,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void extResourceViewResolver(String pageName,HttpServletRequest req,HttpServletResponse res)throws Exception{
        String prefix = "/";
        String suffix = ".jsp";
        req.getRequestDispatcher(prefix+pageName+suffix).forward(req,res);
    }

    private Object methodInvoke(Object object,String methodName){
        try{
            Class<?> classInfo = object.getClass();
            Method method = classInfo.getMethod(methodName);
            Object result = method.invoke(object);
            return result;
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }

    public void findClassMVCAnnotation(List<Class<?>> classes)throws Exception{
        for(Class<?> classInfo : classes){
            ExtController extController = classInfo.getDeclaredAnnotation(ExtController.class);
            if(extController != null){
                String beanId = ClassUtils.toLowerCaseFirstOne(classInfo.getSimpleName());
                Object object = ClassUtils.newInstance(classInfo);
                springmvcBeans.put(beanId,object);
            }
        }
    }

    public void handleMapping(){
        for(Map.Entry<String,Object> mvcBean : springmvcBeans.entrySet()){
            Object object = mvcBean.getValue();
            Class<?> classInfo = object.getClass();
            ExtRequestMapping extRequestMapping = classInfo.getDeclaredAnnotation(ExtRequestMapping.class);
            String baseUrl = "";
            if(extRequestMapping != null){
               baseUrl= extRequestMapping.value();
            }
            Method[] declaredMethods = classInfo.getDeclaredMethods();
            for(Method method : declaredMethods){
                ExtRequestMapping methodExtRequestMapping = method.getDeclaredAnnotation(ExtRequestMapping.class);
                if(methodExtRequestMapping != null){
                    String methodUrl = baseUrl + methodExtRequestMapping.value();
                    urlBeans.put(methodUrl,object);
                    urlMethods.put(methodUrl,method.getName());
                }
            }
        }
    }
}
