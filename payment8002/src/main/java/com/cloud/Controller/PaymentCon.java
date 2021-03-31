package com.cloud.Controller;


import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import com.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@ResponseBody
@Controller
@Slf4j
public class PaymentCon {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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
    public CommoResult getById(@PathVariable("id") Long id){
        Payment result = paymentService.getById(id);
        log.info("***插入结果:" + result);

        if(result != null){
            return new CommoResult(200,"查询成功" +"server:port" + serverPort,result);
        }else {
            return new CommoResult(444,"查询ID",null);
        }
    }

    @GetMapping(value = "/lb")
    public String getPayLB(){
        return serverPort;
    }
}
