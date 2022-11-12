package com.ssg.shoppingserver.domain.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderRejectionReason {

    // 주문 실패/거부 이유

    ACCESS_DENIED(1L, "ACCESS_DENIED", "권한 필요"),
    NOT_IN_STOCK(2L, "NOT_IN_STOCK", "상품 품절/재고 없음"),
    ERROR(5L, "ERROR","ERROR");

    private final Long code;
    private final String title;
    private final String description;

    // find by code
    public static OrderRejectionReason findByCode(Long code) {
        return Arrays.stream(OrderRejectionReason.values())
                .filter( orderRejectionReason -> orderRejectionReason.getCode() == code)
                .findFirst()
                .orElse(ERROR);
    }
}
