package com.example.kafkaconsumer.producer;

import com.example.kafkaconsumer.dto.MessageJSON;
import com.example.kafkaconsumer.dto.ProducerResponse;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public interface BaseProducer {

    public void sendMessage(String msg);

    public ProducerResponse sendMessageWithCallBack(String msg) throws ExecutionException, InterruptedException;

    public ProducerResponse sendMessageJSON(MessageJSON messageJSON);
}
