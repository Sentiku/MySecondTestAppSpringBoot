package com.example.demo.model;

import com.example.demo.exception.Codes;
import com.example.demo.exception.ErrorCodes;
import com.example.demo.exception.ErrorMessages;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response {

    private String uid;
    private String operationUid;
    private String systemTime;
    private Codes code;
    private ErrorCodes errorCode;
    private ErrorMessages errorMessage;
}
