package com.lx.benefits.bean.entity.seckill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User:wangmeng
 * Date:2019/8/23
 * Time:10:58
 * Version:2.x
 * Description:TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillOrder {
    private Long number;

    private Date createTime;

    private Date payTime;

    private String employeeName;

    private Long enterprId;

    private String enterprName;
}
