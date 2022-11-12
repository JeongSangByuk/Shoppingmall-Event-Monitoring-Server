package com.ssg.shoppingserver.domain.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderState {

    // 주문 상태

    BEFORE_PAYMENT(1L, "BEFORE_PAYMENT", "결제 전"),
    PAYMENT_SUCCESS(2L, "PAYMENT_SUCCESS", "결제 완료"),
    PREPARATION_SHIPMENT(3L, "PREPARATION_SHIPMENT", "배송 준비 중"),
    DURING_SHIPMENT(4L, "DURING_SHIPMENT", "배송 중"),
    SHIPMENT_COMPLETED(5L, "SHIPMENT_COMPLETED", "배송 완료"),
    PURCHASE_CONFIRMED(6L, "PURCHASE_CONFIRMED","구매 확정"),
    IN_CART(7L, "IN_CART", "장바구니에 담아진 주문"),
    REJECTED(8L, "REJECTED", "주문 실패/거부"),
    CANCELED(9L, "CANCELED", "주문 취소"),
    ERROR(10L, "ERROR", "Request 에러");

    private final Long code;
    private final String title;
    private final String description;

    // find by code
    public static OrderState findByCode(Long code) {
        return Arrays.stream(OrderState.values())
                .filter(orderState -> orderState.getCode() == code)
                .findFirst()
                .orElse(ERROR);
    }

}
