package com.example.demo.service;

import com.example.demo.exception.Codes;
import com.example.demo.exception.ErrorCodes;
import com.example.demo.exception.ErrorMessages;
import com.example.demo.model.Request;
import com.example.demo.model.Response;
import com.example.demo.util.DateTimeUtil;

import java.text.ParseException;
import java.util.Date;

public class ResponseBuilderService {
    public static Response buildResponse(Request request) throws ParseException {
        Response.ResponseBuilder builder = Response.builder();
        if (request.getPosition().isManager()) {
            builder.uid(request.getUid());
            builder.operationUid(request.getOperationUid());
            builder.systemTime(DateTimeUtil.getCustomFormat().format(new Date()));
            builder.code(Codes.SUCCESS);
            builder.annualBonus(new AnnualBonusServiceImpl().calculate
                    (request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays()));
            builder.quarterlyBonus(new QuarterlyBonusServiceImpl().calculateQuarterlyBonus
                    (request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays()));
            builder.errorCode(ErrorCodes.EMPTY);
            builder.errorMessage(ErrorMessages.EMPTY);
            return   builder.build();
        } else {
            builder.uid(request.getUid());
            builder.operationUid(request.getOperationUid());
            builder.systemTime(DateTimeUtil.getCustomFormat().format(new Date()));
            builder.code(Codes.SUCCESS);
            builder.annualBonus(new AnnualBonusServiceImpl().calculate
                    (request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays()));
            builder.quarterlyBonus(0.0);
            builder.errorCode(ErrorCodes.EMPTY);
            builder.errorMessage(ErrorMessages.EMPTY);
            return builder.build();
        }
    }
}
