package com.ssg.shoppingserver.domain.order.dto.response;

import com.ssg.shoppingserver.domain.order.domain.OrderCancelReason;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class OrderCancelEventResponse {

    // 주문 고유 id
    private UUID orderId;

    // 주문 취소 사유
    private OrderCancelReason orderCancelReason;

    @Builder
    public OrderCancelEventResponse(UUID orderId, OrderCancelReason orderCancelReason) {
        this.orderId = orderId;
        this.orderCancelReason = orderCancelReason;
    }
}
