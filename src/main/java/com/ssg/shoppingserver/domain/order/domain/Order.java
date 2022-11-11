package com.ssg.shoppingserver.domain.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    // Order 고유 id
    private UUID id;

    // 구매자 고유 id
    private UUID userId;

    // 구매 상품 고유 id
    private UUID productId;

    // 상품 구매 수
    private Long productQuantity;

    // 전체 결제 가격
    private Long totalPrice;

    // 주문 현재 상태
    private OrderState orderState;

    // 주문 일시
    private LocalDateTime createdTime;

    @Builder
    public Order(UUID id, UUID userId, UUID productId, Long productQuantity, Long totalPrice, OrderState orderState, LocalDateTime createdTime) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
        this.orderState = orderState;
        this.createdTime = createdTime;
    }
}
