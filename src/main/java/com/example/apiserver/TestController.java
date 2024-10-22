package com.example.apiserver;

import io.confluent.developer.avro.Hobbit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/kafka")
@RestController
public class TestController {
    private final KafkaProducer<String, String> producer;
    private final KafkaProducer<String, Object> objectProducer;

    @GetMapping("/object")
    public String object() {
        Hobbit hobbit = new Hobbit("quote");
        ProducerRecord<String, Object> record = new ProducerRecord<>("object-topic", "key", hobbit);
        try {
            RecordMetadata recordMetadata = objectProducer.send(record).get();
            System.out.println(recordMetadata);
            return recordMetadata.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //1. fire and forget
    @GetMapping("/faf")
    public Void faf() {
        ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", "test-key", "test-value");
        try{
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //2. sync
    @GetMapping("/sync")
    public String sync() {
        ProducerRecord<String, String> record = new ProducerRecord<>("my_topic", "test-value");
        String headerKey = "test-header-key";
        String headerValue = "test-header-value";
        record.headers().add(headerKey, headerValue.getBytes(StandardCharsets.UTF_8));
        try {
            RecordMetadata recordMetadata = producer.send(record).get();
            System.out.println(recordMetadata);
            return recordMetadata.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //3. async
    @GetMapping("/async")
    public Void async() {
        ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", "test-key", "test-value");
        producer.send(record, new DemoProducerCallback());
        return null;
    }
    private class DemoProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            log.info("callback called!!!");
            if (e != null) {
                e.printStackTrace();
            }
        }
    }
}
