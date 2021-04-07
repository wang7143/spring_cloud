package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Alibaba_payment9003 {
    public static void main(String[] args) {
        SpringApplication.run(Alibaba_payment9003.class,args);
    }
}
