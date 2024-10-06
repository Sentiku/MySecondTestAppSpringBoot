package com.example.demo.service;

import com.example.demo.exception.Position;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService{
    @Override
    public double calculate(Position position, double salary, double bonus, int workDays) {
        int year = Year.now().getValue();

        int daysInYear = Year.isLeap(year) ? 366 : 365;

        return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
    }
}
