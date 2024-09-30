package com.example.demo.service;

import com.example.demo.exception.UnsupportedCodeException;
import com.example.demo.exception.ValidationFailedException;
import com.example.demo.model.Response;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException{
        if (bindingResult.hasErrors()){
            throw new
                    ValidationFailedException(Objects.requireNonNull(bindingResult.getFieldError()).toString());
        }
    }

    @Override
    public void UCException(Response response) throws UnsupportedCodeException {
        if (response.getUid().equals("123")){
            throw new UnsupportedCodeException("Неподдерживаемый uid");
        }
    }
}