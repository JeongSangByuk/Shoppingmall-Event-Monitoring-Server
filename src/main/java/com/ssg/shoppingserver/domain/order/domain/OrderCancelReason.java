package com.ssg.shoppingserver.domain.order.domain;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderCancelReason {

    // 주문 취소 이유

    SIMPLE_MIND_CHANGED(1L, "SIMPLE_MIND_CHANGED", "단순 변심"),
    SHIPMENT_DELAYED(2L, "SHIPMENT_DELAYED", "배송 지연"),
    INFORMATION_MISMATCH(3L, "PREPARATION_SHIPMENT", "상품 정보 상이"),
    NOT_IN_STOCK(4L, "NOT_IN_STOCK", "상품 품절/재고 없음"),
    ERROR(5L, "ERROR","ERROR");

    private final Long code;
    private final String title;
    private final String description;

    // find by code
    public static OrderCancelReason findByCode(Long code) {
        return Arrays.stream(OrderCancelReason.values())
                .filter( orderCancelReason -> orderCancelReason.getCode() == code)
                .findFirst()
                .orElse(ERROR);
    }
}
