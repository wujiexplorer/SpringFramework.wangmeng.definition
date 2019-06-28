package com.shujie.threadtroubleshooting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class ThreadTroubleshootingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadTroubleshootingApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofSeconds(2))
                .setReadTimeout(Duration.ofSeconds(2)).build();
    }

    @GetMapping("/getMsg")
    public String getMsg() {
        try {
            String demo = restTemplate.getForObject("http://localhost:8080/slowApi", String.class);
            return ">>>" + demo;

        } catch (Exception e) {
            return "failed!";
        }
    }

    @GetMapping("/slowApi")
    public String slowApi() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "ok!";
    }

}
