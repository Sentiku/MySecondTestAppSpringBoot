package com.example.demo.controller;

import com.example.demo.exception.*;
import com.example.demo.model.Request;
import com.example.demo.model.Response;
import com.example.demo.service.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final CheckUidService checkUidService;

    @Autowired
    public MyController(ValidationService validationService, @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService, CheckUidService checkUidService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.checkUidService = checkUidService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) throws ParseException {
        log.info("request: {}", request);
            Response response = ResponseBuilderService.buildResponse(request);
            log.info("System time success set: {}", response.getSystemTime());
            try {
                validationService.isValid(bindingResult);
                checkUidService.isChecked(request);
            } catch (ValidationFailedException e) {
                return ErrorCatcherService.handleValidationException(response, e);
            } catch (UnsupportedCodeException e) {
                return ErrorCatcherService.handleUnsupportedCodeException(response, e);
            } catch (Exception e) {
                return ErrorCatcherService.handleUnknownException(response, e);
            }
            if (response.getUid().length() < 33 & response.getOperationUid().length() < 33 & 1 < request.getCommunicationId() & request.getCommunicationId() < 100000) {
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
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }