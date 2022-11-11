package com.ssg.shoppingserver.domain.order.application;

import com.ssg.shoppingserver.domain.order.domain.CanceledOrder;
import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import com.ssg.shoppingserver.domain.order.dto.OrderCreateEventRequest;
import com.ssg.shoppingserver.domain.order.dto.OrderTotalInfoGetResponse;
import com.ssg.shoppingserver.domain.product.domain.Product;
import com.ssg.shoppingserver.domain.product.domain.ProductCategory;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    // Order 메모리 보관 queue
    private Queue<Order> orders = new ConcurrentLinkedQueue<Order>();

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

    //get total orders
    public List<OrderTotalInfoGetResponse> getTotalOrders() {

        List<OrderTotalInfoGetResponse> orderTotalInfoGetResponses = orders.stream()
                .map(order -> OrderTotalInfoGetResponse.builder()
                        .order(order).build())
                .collect(Collectors.toList());

        return orderTotalInfoGetResponses;
    }

    // create mock order data
    public void createMockData() throws IOException, ParseException {

        // get mock data json file
        ClassPathResource resource = new ClassPathResource("mock-data/order-mock-data.json");
        Path path = Paths.get(resource.getURI());
        String json = Files.readString(path);

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

}
