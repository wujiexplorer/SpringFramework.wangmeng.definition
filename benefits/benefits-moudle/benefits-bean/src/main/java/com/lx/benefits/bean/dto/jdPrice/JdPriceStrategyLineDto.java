package com.lx.benefits.bean.dto.jdPrice;

import lombok.Data;

/**
 * User: fan
 * Date: 2019/03/04
 * Time: 23:32
 */
@Data
public class JdPriceStrategyLineDto {

    private Long id;

    private Double frome;

    private Double toe;

    private Double priceRate;

    private Byte offline;

}
