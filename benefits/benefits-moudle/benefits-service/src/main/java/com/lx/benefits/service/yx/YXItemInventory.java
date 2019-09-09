package com.lx.benefits.service.yx;

import com.lx.benefits.bean.dto.yx.YXItemInventoryDto;
import com.lx.benefits.bean.dto.yx.result.YXResult;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/27
 * Time: 17:22
 */
public interface YXItemInventory {

    YXResult<List<YXItemInventoryDto>> inventory(List<Long> skuIds);

    YXResult< YXItemInventoryDto> inventory(Long skuId);

    YXResult testCallback();


}
