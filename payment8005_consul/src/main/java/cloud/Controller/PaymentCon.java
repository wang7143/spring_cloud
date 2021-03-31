package cloud.Controller;


import cloud.service.PaymentService;
import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@ResponseBody
@Controller
@Slf4j
public class PaymentCon {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

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
    public CommoResult getId(@PathVariable("id") Long id){
        Payment result = paymentService.getById(id);
        log.info("***插入结果:" + result);

        if(result != null){
            return new CommoResult(200,"查询成功" +"server:port" + serverPort,result);
        }else {
            return new CommoResult(444,"查询ID",null);
        }
    }

    @GetMapping(value = "/discover")
    public Object discover(){
        List<String> services = discoveryClient.getServices();
        for (String element: services) {
            log.info("element:" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/con")
    public String zookpoot(){
        return "springcloud consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
