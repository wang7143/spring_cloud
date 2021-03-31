package com.cloud.Controller;



import com.cloud.Service.PayHystrixSver;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;


@RestController
@Slf4j
@DefaultProperties(defaultFallback = "Goble")
public class OrderContro {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

//    @Resource
//    private RestTemplate restTemplate;


//    @Resource
//    private DiscoveryClient discoveryClient;

    @Resource
    private PayHystrixSver payHystrixSver;



//    @GetMapping(value = "/consumer/payment/create")
//    public CommoResult<Payment> create(Payment payment){
//        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommoResult.class);
//    }
//
//    @GetMapping(value = "/consumer/payment/get/{id}")
//    public CommoResult<Payment> getById(@PathVariable("id") Long id){
//        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommoResult.class);
//    }

//    @GetMapping(value = "/consumer/lb")
//    public String getLB(){
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//        if(instances == null || instances.size() <= 0){
//            return null;
//        }
//        ServiceInstance instance = loadBalance.instance(instances);
//        URI uri = instance.getUri();
//        String scheme = instance.getScheme();
//        String instanceId = instance.getInstanceId();
//        return restTemplate.getForObject(uri + "/lb",String.class);
//    }

    @GetMapping(value = "/consumer/aa/{id}")
    public String payinfo(@PathVariable("id") Integer id){
        String payinfo = payHystrixSver.payinfo(id);
        return payinfo;
    }

    @GetMapping(value = "/consumer/bb/{id}")
//    @HystrixCommand(fallbackMethod = "fialtimeout",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    @HystrixCommand
    public String pay_timeout(@PathVariable("id") Integer id){
        String s = payHystrixSver.pay_timeout(id);
        return s;
    }

    public String fialtimeout(@PathVariable("id") Integer id){
        return "消费者80，对方支付繁忙。";
    }

    public String Goble(){
        return "global消费者80，对方支付繁忙。";
    }
}

