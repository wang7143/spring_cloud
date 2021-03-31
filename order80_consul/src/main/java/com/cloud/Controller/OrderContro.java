package com.cloud.Controller;



import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderContro {

    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/create")
    public CommoResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommoResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommoResult<Payment> getById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommoResult.class);
    }

    @GetMapping(value = "/consumer/con")
    public String zook(){
        String forObject = restTemplate.getForObject(PAYMENT_URL + "/con", String.class);
        return forObject;
    }
}




