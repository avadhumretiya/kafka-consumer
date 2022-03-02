package com.example.kafkaconsumer.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BaseConsumer {

//    @Value("${kafka.consumer.groupid}")
//    private static final String groupId=null;
//
//    @Value("#{'${topicOne:annotated1,foo}'.split(',')}")
//    private String[] topics;

    @KafkaListener(topics = "#{'${kafka.consumer.topics}'.split(',')}", groupId = "#{'${kafka.consumer.groupid}'}")
    public void receiveMessage(
            String payload
            ,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic
    ) {
        log.info(payload);
        log.info(topic);
    }
}
