package com.lx.benefits.config.aspect;

import com.apollo.common.lock.aspect.DistributedLockAspect;
import com.apollo.common.lock.support.DistributedLock;
import com.lx.benefits.web.aspect.LogAspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public LogAspect logAspect(){
        LogAspect logAspect = new LogAspect();
        return logAspect;
    }
    /**
     * 分布式锁切面
     * @param redisDistributedLock
     * @return
     */
    @Bean
    public DistributedLockAspect distributedLockAspect(@Qualifier("redisDistributedLock") DistributedLock redisDistributedLock){
        DistributedLockAspect distributedLockAspect = new DistributedLockAspect();
        distributedLockAspect.setDistributedLock(redisDistributedLock);
        return  distributedLockAspect;
    }
}
