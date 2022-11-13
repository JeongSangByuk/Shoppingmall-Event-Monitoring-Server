package com.ssg.shoppingserver.domain.order.application;

import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import com.ssg.shoppingserver.domain.order.dto.OrderInfoGetResponse;
import com.ssg.shoppingserver.domain.order.dto.OrderSearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderSearchService {

    private final OrderService orderService;

    private final String ORDER_STATE_KEY = "orderStates";

    private final String MIN_TOTAL_PRICE_KEY = "minTotalPrice";

    private final String MIN_TOTAL_QUANTITY_KEY = "minTotalQuantity";


    // order 검색
    public List<OrderInfoGetResponse> searchOrder(OrderSearchRequest orderSearchRequest) {

        List<Order> orders = orderService.getOrders().stream()
                .filter(order ->
                        checkByOrderState(order, orderSearchRequest)
                        && checkByMinValue(order, orderSearchRequest, MIN_TOTAL_PRICE_KEY)
                        && checkByMinValue(order, orderSearchRequest, MIN_TOTAL_QUANTITY_KEY)
                ).collect(Collectors.toList());

        List<OrderInfoGetResponse> orderInfoGetResponses = orders.stream()
                .map(order -> OrderInfoGetResponse.builder()
                        .order(order).build())
                .collect(Collectors.toList());

        return orderInfoGetResponses;
    }

    // 주문 상태로 검색
    public boolean checkByOrderState(Order order, OrderSearchRequest orderSearchRequest) {

        Long[] orderStates = orderSearchRequest.getOrderStates();

        // ALL 조건일 경우. 그냥 true 리턴
        if (orderSearchRequest.getIsAllChecked().get(ORDER_STATE_KEY))
            return true;

        for (Long orderState : orderStates) {

            // Order state가 포함된다면 true return
            if (OrderState.findByCode(orderState).equals(order.getOrderState()))
                return true;
        }

        // orderState에 포함되지 않는 다면 false 리턴
        return false;

    }

    // 특정 가격 이상, 특정 수량 이상으로 검색
    public boolean checkByMinValue(Order order, OrderSearchRequest orderSearchRequest, String option) {

        Long optionValue;
        Long orderValue;

        switch (option){
            case MIN_TOTAL_PRICE_KEY:
                optionValue = orderSearchRequest.getMinTotalPrice();
                orderValue = order.getTotalPrice();
                break;
            case MIN_TOTAL_QUANTITY_KEY:
                optionValue = orderSearchRequest.getMinTotalQuantity();
                orderValue = order.getProductQuantity();
                break;
            default:
                throw new IllegalArgumentException();
        }

        // ALL 조건일 경우. 그냥 true 리턴
        if (orderSearchRequest.getIsAllChecked().get(option))
            return true;

        // 이상 조건 만족시 true return
        if (orderValue >= optionValue)
            return true;

        return false;

    }

}
