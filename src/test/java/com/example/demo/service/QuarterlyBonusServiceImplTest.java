package com.example.demo.service;

import com.example.demo.exception.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuarterlyBonusServiceImplTest {
    @Test
    void calculateQuarterlyBonus() {
        Position position = Position.TL;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        //when
        double result = new QuarterlyBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);

        //then
        double expected = 195267.48971193415;
        assertThat(result).isEqualTo(expected);
    }
}
