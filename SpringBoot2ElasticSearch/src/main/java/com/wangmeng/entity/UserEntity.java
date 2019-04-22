package com.wangmeng.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/20
 * TIME 23:42
 * Description no Description
 **/
@Document(indexName = "wangmeng",type = "user")
@Data
public class UserEntity {

    /**
     * 行号id一般都为String
     */
    @Id
    private String id;
    private String nane;
    private Integer age;
    private Integer sex;
}
