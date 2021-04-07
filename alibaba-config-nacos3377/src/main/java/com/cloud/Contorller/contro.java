package com.cloud.Contorller;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

@RestController
@RefreshScope  //支持动态刷新
public class contro {


    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/info")
    public String getPayment(){
        return configInfo;
    }
}
