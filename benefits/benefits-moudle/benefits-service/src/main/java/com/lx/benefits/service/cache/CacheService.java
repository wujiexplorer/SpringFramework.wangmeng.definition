package com.lx.benefits.service.cache;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by softw on 2019/3/22.
 */
public interface CacheService {

    /**
     * 删除分类缓存
     */
    void delNavCache();

    String setCacheCagetory(CountDownLatch countDownLatch , Long enterprId, Map<Long,Long> filterMap);
}
