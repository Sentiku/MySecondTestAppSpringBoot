package com.example.demo.service;

import com.example.demo.model.Request;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.demo.exception.System.Service_1;

@Service
public class ModifySystemNameRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request) {
        request.setSystemName(Service_1);
        request.setSource("Service 1");
        HttpEntity<Request> httpEntity = new HttpEntity<>(request);
        new RestTemplate().exchange("http://localhost:8084/feedback", HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {
        });
    }
}
