package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableHystrixDashboard
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = selfule.class) //配置自定义负载策略
public class Hystrix_dashboard9001 {
    public static void main(String[] args) {
        SpringApplication.run(Hystrix_dashboard9001.class,args);
    }
}
