package com.example.demo.service;

import com.example.demo.exception.Position;
import org.springframework.stereotype.Service;

@Service
public interface AnnualBonusService {
    double calculate(Position position, double salary, double bonus, int workDays);
}
