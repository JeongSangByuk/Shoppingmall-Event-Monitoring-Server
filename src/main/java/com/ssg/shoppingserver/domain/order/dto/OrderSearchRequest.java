package com.ssg.shoppingserver.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class OrderSearchRequest {

    private Map<String, Boolean> isAllChecked;

    private Long[] orderStates;

    private Long minTotalPrice;

    private Long minTotalQuantity;

    private Long time;

}
