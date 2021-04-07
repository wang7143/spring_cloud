package com.cloud.service;

import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements FenService{

    @Override
    public CommoResult<Payment> paymentSQL(Long id) {
        return new CommoResult<>(4444,"服务降级",new Payment(id,"error"));
    }
}
