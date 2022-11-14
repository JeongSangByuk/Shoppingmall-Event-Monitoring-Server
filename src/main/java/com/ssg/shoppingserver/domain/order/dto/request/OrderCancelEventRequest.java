package com.ssg.shoppingserver.domain.order.dto.request;

import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class OrderCancelEventRequest {

    @NotNull
    // 주문 고유 id
    private UUID orderId;

    @NotNull
    private Long orderCancelReasonCode;

    @Builder
    public OrderCancelEventRequest(UUID orderId, Long orderCancelReasonCode) {
        this.orderId = orderId;
        this.orderCancelReasonCode = orderCancelReasonCode;
    }
}
