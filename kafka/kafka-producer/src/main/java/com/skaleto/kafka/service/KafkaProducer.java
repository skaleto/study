package com.skaleto.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author yaoyibin
 */
@Service
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void produce(String message) {
        kafkaTemplate.send("mytopic", message).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(@NotNull Throwable ex) {
                log.error("send message fail", ex);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("send message success {}", result.toString());
            }
        });
    }
}
