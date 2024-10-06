package com.example.demo.service;

import com.example.demo.exception.UnsupportedCodeException;
import com.example.demo.model.Request;
import org.springframework.stereotype.Service;

@Service
public interface CheckUidService {

    void isChecked (Request request) throws UnsupportedCodeException;
}
