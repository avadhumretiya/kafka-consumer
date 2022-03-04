package com.example.kafkaconsumer.producer;

import com.example.kafkaconsumer.dto.MessageJSON;
import com.example.kafkaconsumer.dto.ProducerResponse;
import com.example.kafkaconsumer.exceptions.KCException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public interface BaseProducer {

    public void sendMessage(String msg) throws KCException;

    public ProducerResponse sendMessageWithCallBack(String msg) throws ExecutionException, InterruptedException, KCException;

    public ProducerResponse sendMessageJSON(MessageJSON messageJSON) throws ExecutionException, InterruptedException, KCException;
}
