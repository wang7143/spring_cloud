package com.cloud.Contorller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.entities.CommoResult;
import com.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class contro {

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "nacos  ,  serverport: " + serverport + "  id" + id;
    }

    @GetMapping("testA")
    public String testA(){
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "----testA";
    }

    @GetMapping("testB")
    public String testB(){
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "----testB";

    }

    @GetMapping("testD")
    public String testD(){
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        int age = 10 / 0;
        log.info( "...testD");
        return "----testD";

    }


    @GetMapping("testE")
    public String testE(){
        log.info("...testE");
        int age = 10 / 0;
        return "----testE";

    }

    @GetMapping("/hotkey")
    @SentinelResource(value = "hotkey",blockHandler = "deal_hotkey")
    public String Hotkey(@RequestParam(value = "p1",required = false) String p1,
                         @RequestParam(value = "p2",required = false) String p2){
        return "----testHotkey";

    }

    public String deal_hotkey(String p1, String p2, BlockException exception){
        return "--- 兜底";
    }


    @GetMapping("/rateLimit/byUrl")
//    @SentinelResource(value = "byUrl")
    public String reString(){
        return "200";
    }

}
