package com.cloud.Service.imp;

import com.cloud.Service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)
public class MessageImp implements MessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String sendMessage() {
        String s = UUID.randomUUID().toString();
        boolean send = output.send(MessageBuilder.withPayload(s).build());
        System.out.println("***" + s);
        return s + send;
    }
}
