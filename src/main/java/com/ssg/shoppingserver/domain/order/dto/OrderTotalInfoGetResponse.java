package com.ssg.shoppingserver.domain.order.dto;

import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class OrderTotalInfoGetResponse {

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
    public OrderTotalInfoGetResponse(Order order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.productId = order.getProductId();
        this.productQuantity = order.getProductQuantity();
        this.totalPrice = order.getTotalPrice();
        this.orderState = order.getOrderState();
        this.createdTime = order.getCreatedTime();
    }
}
