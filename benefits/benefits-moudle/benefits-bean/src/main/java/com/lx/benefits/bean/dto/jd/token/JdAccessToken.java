package com.lx.benefits.bean.dto.jd.token;

import com.lx.benefits.bean.dto.jd.annotation.Id;
import com.lx.benefits.bean.dto.jd.base.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author szy
 */
@Data
public class JdAccessToken extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1488788220981L;

    /**
     * 数据类型bigint(20)
     */
    @Id
    private Long id;

    /**
     * 数据类型varchar(128)
     */
    private String accessToken;

    /**
     * 数据类型varchar(128)
     */
    private String refreshToken;

    /**
     * 数据类型datetime
     */
    private Date getTime;

    /**
     * 数据类型bigint(20)
     */
    private Long expiresIn;

    /**
     * 数据类型bigint(20)
     */
    private Long refreshTokenExpires;

    /**
     * 数据类型varchar(32)
     */
    private String uid;

    /**
     * 数据类型datetime
     */
    private Date createTime;

    /**
     * 数据类型datetime
     */
    private Date updateTime;

    /**
     * 数据类型varchar(32)
     */
    private String createUser;

    /**
     * 数据类型varchar(32)
     */
    private String updateUser;

    /**
     * 数据类型varchar(32)
     */
    private String remark;

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

}
