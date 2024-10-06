package com.example.demo.service;

import com.example.demo.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;
@Slf4j
@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            log.error("Ошибка в bindingResult: {}",bindingResult);
            throw new
                    ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}