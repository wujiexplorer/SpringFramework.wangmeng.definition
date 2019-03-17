package com.wangmeng.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {

    //返回值，就是导入到容器中的组件全类名
    //importingClassMetadata
    //AnnotationMetadata：当前标注@Import注解的类的所有注解信息
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //方法不要返回null值
        return new String[]{"com.wangmeng.bean.Blue","com.wangmeng.bean.Yellow"};
    }
}
