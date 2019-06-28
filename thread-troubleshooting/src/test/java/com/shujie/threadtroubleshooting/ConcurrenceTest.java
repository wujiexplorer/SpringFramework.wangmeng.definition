package com.shujie.threadtroubleshooting;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p> description:
 * <p> 2019/06/27
 *
 * @author ssj
 * @version 1.0.0
 */
public class ConcurrenceTest {

    private static final int TOTAL_REQUEST = 10;
    private static final int THREAD_POOL_SIZE = 50;

    @Test
    public void test() throws InterruptedException {
        System.setProperty("http.maxConnections", String.valueOf(THREAD_POOL_SIZE));
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        CountDownLatch countDownLatch = new CountDownLatch(TOTAL_REQUEST);
        for (int i = 0; i < TOTAL_REQUEST; i++) {
            executorService.execute(()->{
                restTemplate.getForObject("http://localhost:8080/getMsg", String.class);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
}
