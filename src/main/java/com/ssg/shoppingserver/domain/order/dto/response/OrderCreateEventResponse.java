package com.ssg.shoppingserver.domain.order.dto.response;

import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class OrderCreateEventResponse {

    // 주문 고유 id
    private UUID id;

    // 전체 결제 가격
    private Long totalPrice;

    // 주문 현재 상태
    private OrderState orderState;

    @Builder
    public OrderCreateEventResponse(UUID id, Long totalPrice, OrderState orderState) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderState = orderState;
    }
}
