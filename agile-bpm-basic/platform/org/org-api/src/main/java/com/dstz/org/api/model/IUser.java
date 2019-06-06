package com.dstz.org.api.model;

import java.io.Serializable;
import java.util.Map;

import com.dstz.base.api.model.IBaseModel;

/**
 * 描述：用户实体接口
 */
public interface IUser extends Serializable{
    /**
     * 男性=Male
     */
    public static final String SEX_MALE = "Male";
    /**
     * 女性=Female
     */
    public static final String SEX_FAMALE = "Female";

    /**
     * 用户标识Id
     *
     * @return String
     */
    String getUserId();

    void setUserId(String userId);

    /**
     * 用户姓名
     *
     * @return String
     */
    String getFullname();

    void setFullname(String fullName);

    /**
     * 用户账号
     *
     * @return String
     */
    String getAccount();

    void setAccount(String account);

    /**
     * 获取密码
     *
     * @return String
     */
    String getPassword();

    /**
     * 邮件。
     *
     * @return String
     */
    String getEmail();

    /**
     * 手机。
     *
     * @return String
     */
    String getMobile();

    String getWeixin();
    
    Integer getStatus();

}
