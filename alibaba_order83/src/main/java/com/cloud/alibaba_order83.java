package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class alibaba_order83 {
    public static void main(String[] args) {
        SpringApplication.run(alibaba_order83.class,args);
    }
}
