package com.ssg.shoppingserver.domain.order.application;

import com.ssg.shoppingserver.domain.order.domain.CanceledOrder;
import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import com.ssg.shoppingserver.domain.order.dto.OrderCreateEventRequest;
import com.ssg.shoppingserver.domain.order.dto.OrderTotalInfoGetResponse;
import com.ssg.shoppingserver.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
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

}
