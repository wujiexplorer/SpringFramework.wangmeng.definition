package com.wangmeng.xml.spring;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class ExtClassPathXmlApplicationContext {

    private String xmlPath;

    public ExtClassPathXmlApplicationContext(String xmlPath){
        this.xmlPath = xmlPath;
    }

    public Object getBean(String beanId)throws Exception{
        if(StringUtils.isEmpty(beanId)){
            throw new Exception("beanId不能为空！");
        }
        List<Element> readXML = readXML();
        if(null == readXML || readXML.isEmpty()){
            throw new Exception("配置文件中，没有配置bean信息");
        }
        String className = findByElementClass(readXML,beanId);
        if(StringUtils.isEmpty(className)){
            throw new Exception("该bean对象没有配置class地址");
        }

        return newInstance(className);
    }

    public String findByElementClass(List<Element> readXML,String beanId){
        for(Element element :readXML){
            String xmlBeanId = element.attributeValue("id");
            if(StringUtils.isEmpty(xmlBeanId)){
                continue;
            }
            if(xmlBeanId.equals(beanId)){
                String xmlClass = element.attributeValue("class");
                //Object newInstance = newInstance(xmlClass);
                return xmlClass;
                // break;

            }
        }
        return null;
    }

    public Object newInstance(String className)throws Exception{
        Class<?> classInfo = Class.forName(className);
        return classInfo.newInstance();
    }

    public List<Element> readXML()throws Exception{
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourceAaStream(xmlPath));
        Element element = document.getRootElement();
        List<Element> elements = element.elements();
        return elements;
    }

    public InputStream getResourceAaStream(String xmlPath){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);//获取项目路径
        return inputStream;
    }

    public static void main(String[] args) {

    }
}
