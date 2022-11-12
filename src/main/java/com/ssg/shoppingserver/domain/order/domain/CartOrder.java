package com.ssg.shoppingserver.domain.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartOrder extends Order {

    /*
    장바구니 엔티티는, Order 엔티티와 유사한 칼럼이 매우 많습니다.
    모니터링에 필요한, 활용하는 정보가 비슷하기 때문입니다.
    때문에 Order 엔티티를 상속 받았습니다.
     */

    // '계속 담아 두기 기능' 여부
    private boolean isPersistent;

    public CartOrder(Order order, boolean isPersistent) {
        super(order.getId(), order.getUserId(), order.getProductId(), order.getProductQuantity(), order.getTotalPrice()
                , order.getOrderState(), order.getCreatedAt());
        this.isPersistent = isPersistent;
    }
}
