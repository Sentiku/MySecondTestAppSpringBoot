package com.example.demo.service;

import com.example.demo.exception.Position;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService{
    @Override
        public double calculateQuarterlyBonus(Position position, double salary, double bonus, int workDays) {

        int year = Year.now().getValue();
        int daysInYear = Year.isLeap(year) ? 366 : 365;
        return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays / 4;
        }
}