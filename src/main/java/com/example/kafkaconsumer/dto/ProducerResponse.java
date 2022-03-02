package com.example.kafkaconsumer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProducerResponse {

    private String message;
    private String topicName;
    private Integer partitions;
    private Long offsets;
    private Long timeStamp;
    private MessageJSON messageJSON;
}
