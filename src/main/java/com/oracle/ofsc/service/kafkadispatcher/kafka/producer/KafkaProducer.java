package com.oracle.ofsc.service.kafkadispatcher.kafka.producer;

import com.oracle.ofsc.service.kafkadispatcher.dispatcher.config.KafkaDispatcherConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Objects;

@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
                         KafkaDispatcherConfig kafkaDispatcherConfig) {
        Objects.requireNonNull(kafkaTemplate, "'kafkaTemplate' must be not null");
        Objects.requireNonNull(kafkaDispatcherConfig, "'kafkaDispatcherConfig' must be not null");
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, ConsumerRecord<String, String> record, int partition) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
                topic,
                partition,
                record.timestamp(),
                record.key(),
                record.value(),
                record.headers()
        );

        final ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerRecord);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Message was successfully send to " + topic);
            }

            @Override
            public void onFailure(Throwable e) {
                System.out.println("Message was failure send to " + record.topic());
            }
        });
    }
}
