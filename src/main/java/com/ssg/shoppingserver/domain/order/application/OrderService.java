package com.ssg.shoppingserver.domain.order.application;

import com.ssg.shoppingserver.domain.order.domain.CanceledOrder;
import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderCancelReason;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import com.ssg.shoppingserver.domain.order.dto.*;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Getter
    // Order 메모리 보관 queue
    private Queue<Order> orders = new ConcurrentLinkedQueue<Order>();

    @Getter
    // Canceled Order 메모리 보관 queue
    private Queue<CanceledOrder> canceledOrders = new ConcurrentLinkedQueue<CanceledOrder>();

    @PostConstruct
    private void init() throws IOException, ParseException {

        // create mock order data
        createMockData();
    }

    // create order, order add
    public void createOrder(OrderCreateEventRequest orderCreateEventRequest) {

        // get order entity
        Order order = orderCreateEventRequest.toEntity();

        // order add
        orders.add(order);
    }

    // get total orders
    public List<OrderInfoGetResponse> getTotalOrders() {

        List<OrderInfoGetResponse> orderInfoGetRespons = orders.stream()
                .map(order -> OrderInfoGetResponse.builder()
                        .order(order).build())
                .collect(Collectors.toList());

        return orderInfoGetRespons;
    }

    // get total orders
    public List<CanceledOrderInfoGetResponse> getTotalCanceledOrders() {

        List<CanceledOrderInfoGetResponse> canceledOrderInfoGetResponse = canceledOrders.stream()
                .map(canceledOrder -> CanceledOrderInfoGetResponse.builder()
                        .canceledOrder(canceledOrder).build())
                .collect(Collectors.toList());

        return canceledOrderInfoGetResponse;
    }

    // cancel order
    public void cancelOrder(OrderCancelEventRequest orderCancelEventRequest) {

        // find by id
        Order orderById = orders.stream()
                .filter(order -> order.getId().equals(orderCancelEventRequest.getOrderId()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException());

        // add canceled order
        canceledOrders.add(new CanceledOrder(orderById, OrderCancelReason.findByCode(orderCancelEventRequest.getOrderCancelReasonCode()), LocalDateTime.now()));

        // change canceled order state
        orderById.changeToCancelState();

    }

    // create mock order data
    public void createMockData() throws IOException, ParseException {

        // get mock data json file
        ClassPathResource cpr = new ClassPathResource("mock-data/order-mock-data.json");
        String json = new String(FileCopyUtils.copyToByteArray(cpr.getInputStream()), StandardCharsets.UTF_8);

        // parsing json
        JSONArray mockOrders = (JSONArray) new JSONParser().parse(json);

        // add mock order data
        for (Object mockOrdersObjet : mockOrders) {

            JSONObject mockOrder = (JSONObject) mockOrdersObjet;

            // create mock order entity
            Order order = Order.builder()
                    .id(UUID.fromString((String) mockOrder.get("id")))
                    .userId(UUID.fromString((String) mockOrder.get("userId")))
                    .productId(UUID.fromString((String) mockOrder.get("productId")))
                    .totalPrice((Long) mockOrder.get("totalPrice"))
                    .productQuantity((Long) mockOrder.get("productQuantity"))
                    .orderState(OrderState.findByCode((Long) mockOrder.get("orderState")))
                    .createdAt(LocalDateTime.parse((String) mockOrder.get("createdAt"), BaseLocalDateTimeFormatter.getLocalTimeFormatter())).build();

            // mock product data add
            orders.add(order);
        }
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
