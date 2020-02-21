package com.oracle.ofsc.service.kafkadispatcher.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"routing-input", "routing-input-low"})
    public void listen(ConsumerRecord<String, String> record, Consumer<?, ?> consumer) {
        System.out.println();
    }
}
