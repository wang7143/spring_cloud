package com.cloud.Controller;


import com.cloud.Service.MessageProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MessageCon {

    @Resource
    private MessageProvider messages;

    @ResponseBody
    @GetMapping(value = "/send")
    public String sendMessage(){
        return messages.sendMessage();
    }
}
