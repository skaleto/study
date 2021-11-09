package com.skaleto.kafka.controller;

import com.skaleto.kafka.service.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yaoyibin
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private KafkaProducer kafkaProducer;

    @GetMapping("/kafka/produce/{message}")
    public void sendMessage1(@PathVariable("message") String message) {
        kafkaProducer.produce(message);
    }

}