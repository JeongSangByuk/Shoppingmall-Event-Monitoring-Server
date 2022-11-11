package com.ssg.shoppingserver.domain.product.dto;

import com.ssg.shoppingserver.domain.product.domain.Product;
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
    private Long quantity;

    // Product 가격
    private Long price;

    // Product 카테고리
    private ProductCategory productCategory;

    @Builder
    public ProductTotalInfoGetResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
        this.productCategory = product.getProductCategory();
    }
}
