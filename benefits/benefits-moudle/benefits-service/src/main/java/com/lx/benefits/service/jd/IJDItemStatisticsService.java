package com.lx.benefits.service.jd;

import com.lx.benefits.bean.dto.jd.JDSkuImportReq;
import com.lx.benefits.bean.entity.jd.PrdJdBaseItem;

import java.util.List;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 21:59
 */
public interface IJDItemStatisticsService {

    void statistics() throws Exception;

    void goodsImport(JDSkuImportReq record) throws Exception;

    void updateItemDetailByRate(List<PrdJdBaseItem> jdBaseItems);

}
