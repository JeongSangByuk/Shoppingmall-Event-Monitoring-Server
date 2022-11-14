package com.ssg.shoppingserver.domain.order.application;

import com.ssg.shoppingserver.domain.order.domain.CartOrder;
import com.ssg.shoppingserver.domain.order.dto.request.CartOrderCreateEventRequest;
import com.ssg.shoppingserver.domain.order.dto.response.CartOrderInfoGetResponse;
import com.ssg.shoppingserver.domain.order.repository.OrderRepository;
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

    private final OrderRepository orderRepository;

    // create cart order, cart order add
    public void createCartOrder(CartOrderCreateEventRequest cartOrderCreateEventRequest) {

        // get cart order entity
        CartOrder cartOrder = cartOrderCreateEventRequest.toEntity();

        // cart order add
        orderRepository.getCartOrders().add(cartOrder);
    }

    // get total cart orders
    public List<CartOrderInfoGetResponse> getTotalCartOrders() {

        List<CartOrderInfoGetResponse> cartOrderInfoGetResponse = orderRepository.getCartOrders().stream()
                .map(cartOrder -> CartOrderInfoGetResponse.builder()
                        .cartOrder(cartOrder).build())
                .collect(Collectors.toList());

        return cartOrderInfoGetResponse;
    }

}
