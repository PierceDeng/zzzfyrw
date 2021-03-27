package com.zzzfyrw.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.zzzfyrw.common","com.zzzfyrw.core"})
public class ZzzfyrwCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzzfyrwCoreApplication.class, args);
        System.out.println("启动成功");
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
