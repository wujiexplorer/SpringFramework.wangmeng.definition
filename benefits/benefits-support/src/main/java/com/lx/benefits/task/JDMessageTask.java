package com.lx.benefits.task;

import com.lx.benefits.service.jd.IJDMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * User: fan
 * Date: 2019/03/18
 * Time: 00:10
 */
@Component
public class JDMessageTask {

    @Autowired
    IJDMessageService ijdMessageService;

    @Scheduled(cron = "0 */45 * * * ?")
    public void YxSyncTask() {
        ijdMessageService.doMessageProcess();
    }

}
