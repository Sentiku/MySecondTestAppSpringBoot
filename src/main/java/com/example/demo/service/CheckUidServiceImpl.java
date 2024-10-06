package com.example.demo.service;

import com.example.demo.exception.UnsupportedCodeException;
import com.example.demo.model.Request;
import org.springframework.stereotype.Service;
@Service
public class CheckUidServiceImpl implements CheckUidService{
    @Override
    public void isChecked (Request request) throws UnsupportedCodeException {
        if (request.getUid().equals("123")) {
            throw new UnsupportedCodeException(" Не поддерживаемая ошибка -" +
                    " это сообщение из Сервиса" );
        }
    }
}
