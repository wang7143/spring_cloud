package com.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = selfule.class) //配置自定义负载策略
public class OrderMain_feing80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain_feing80.class,args);
    }
}
