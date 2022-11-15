package com.ssg.shoppingserver.domain.order.repository;

import com.ssg.shoppingserver.domain.order.domain.*;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
@Slf4j
public class OrderRepository {

    @Getter
    // Order 메모리 보관 queue
    private Queue<Order> orders = new ConcurrentLinkedQueue<Order>();

    @Getter
    // Cart Order 메모리 보관 queue
    private Queue<CartOrder> cartOrders = new ConcurrentLinkedQueue<CartOrder>();

    @Getter
    // Canceled Order 메모리 보관 queue
    private Queue<CanceledOrder> canceledOrders = new ConcurrentLinkedQueue<CanceledOrder>();


    @PostConstruct
    private void init() throws IOException, ParseException {

        // create mock order data
        createMockOrderData();
    }

    // create mock order data
    public void createMockOrderData() throws IOException, ParseException {

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

        // canceled-order add
        for (Order order : orders) {
            if(order.getOrderState().equals(OrderState.CANCELED))
                canceledOrders.add(new CanceledOrder(order, OrderCancelReason.NOT_IN_STOCK, LocalDateTime.now()));
        }
    }

}