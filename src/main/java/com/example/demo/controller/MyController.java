package com.example.demo.controller;

import com.example.demo.exception.*;
import com.example.demo.model.Request;
import com.example.demo.model.Response;
import com.example.demo.service.ModifyRequestService;
import com.example.demo.service.ModifyResponseService;
import com.example.demo.service.ValidationService;
import com.example.demo.util.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    public MyController(ValidationService validationService, @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService){
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }
@Autowired
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult){

        log.info("request: {}", request);
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        log.info("System time success set: {}", response.getSystemTime());
        try{
            validationService.isValid(bindingResult);
            validationService.UCException(response);
        } catch (ValidationFailedException e){
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("Code status: {}", Codes.FAILED);
            log.error("Error code: {}", ErrorCodes.VALIDATION_EXCEPTION);
            log.error("Error message: {}", ErrorMessages.VALIDATION);
            log.info("Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("Code status: {}", Codes.FAILED);
            log.error("Error code: {}", ErrorCodes.UNKNOWN_EXCEPTION);
            log.error("Error message: {}", ErrorMessages.UNKNOWN);
            log.info("Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnsupportedCodeException e){
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            log.error("Code status: {}", Codes.FAILED);
            log.error("Error code: {}", ErrorCodes.UNSUPPORTED_EXCEPTION);
            log.error("Error message: {}", ErrorMessages.UNSUPPORTED);
            log.info("Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.getUid().length() < 33 & response.getOperationUid().length() < 33 & 1 < request.getCommunicationId() & request.getCommunicationId() < 100000){
            log.info("Code status: {}", Codes.SUCCESS);
            log.info("Response: {}", response);
            modifyResponseService.modify(response);
            modifyRequestService.modify(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.OUT_OF_RANGE);
            response.setErrorMessage(ErrorMessages.RANGE);
            log.error("Code status: {}", Codes.FAILED);
            log.error("Error code: {}", ErrorCodes.OUT_OF_RANGE);
            log.error("Error message: {}", ErrorMessages.RANGE);
            log.info("Response: {}", response);
            modifyResponseService.modify(response);
            modifyRequestService.modify(request);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
