package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.dto.MessageJSON;
import com.example.kafkaconsumer.dto.ProducerResponse;
import com.example.kafkaconsumer.exceptions.KCException;
import com.example.kafkaconsumer.producer.BaseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProducerServiceImpl implements ProducerService{

    @Autowired
    BaseProducer baseProducer;

    @Override
    public String produceStringMessage(String message) throws KCException {
        baseProducer.sendMessage(message);
        return message;
    }

    @Override
    public ProducerResponse produceStringMessageWithCallBack(String message) throws ExecutionException, InterruptedException, KCException {
        ProducerResponse producerResponse=baseProducer.sendMessageWithCallBack(message);
        producerResponse.setMessage(message);
        return producerResponse;
    }

    @Override
    public ProducerResponse produceJSONMessageWithCallBack(MessageJSON messageJSON) throws ExecutionException, InterruptedException, KCException {
        ProducerResponse producerResponse=baseProducer.sendMessageJSON(messageJSON);
        producerResponse.setMessageJSON(messageJSON);
        return producerResponse;
    }
}
