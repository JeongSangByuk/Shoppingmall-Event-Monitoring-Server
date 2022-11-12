package com.ssg.shoppingserver.domain.order.domain;

import com.ssg.shoppingserver.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity{

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

    // 주문 실패/거부 시 사유.
    private OrderRejectionReason orderRejectionReason;

    public Order(UUID id, UUID userId, UUID productId, Long productQuantity, Long totalPrice, OrderState orderState, LocalDateTime createdAt) {
        super(createdAt);
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
        this.orderState = orderState;
    }

}
