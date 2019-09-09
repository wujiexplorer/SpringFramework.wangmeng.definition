package com.lx.benefits.task;

import com.lx.benefits.bean.dto.jd.JDSkuImportReq;
import com.lx.benefits.bean.dto.yx.YxSkuImportReq;
import com.lx.benefits.service.jd.PrdJdBaseItemService;
import com.lx.benefits.service.yx.IYXItemInfoService;
import com.lx.benefits.service.yx.PrdYxBaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * User: fan
 * Date: 2019/03/12
 * Time: 10:07
 */
//@Component
public class GoodsSyncTask {

    @Autowired
    IYXItemInfoService iyxItemInfoService;
    @Autowired
    PrdYxBaseItemService prdYxBaseItemService;
    @Autowired
    PrdJdBaseItemService jdBaseItemService;

    //@Scheduled(cron = "0 0 1 * * ?")
    public void YxSyncTask() {
        iyxItemInfoService.statistics();
        prdYxBaseItemService.goodsImport(new YxSkuImportReq());
    }


    //@Scheduled(cron = "0 0 1 * * ?")
    public void JdSyncTask() {
        jdBaseItemService.synItemFromJd();
        jdBaseItemService.goodsImport(new JDSkuImportReq());
    }

}
