package com.example.demo.service;

import com.example.demo.exception.*;
import com.example.demo.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ErrorCatcherService {
    public static ResponseEntity<Response> handleValidationException(Response response, ValidationFailedException e) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
        response.setErrorMessage(ErrorMessages.VALIDATION);
        log.info("response: {}", response);
        log.error("Ошибка Валидации: ", e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Response> handleUnsupportedCodeException(Response response, UnsupportedCodeException e)
    {
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
        response.setErrorMessage(ErrorMessages.UNSUPPORTED);
        log.info("response: {}", response);
        log.error("Неподдерживаемая ошибка: ", e);
        return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
    }

    public static ResponseEntity<Response> handleUnknownException(Response response, Exception e) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
        response.setErrorMessage(ErrorMessages.UNKNOWN);
        log.info("response: {}", response);
        log.error("Неизвестная ошибка: ", e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
