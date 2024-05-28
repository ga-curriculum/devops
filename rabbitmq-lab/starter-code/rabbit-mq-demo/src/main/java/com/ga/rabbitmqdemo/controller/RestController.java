package com.ga.rabbitmqdemo.controller;

import com.ga.rabbitmqdemo.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    Sender sender;

    @GetMapping
    public String hello(){
        return "Welcome to RabbitMQ";
    }

    @GetMapping("/send/{msg}")
    public String send(@PathVariable String msg){
        sender.send(msg);
        return "message sent";
    }
}
