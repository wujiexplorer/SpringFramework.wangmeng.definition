package com.lx.benefits.service.enterprcustomprice;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.PageBean;
import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.dto.admin.customized.PriceDetailResDto;
import com.lx.benefits.bean.dto.admin.customized.PriceModuleInfoDto;
import com.lx.benefits.bean.dto.admin.customized.PriceQueryReqDto;
import com.lx.benefits.bean.entity.product.SkuEntity;

import org.springframework.stereotype.Service;

/**
 * @author unknow on 2018-12-25 23:35.
 */
@Service
public interface EnterprCustomPriceService {
    Long insertSelective(PriceModuleInfoDto priceModuleInfoDto);

    JSONObject selectByExample(PriceQueryReqDto priceQueryReqDto);

    PriceDetailResDto selectByPrimaryKey(Long customId);

    PriceDetailResDto selectByEnterprId(Long enterprId);

    Boolean delEnterprCustomPrice(Long customId);

    JSONObject batchImport(PriceModuleInfoDto priceModuleInfoDto);

	PageResultBean<SkuEntity> getPCFeatureListByPage(Long enterpriseId, PageBean pageBean);
}