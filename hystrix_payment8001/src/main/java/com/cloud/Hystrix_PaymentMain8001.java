package com.cloud;


import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.security.PublicKey;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableCircuitBreaker
public class Hystrix_PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(Hystrix_PaymentMain8001.class,args);
    }

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet let = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> re = new ServletRegistrationBean<>(let);
        re.setLoadOnStartup(1);
        re.addUrlMappings("/hystrix.stream");
        re.setName("HystrixMetricsStreamServlet");
        return re;
    }
}


