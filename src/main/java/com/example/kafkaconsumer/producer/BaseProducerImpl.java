package com.example.kafkaconsumer.producer;

import com.example.kafkaconsumer.dto.MessageJSON;
import com.example.kafkaconsumer.dto.ProducerResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Component
@Log4j2
public class BaseProducerImpl implements BaseProducer {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendMessage(String msg) {
        kafkaTemplate.send("mytopic", msg);
    }

    @Override
    public ProducerResponse sendMessageWithCallBack(String msg) throws ExecutionException, InterruptedException {
        ProducerResponse producerResponse = new ProducerResponse();

        RecordMetadata recordMetadata = kafkaTemplate.send("mytopic", msg).get().getRecordMetadata();
        producerResponse.setTopicName(recordMetadata.topic());
        producerResponse.setOffsets(recordMetadata.offset());
        producerResponse.setPartitions(recordMetadata.partition());
        producerResponse.setTimeStamp(recordMetadata.timestamp());

        return producerResponse;
    }

    @Override
    public ProducerResponse sendMessageJSON(MessageJSON messageJSON) {

        ProducerResponse producerResponse = new ProducerResponse();

        ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send("mytopic", messageJSON);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {

            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                producerResponse.setTopicName(result.getRecordMetadata().topic());
                producerResponse.setOffsets(result.getRecordMetadata().offset());
                producerResponse.setPartitions(result.getRecordMetadata().partition());
                producerResponse.setTimeStamp(result.getRecordMetadata().timestamp());
            }
        });
        return producerResponse;
    }
}
