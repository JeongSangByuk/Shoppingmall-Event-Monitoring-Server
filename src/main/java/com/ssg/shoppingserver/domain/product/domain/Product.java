package com.ssg.shoppingserver.domain.product.domain;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

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
    public Product(UUID id, String name, Long quantity, Long price, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productCategory = productCategory;
    }
}
