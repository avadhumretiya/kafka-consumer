package com.example.kafkaconsumer.producer;

import com.example.kafkaconsumer.dto.MessageJSON;
import com.example.kafkaconsumer.dto.ProducerResponse;
import com.example.kafkaconsumer.exceptions.KCException;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Log4j2
public class BaseProducerImpl implements BaseProducer {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${error.messages}")
    private String errorMessage;

    @Override
    public void sendMessage(String msg) throws KCException {
        try {
            kafkaTemplate.send("replication", msg);
        } catch (Exception exception) {
            throw new KCException(errorMessage);
        }
    }

    @Override
    public ProducerResponse sendMessageWithCallBack(String msg) throws ExecutionException, InterruptedException, KCException {
        ProducerResponse producerResponse = new ProducerResponse();

        RecordMetadata recordMetadata = kafkaTemplate.send("replication", msg).get().getRecordMetadata();
        producerResponse.setTopicName(recordMetadata.topic());
        producerResponse.setOffsets(recordMetadata.offset());
        producerResponse.setPartitions(recordMetadata.partition());
        producerResponse.setTimeStamp(recordMetadata.timestamp());


        return producerResponse;
    }

    @Override
    public ProducerResponse sendMessageJSON(MessageJSON messageJSON) throws ExecutionException, InterruptedException, KCException {

        ProducerResponse producerResponse = new ProducerResponse();

        RecordMetadata recordMetadata = kafkaTemplate.send("replication", messageJSON).get().getRecordMetadata();
        producerResponse.setTopicName(recordMetadata.topic());
        producerResponse.setOffsets(recordMetadata.offset());
        producerResponse.setPartitions(recordMetadata.partition());
        producerResponse.setTimeStamp(recordMetadata.timestamp());

        return producerResponse;
    }
}
