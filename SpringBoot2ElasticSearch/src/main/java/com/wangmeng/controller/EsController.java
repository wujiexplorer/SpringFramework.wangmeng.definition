package com.wangmeng.controller;

import com.wangmeng.entity.UserEntity;
import com.wangmeng.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/21
 * TIME 0:34
 * Description no Description
 **/
@RestController
public class EsController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity userEntity){
        return userDao.save(userEntity);
    }

    @RequestMapping("/findById")
    public Optional<UserEntity> findById(String id){
        Optional<UserEntity> userEntity = userDao.findById(id);
        return userEntity;
    }
}
