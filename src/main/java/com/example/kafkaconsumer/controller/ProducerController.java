package com.example.kafkaconsumer.controller;

import com.example.kafkaconsumer.dto.MessageJSON;
import com.example.kafkaconsumer.dto.ProducerResponse;
import com.example.kafkaconsumer.exceptions.KCException;
import com.example.kafkaconsumer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/message")
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @GetMapping(value = "/publish-message/{message}")
    public String publishStringMessage(@PathVariable("message") String message) throws KCException {
        return producerService.produceStringMessage(message);
    }

    @GetMapping(value = "/publish-message-with-callback/{message}")
    public ProducerResponse publishStringMessageWithCallBack(@PathVariable("message") String message) throws ExecutionException, InterruptedException, KCException {
        return producerService.produceStringMessageWithCallBack(message);
    }

    @GetMapping(value = "/publish-json-message-with-callback")
    public ProducerResponse publishJSONMessageWithCallBack(@RequestBody MessageJSON messageJSON) throws ExecutionException, InterruptedException, KCException {
        return producerService.produceJSONMessageWithCallBack(messageJSON);
    }
}
