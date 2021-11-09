package com.skaleto.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;

@Configuration
@Slf4j
public class KafkaConfiguration {

    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("mytopic", 10, (short) 2);
    }

    @Bean
    public NewTopic updateTopic() {
        return new NewTopic("mytopic", 12, (short) 3);
    }

    @Bean
    public ConsumerAwareListenerErrorHandler consumeErrorHandler() {
        return (message, e, consumer) -> {
            log.info("message {}, exception {}, consumer {}", message, e, consumer);
            return 1;
        };
    }
}
