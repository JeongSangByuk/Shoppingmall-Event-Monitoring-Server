package com.ssg.shoppingserver.domain.order.dto;

import com.ssg.shoppingserver.domain.order.domain.CartOrder;
import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CartOrderCreateEventRequest {

    @NotNull
    // 장바구니 담기 사용자 고유 id
    private UUID userId;

    @NotNull
    // 장바구니 담기 상품 고유 id
    private UUID productId;

    @NotNull
    // 장바구니 담기 상품수
    private Long productQuantity;

    @NotNull
    // 장바구니 담기 전체 가격
    private Long totalPrice;

    // dto to entity
    public CartOrder toEntity() {

        return CartOrder.builder()
                .id(UUID.randomUUID())
                .userId(this.userId)
                .productId(this.productId)
                .productQuantity(this.productQuantity)
                .totalPrice(this.totalPrice)
                .orderState(OrderState.IN_CART)
                .isPersistent(false)
                .createdAt(LocalDateTime.now()).build();
    }
}
