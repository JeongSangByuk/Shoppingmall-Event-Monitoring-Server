package com.ssg.shoppingserver;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class test1 {

    @Test
    void test() {
        Logger log = (Logger) LoggerFactory.getLogger(test1.class);

        for(int i = 0; i < 30; i ++)
            log.info(UUID.randomUUID().toString());
    }


}
