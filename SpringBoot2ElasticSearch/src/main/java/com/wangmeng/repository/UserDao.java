package com.wangmeng.repository;

import com.wangmeng.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/21
 * TIME 0:32
 * Description no Description
 **/
public interface UserDao extends CrudRepository<UserEntity,String> {
}
