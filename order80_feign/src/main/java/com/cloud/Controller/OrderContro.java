package com.cloud.Controller;



import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;

import com.cloud.service.Paymentfeing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderContro {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";



    @Resource
    private Paymentfeing paymentfeing;




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


    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommoResult<Payment> getPayment(@PathVariable("id") Long id)
    {
        return paymentfeing.getId(id);
    }

    @GetMapping(value = "/consumer/payment/timeout")
    public String Timeout(){
        return paymentfeing.Timeout();
    }

}
