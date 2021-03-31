package com.cloud.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = FabackService.class)
public interface PayHystrixSver {
    @GetMapping(value = "/aa/{id}")
    public String payinfo(@PathVariable("id") Integer id);


    @GetMapping(value = "/bb/{id}")
    public String pay_timeout(@PathVariable("id") Integer id);

}


