package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.dto.MessageJSON;
import com.example.kafkaconsumer.dto.ProducerResponse;
import com.example.kafkaconsumer.exceptions.KCException;

import java.util.concurrent.ExecutionException;

public interface ProducerService {

    public String produceStringMessage(String message) throws KCException;

    public ProducerResponse produceStringMessageWithCallBack(String message) throws ExecutionException, InterruptedException, KCException;

    public ProducerResponse produceJSONMessageWithCallBack(MessageJSON messageJSON) throws ExecutionException, InterruptedException, KCException;

}
