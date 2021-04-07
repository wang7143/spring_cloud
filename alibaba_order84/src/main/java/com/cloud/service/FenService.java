package com.cloud.service;

import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "cloud-alibaba",fallback = PaymentFallbackService.class)
public interface FenService {

    @GetMapping(value = "/PaymentSQL/{id}")
    public CommoResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
