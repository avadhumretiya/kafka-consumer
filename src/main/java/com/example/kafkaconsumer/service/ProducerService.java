package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.dto.MessageJSON;
import com.example.kafkaconsumer.dto.ProducerResponse;

import java.util.concurrent.ExecutionException;

public interface ProducerService {

    public String produceStringMessage(String message);

    public ProducerResponse produceStringMessageWithCallBack(String message) throws ExecutionException, InterruptedException;

    public ProducerResponse produceJSONMessageWithCallBack(MessageJSON messageJSON);

}
