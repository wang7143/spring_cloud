package com.cloud.Contorller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import com.cloud.service.FenService;
import com.cloud.service.PaymentFallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
public class contro {



    @Lazy
    @Resource
    private FenService pa;

//    @Resource
//    private RestTemplate restTemplate;

    @Value("${service-url.nacos-service}")
    private String serverURL;

//    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "handfallback",exceptionsToIgnore = {IllegalArgumentException.class})
//    @GetMapping(value = "/consumer/payment/{id}")
//    public CommoResult<Payment> getPayment(@PathVariable("id") Long id){
//        CommoResult<Payment> rs = restTemplate.getForObject(serverURL + "/PaymentSQL/" + id, CommoResult.class);
//        if (id == 4) {
//            throw new IllegalArgumentException("参数异常");
//        }else if (rs.getData() == null) {
//            throw new NullPointerException("空指针，无记录");
//        }
//        return rs;
//    }

//    fallback方法
//    public CommoResult handfallback(@PathVariable("id") Long id, Throwable e){
//        Payment payment = new Payment(id, "null");
//        return new CommoResult<>(444,"兜底" + e.getMessage(),payment);
//    }
//
//    public CommoResult blockHandler(@PathVariable Long id, BlockException blockException){
//        Payment payment = new Payment(id, "null");
//        return new CommoResult<>(445,"blockHandler限流" + blockException.getMessage(),payment);
//    }



    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommoResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
        return pa.paymentSQL(id);
    }

}


