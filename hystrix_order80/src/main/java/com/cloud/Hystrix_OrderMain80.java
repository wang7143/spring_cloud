package com.cloud;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = selfule.class) //配置自定义负载策略
public class Hystrix_OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(Hystrix_OrderMain80.class,args);
    }
}
