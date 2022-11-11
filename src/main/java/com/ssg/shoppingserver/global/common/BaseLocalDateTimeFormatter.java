package com.ssg.shoppingserver.global.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseLocalDateTimeFormatter {

    public static DateTimeFormatter getLocalTimeFormatter() {
        return DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));
    }
}
