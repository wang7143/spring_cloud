package com.cloud.Contorller;

import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class contro {

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "nacos  ,  serverport: " + serverport + "  id" + id;
    }

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"HJKHSAKJDHSJKAHD"));
        hashMap.put(2L, new Payment(2L,"hgudhfkdshfkjdshf"));
        hashMap.put(3L, new Payment(3L,"sdfdsfdsifhihdhkjash"));
    }

    @GetMapping("/PaymentSQL/{id}")
    public CommoResult<Payment> payment(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommoResult<Payment> result = new CommoResult<>(200,"mysql,  serverport: " + serverport,payment);
        if (id == 4) {
            throw new IllegalArgumentException("参数异常");
        }else if (result.getData() == null) {
            throw new NullPointerException("空指针，无记录");
        }
        return result;
    }
}
