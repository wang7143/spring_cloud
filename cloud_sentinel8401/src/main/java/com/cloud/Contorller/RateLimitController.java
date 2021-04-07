package com.cloud.Contorller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommoResult byResource()
    {
        return new CommoResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }



    public CommoResult handleException(BlockException exception){
        return new CommoResult(444,exception.getClass().getCanonicalName()+"\t服务不可用");
    }
}
