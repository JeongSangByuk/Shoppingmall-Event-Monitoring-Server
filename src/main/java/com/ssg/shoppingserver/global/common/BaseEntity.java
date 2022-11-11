package com.ssg.shoppingserver.global.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseEntity {

    // 객체 생성 일시
    private LocalDateTime createdAt;

}
