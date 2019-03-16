package com.wangmeng.config;

import com.wangmeng.bean.Person;
import com.wangmeng.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//配置类==配置文件
@Configuration   //告诉Spring只是一个配置类
//@ComponentScan(value="com.wangmeng",includeFilters =  {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})
//},useDefaultFilters = false)
@ComponentScans(
        value = {
                @ComponentScan(value="com.wangmeng",includeFilters =  {
//                        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
//                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),
                        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
                },useDefaultFilters = false)
        }
)
//@ComponentScan value:指定要扫描的包
//excludeFilter = Filter[],指定扫描的时候按照什么规则排除那些组件
//includeFilter = Filter[],指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION,按照注解
//FilterType.ASSIGNABLE_TYPE,按照给定的类型
//FilterType.ASPECTJ,使用ASPECTJ表达式
//FilterType.REGEX,使用正则指定
//FilterType.CUSTOM,使用自定义规则,自定义类，接口类，实现类都能获取到
//includeFilter,excludeFilter是针对于显示的标明@Controller,@Service,@Repository,@Component注解
//的Bean，@ComponentScan则是扫面指定包下除了上述的注解，还包括实现类，接口等.
public class MainConfig {

    //给容器注册一个Bean;类型为返回值类型,id默认用方法名作为id
    @Bean("person")//优先级高些
    public Person person01(){
        return new Person("lisi",20);
    }
}
