package com.ssg.shoppingserver.domain.product.domain;

import com.ssg.shoppingserver.domain.order.domain.OrderState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {

    // 상품 카테고리

    FASHION(1L,"FASHION"),
    BEAUTY(2L,"BEAUTY"),
    SPORT(3L,"SPORT"),
    FOOD(4L,"FOOD"),
    DIGITAL(5L,"DIGITAL"),
    BOOK(6L,"BOOK"),
    LIFE(7L,"LIFE"),
    ERROR(8L,"ERROR");

    private final Long code;
    private final String title;

    // find by code
    public static ProductCategory findByCode(Long code) {
        return Arrays.stream(ProductCategory.values())
                .filter( productCategory -> productCategory.getCode() == code)
                .findFirst()
                .orElse(ERROR);
    }
}
