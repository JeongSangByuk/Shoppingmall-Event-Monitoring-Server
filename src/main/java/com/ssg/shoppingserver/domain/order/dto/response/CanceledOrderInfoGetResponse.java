package com.ssg.shoppingserver.domain.order.dto.response;

import com.ssg.shoppingserver.domain.order.domain.CanceledOrder;
import com.ssg.shoppingserver.domain.order.domain.OrderCancelReason;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CanceledOrderInfoGetResponse {

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
    private String createdAt;

    // CanceledOrder 이유(고객 취소 이유)
    private OrderCancelReason orderCancelReason;

    // 주문 취소 일시
    private String canceledAt;

    @Builder
    public CanceledOrderInfoGetResponse(CanceledOrder canceledOrder) {
        this.id = canceledOrder.getId();
        this.userId = canceledOrder.getUserId();
        this.productId = canceledOrder.getProductId();
        this.productQuantity = canceledOrder.getProductQuantity();
        this.totalPrice = canceledOrder.getTotalPrice();
        this.orderState = canceledOrder.getOrderState();
        this.createdAt = canceledOrder.getCanceledAt().format(BaseLocalDateTimeFormatter.getLocalTimeFormatter());
        this.orderCancelReason = canceledOrder.getOrderCancelReason();
        this.canceledAt = canceledOrder.getCanceledAt().format(BaseLocalDateTimeFormatter.getLocalTimeFormatter());
    }
}
