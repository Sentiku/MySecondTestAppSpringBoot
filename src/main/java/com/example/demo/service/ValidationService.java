package com.example.demo.service;

import com.example.demo.exception.UnsupportedCodeException;
import com.example.demo.exception.ValidationFailedException;
import com.example.demo.model.Response;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
    void UCException(Response response) throws UnsupportedCodeException;
}
