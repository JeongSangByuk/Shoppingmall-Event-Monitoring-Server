package com.ssg.shoppingserver.domain.order.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@NoArgsConstructor
public class OrderSearchRequest {

    @NotNull
    private Map<String, Boolean> isAllChecked;

    @NotNull
    private Long[] orderStates;

    @NotNull
    private Long minTotalPrice;

    @NotNull
    private Long minTotalQuantity;

    @NotNull
    private Long time;

}
