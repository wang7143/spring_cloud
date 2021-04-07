package com.cloud.Controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(Sink.class)
public class MessageCon {

    @Value(value = "${server.port}")
    private String serport1;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("客户8804--------" + message.getPayload());
    }
}
