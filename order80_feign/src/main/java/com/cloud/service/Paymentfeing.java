package com.cloud.service;

import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface Paymentfeing {

    @GetMapping(value = "/payment/get/{id}")
    public CommoResult<Payment> getId(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/timeout")
    public String Timeout();
}
