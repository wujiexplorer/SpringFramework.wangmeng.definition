package com.lx.benefits.bean.vo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User:wangmeng
 * Date:2019/7/12
 * Time:14:04
 * Version:2.x
 * Description:TODO
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOverviewVO {

    private Integer saleAmount;

    private Integer salePoint;

    private Integer hours;
}
