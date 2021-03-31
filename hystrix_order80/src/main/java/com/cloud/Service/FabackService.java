package com.cloud.Service;

import org.springframework.stereotype.Component;

@Component
public class FabackService implements PayHystrixSver{

    @Override
    public String payinfo(Integer id) {
        return "fallback info ";
    }


    @Override
    public String pay_timeout(Integer id) {
        return "fallback time out";
    }
}
