package com.lx.benefits.bean.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: UserCompany
* @Description:
* @author wind
* @date 2019-2-11
*/
@Data
public class UserCompany  implements Serializable{
	
    /**
     *主键
     */
	private Long id;
    /**
     *用户id
     */
	private Long userId;
    /**
     *企业ID
     */
	private Long companyId;
    /**
     *是否默认 0：否，1：是
     */
	private Integer isDefault;
    /**
     *是否删除 0：未删除，1：已删除
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
