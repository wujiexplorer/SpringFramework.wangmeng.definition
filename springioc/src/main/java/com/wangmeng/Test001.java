package com.wangmeng;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Test001 {

    public static void main(String[] args)throws Exception{
        new Test001().test001();
    }

    public void test001()throws Exception{
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourceAaStream("student.xml"));
        Element rootElement = document.getRootElement();
        getNodes(rootElement);

    }

    public void getNodes(Element rootElement){
        System.out.println("获取节点名称："+rootElement.getName());
        List<Attribute> attributes = rootElement.attributes();
        for(Attribute attribute : attributes){
            System.out.println(attribute.getName()+"-------------"+attribute.getText());
        }
        String textTrim = rootElement.getTextTrim();
        if(StringUtils.isNotEmpty(textTrim)){
            System.out.println("textTrim:"+textTrim);
        }
        Iterator<Element> elementIterable = rootElement.elementIterator();
        while (elementIterable.hasNext()){
            Element next = elementIterable.next();
            getNodes(next);
        }

    }

    public InputStream getResourceAaStream(String xmlPath){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);//获取项目路径
        return inputStream;
    }
}
