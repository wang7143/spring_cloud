package com.cloud.Controller;


import com.cloud.dao.PaymentDao;
import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import com.cloud.service.PaymentService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ResponseBody
@Controller
@Slf4j
public class PaymentCon {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommoResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("***插入结果:" + result);

        if(result > 0){
            return new CommoResult(200,"从插入数据库成功" + "server:port" + serverPort,result);
        }else {
            return new CommoResult(444,"插入数据库失败" + "server:port" + serverPort,null);
        }
    }



    @GetMapping(value = "/payment/get/{id}")
    public CommoResult<Payment> getId(@PathVariable("id") Long id){
        Payment result = paymentService.getById(id);
        log.info("***插入结果:" + result);

        if(result != null){
            return new CommoResult(200,"查询成功" +"server:port" + serverPort,result);
        }else {
            return new CommoResult(444,"查询ID",null);
        }
    }

    @GetMapping(value = "/discover")
    public Object discover(){
        List<String> services = discoveryClient.getServices();
        for (String element: services) {
            log.info("element:" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String getPayLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/timeout")
    public String Timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/aa/{id}")
    public String payinfo(@PathVariable("id") Integer id){
        log.info("rrr");
        return "线程池： " + Thread.currentThread().getName() + "   paymentinfo_OK,id:  " + id;
    }

    @GetMapping(value = "/bb/{id}")
    @HystrixCommand(fallbackMethod = "payment_timeHandler",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String pay_timeout(@PathVariable("id") Integer id){
//        int i = 1 / 0;
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return "线程池： " + Thread.currentThread().getName() + "   paymentinfo_OK,id:  " + id + "耗时" + 3;
    }

    public String payment_timeHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "   paymentinfo_timehandler,id:  " + id + "(●'◡'●)(●'◡'●)";
    }
}
