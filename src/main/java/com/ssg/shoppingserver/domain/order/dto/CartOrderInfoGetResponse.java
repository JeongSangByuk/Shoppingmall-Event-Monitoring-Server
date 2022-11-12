package com.ssg.shoppingserver.domain.order.dto;

import com.ssg.shoppingserver.domain.order.domain.CartOrder;
import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CartOrderInfoGetResponse {

    // Cart-Order 고유 id
    private UUID id;

    // 장바구니 담기 사용자 고유 id
    private UUID userId;

    // 장바구니 담기 상품 고유 id
    private UUID productId;

    // 장바구니 담기 상품수
    private Long productQuantity;

    // 장바구니 담기 전체 가격
    private Long totalPrice;

    // '계속 담아 두기 기능' 여부
    private boolean isPersistent;

    // 장바구니 담기 일시
    private LocalDateTime createdTime;

    @Builder
    public CartOrderInfoGetResponse(CartOrder cartOrder) {
        this.id = cartOrder.getId();
        this.userId = cartOrder.getUserId();
        this.productId = cartOrder.getProductId();
        this.productQuantity = cartOrder.getProductQuantity();
        this.totalPrice = cartOrder.getTotalPrice();
        this.isPersistent = cartOrder.isPersistent();
        this.createdTime = cartOrder.getCreatedAt();
    }
}
