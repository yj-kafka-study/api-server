package com.example.apiserver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomKafkaProducer {
    private KafkaProducer<String, String> kafkaProducer;

    public void create(String testData) {
        ProducerRecord<String, String> records = new ProducerRecord<>("topic-callback", "test", testData);
        kafkaProducer.send(records, new DemoProducerCallback());
    }

    private class DemoProducerCallback implements Callback{

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            log.info("callback called!!!");
            if (e != null) {
                e.printStackTrace();
            }
        }
    }
}
