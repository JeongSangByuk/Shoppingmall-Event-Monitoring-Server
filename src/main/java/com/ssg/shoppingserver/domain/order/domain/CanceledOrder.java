package com.ssg.shoppingserver.domain.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CanceledOrder extends Order{

    /*
    주문 취소와 같은 경우는, '주문하기' 이벤트만큼 크리티컬한 이벤트라고 생각합니다.
    때문에 따로 Entity를 하나 더 만들어서 관리하는 것으로 결정했습니다.
    이 때, 전반적인 속성이 Order 도메인과 같음으로, Order Entity를 상속받았습니다.
     */

    // CanceledOrder 이유(고객 취소 이유)
    private OrderCancelReason orderCancelReason;

    // 주문 취소 일시
    private LocalDateTime canceledAt;

    public CanceledOrder(Order order, OrderCancelReason orderCancelReason, LocalDateTime canceledAt) {
        super(order.getId(), order.getUserId(), order.getProductId(), order.getProductQuantity(), order.getTotalPrice()
                , order.getOrderState(), order.getCreatedAt());
        this.orderCancelReason = orderCancelReason;
        this.canceledAt = canceledAt;
    }
}
