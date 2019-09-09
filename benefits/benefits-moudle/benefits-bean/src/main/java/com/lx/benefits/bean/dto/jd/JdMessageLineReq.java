package com.lx.benefits.bean.dto.jd;

import com.lx.benefits.bean.dto.jd.annotation.Id;
import lombok.Data;

/**
 * User: fan
 * Date: 2019/03/17
 * Time: 23:07
 */
@Data
public class JdMessageLineReq {

    @Id
    private Long id;

    /**
     * 数据类型bigint(20)
     */
    private Long messageId;

    /**
     * 数据类型int(255)
     * @see com.lx.benefits.bean.enums.jdapi.JDMessageType
     */
    private Integer messageType;

    private Integer status;

    private Integer page = 1;

    private Integer pageSize = 10;
}
