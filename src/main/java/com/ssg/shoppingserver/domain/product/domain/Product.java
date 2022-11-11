package com.ssg.shoppingserver.domain.product.domain;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    // Product 고유 id
    private UUID id;

    // Product 이름
    private String name;

    // Product 수량
    private Long quantity;

    // Product 가격
    private Long price;

    // Product 카테고리
    private ProductCategory productCategory;

    @Builder
    public Product(UUID id, String name, Long quantity, Long price, ProductCategory productCategory, LocalDateTime createdAt) {
        super(createdAt);
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productCategory = productCategory;
    }
}
