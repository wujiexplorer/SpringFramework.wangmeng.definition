package com.wangmeng.jwt.model;

/**
 * @program: users
 * @author: wangmeng
 * @create: 2018-03-02 10:44
 * Roles：
 * ADMIN & MEMBER
 **/
public enum Role {
    ADMIN, MEMBER;

    public String authority(){
        return "ROLE_" + this.name();
    }

    @Override
    public String toString(){
        return this.name();
    }
}
