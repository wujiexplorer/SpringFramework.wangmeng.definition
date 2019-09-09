package com.lx.benefits.bean.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: User
* @Description:
* @author wind
* @date 2019-2-11
*/
@Data
public class User  implements Serializable{
	
    /**
     *主键
     */
	private Long id;
    /**
     *用户名
     */
	private String username;
    /**
     *密码
     */
	private String password;
    /**
     *手机号
     */
	private String mobile;
    /**
     *用户昵称
     */
	private String nickName;
    /**
     *出生日期
     */
	private Date birthday;
    /**
     *用户真实姓名
     */
	private String name;
    /**
     *身份证号
     */
	private String idcardNum;
    /**
     *用户状态
     */
	private Integer status;
    /**
     * 登录ip
     */
	private String ip;
    /**
     * 是否web页面登录进来
     */
	private boolean fromWeb;
    /**
     *删除状态 0：未删除；1：已删除
     */
	private Integer isDelete;
    /**
     *创建时间
     */
	private Date createTime;
    /**
     *更新时间
     */
	private Date updateTime;


}
