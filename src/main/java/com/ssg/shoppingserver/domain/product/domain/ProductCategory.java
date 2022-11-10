package com.ssg.shoppingserver.domain.product.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {

    // 상품 카테고리

    FASHION("FASHION"),
    BEAUTY("BEAUTY"),
    SPORT("SPORT"),
    FOOD("FOOD"),
    DIGITAL("DIGITAL"),
    BOOK("BOOK"),
    LIFE("LIFE");

    private final String name;
}
