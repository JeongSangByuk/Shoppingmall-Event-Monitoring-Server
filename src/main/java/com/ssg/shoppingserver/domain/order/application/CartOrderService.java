package com.ssg.shoppingserver.domain.order.application;

import com.ssg.shoppingserver.domain.order.domain.CartOrder;
import com.ssg.shoppingserver.domain.order.domain.Order;
import com.ssg.shoppingserver.domain.order.dto.CanceledOrderTotalInfoGetResponse;
import com.ssg.shoppingserver.domain.order.dto.CartOrderCreateEventRequest;
import com.ssg.shoppingserver.domain.order.dto.CartOrderTotalInfoGetResponse;
import com.ssg.shoppingserver.domain.order.dto.OrderCreateEventRequest;
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
public class CartOrderService {

    // Cart Order 메모리 보관 queue
    private Queue<CartOrder> cartOrders = new ConcurrentLinkedQueue<CartOrder>();

    // create cart order, cart order add
    public void createCartOrder(CartOrderCreateEventRequest cartOrderCreateEventRequest) {

        // get cart order entity
        CartOrder cartOrder = cartOrderCreateEventRequest.toEntity();

        // cart order add
        cartOrders.add(cartOrder);
    }

    // get total cart orders
    public List<CartOrderTotalInfoGetResponse> getTotalCartOrders() {

        List<CartOrderTotalInfoGetResponse> cartOrderTotalInfoGetResponses = cartOrders.stream()
                .map(cartOrder -> CartOrderTotalInfoGetResponse.builder()
                        .cartOrder(cartOrder).build())
                .collect(Collectors.toList());

        return cartOrderTotalInfoGetResponses;
    }

}
