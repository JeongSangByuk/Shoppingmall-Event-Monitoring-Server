package com.ssg.shoppingserver.domain.order.application;

import com.ssg.shoppingserver.domain.order.domain.CanceledOrder;
import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderCancelReason;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import com.ssg.shoppingserver.domain.order.dto.request.OrderCancelEventRequest;
import com.ssg.shoppingserver.domain.order.dto.request.OrderCreateEventRequest;
import com.ssg.shoppingserver.domain.order.dto.response.CanceledOrderInfoGetResponse;
import com.ssg.shoppingserver.domain.order.dto.response.OrderCancelEventResponse;
import com.ssg.shoppingserver.domain.order.dto.response.OrderCreateEventResponse;
import com.ssg.shoppingserver.domain.order.dto.response.OrderInfoGetResponse;
import com.ssg.shoppingserver.domain.order.repository.OrderRepository;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    // 기준 시간.
    private final LocalDateTime baseTime = LocalDateTime.of(2022, 11, 10, 10, 00, 00);

    // 설정 없음의 값
    private final Long NOT_LIMITED_TIME = 100000L;

    private final OrderRepository orderRepository;

    // create order, order add
    public OrderCreateEventResponse createOrder(OrderCreateEventRequest orderCreateEventRequest) {

        // get order entity
        Order order = orderCreateEventRequest.toEntity();

        // order add
        orderRepository.getOrders().add(order);

        // create response dto
        OrderCreateEventResponse orderCreateEventResponse = OrderCreateEventResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .orderState(order.getOrderState()).build();

        return orderCreateEventResponse;
    }

    // get total orders
    public List<OrderInfoGetResponse> getTotalOrders() {

        List<OrderInfoGetResponse> orderInfoGetRespons = orderRepository.getOrders().stream()
                .map(order -> OrderInfoGetResponse.builder()
                        .order(order).build())
                .collect(Collectors.toList());

        return orderInfoGetRespons;
    }

    // get total orders
    public List<CanceledOrderInfoGetResponse> getTotalCanceledOrders() {

        List<CanceledOrderInfoGetResponse> canceledOrderInfoGetResponse = orderRepository.getCanceledOrders().stream()
                .map(canceledOrder -> CanceledOrderInfoGetResponse.builder()
                        .canceledOrder(canceledOrder).build())
                .collect(Collectors.toList());

        return canceledOrderInfoGetResponse;
    }

    // cancel order
    public OrderCancelEventResponse cancelOrder(OrderCancelEventRequest orderCancelEventRequest) {

        // find by id
        Order orderById = orderRepository.getOrders().stream()
                .filter(order -> order.getId().equals(orderCancelEventRequest.getOrderId()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException());

        // add canceled order
        orderRepository.getCanceledOrders().add(new CanceledOrder(orderById, OrderCancelReason.findByCode(orderCancelEventRequest.getOrderCancelReasonCode()), LocalDateTime.now()));

        // change canceled order state
        orderById.changeToCancelState();

        // create response dto
        OrderCancelEventResponse orderCancelEventResponse = OrderCancelEventResponse.builder()
                .orderId(orderById.getId())
                .orderCancelReason(OrderCancelReason.findByCode(orderCancelEventRequest.getOrderCancelReasonCode())).build();

        return orderCancelEventResponse;

    }

    // 시간을 통한 게산
    public boolean checkByTime(Order order, Long time) {

        if(time.equals(NOT_LIMITED_TIME))
            return true;

        // 시간 차이 계산
        Duration duration = Duration.between(order.getCreatedAt(), baseTime);

        // 특정 시간 기준 이내인지
        if (duration.getSeconds() <= time)
            return true;

        return false;
    }

}
