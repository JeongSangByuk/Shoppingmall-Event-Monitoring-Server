package com.ssg.shoppingserver.domain.order.dto.request;

import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class OrderCreateEventRequest {

    @NotNull
    // 구매자 고유 id
    private UUID userId;

    @NotNull
    // 구매 상품 고유 id
    private UUID productId;

    @NotNull
    // 상품 구매 수
    private Long productQuantity;

    @NotNull
    // 전체 결제 가격
    private Long totalPrice;

    @NotNull
    // 주문 현재 상태
    private Long orderStateCode;

    @Builder
    public OrderCreateEventRequest(UUID userId, UUID productId, Long productQuantity, Long totalPrice, Long orderStateCode) {
        this.userId = userId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
        this.orderStateCode = orderStateCode;
    }

    // dto to entity
    public Order toEntity() {
        return Order.builder()
                .id(UUID.randomUUID())
                .userId(this.userId)
                .productId(this.productId)
                .productQuantity(this.productQuantity)
                .totalPrice(this.totalPrice)
                .orderState(OrderState.findByCode(this.orderStateCode))
                .createdAt(LocalDateTime.now()).build();
    }
}
