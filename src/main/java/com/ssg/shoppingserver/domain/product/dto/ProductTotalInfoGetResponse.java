package com.ssg.shoppingserver.domain.product.dto;

import com.ssg.shoppingserver.domain.product.domain.ProductCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProductTotalInfoGetResponse {

    // Product 고유 id
    private UUID id;

    // Product 이름
    private String name;

    // Product 수량
    private int quantity;

    // Product 가격
    private int price;

    // Product 카테고리
    private ProductCategory productCategory;

    @Builder
    public ProductTotalInfoGetResponse(UUID id, String name, int quantity, int price, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productCategory = productCategory;
    }
}
