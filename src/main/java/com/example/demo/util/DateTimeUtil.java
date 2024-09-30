package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

@Slf4j
public class DateTimeUtil {
    public static SimpleDateFormat getCustomFormat()
    {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }
}
