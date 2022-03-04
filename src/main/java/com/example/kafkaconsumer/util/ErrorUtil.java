package com.example.kafkaconsumer.util;

import com.example.kafkaconsumer.dto.ErrorResponse;
import com.example.kafkaconsumer.enums.MessageStatus;
import com.example.kafkaconsumer.exceptions.KCException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ErrorUtil {

    @ExceptionHandler(value = {Exception.class})
    public ErrorResponse errorResponse(Exception exception){
        log.info("Default exception entry.......................");
        exception.printStackTrace();
        return new ErrorResponse(
                MessageStatus.FAILURE.getValue(),
                exception.getMessage(),
                exception.getStackTrace());
    }

    @ExceptionHandler(value = {KCException.class})
    public ErrorResponse errorResponse(KCException kcException){
        log.info("Custom exception entry.......................");
        kcException.printStackTrace();
        return new ErrorResponse(
                MessageStatus.FAILURE.getValue(),
                kcException.getMessage(),
                kcException.getStackTrace());
    }

}
