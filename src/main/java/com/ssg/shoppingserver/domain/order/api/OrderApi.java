package com.ssg.shoppingserver.domain.order.api;

import com.ssg.shoppingserver.domain.order.application.OrderService;
import com.ssg.shoppingserver.domain.order.dto.response.CanceledOrderInfoGetResponse;
import com.ssg.shoppingserver.domain.order.dto.request.OrderCancelEventRequest;
import com.ssg.shoppingserver.domain.order.dto.request.OrderCreateEventRequest;
import com.ssg.shoppingserver.domain.order.dto.response.OrderCancelEventResponse;
import com.ssg.shoppingserver.domain.order.dto.response.OrderCreateEventResponse;
import com.ssg.shoppingserver.domain.order.dto.response.OrderInfoGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;

    // total order list get api
    @GetMapping("/orders")
    public List<OrderInfoGetResponse> getOrders() {
        return orderService.getTotalOrders();
    }

    // canceled total order list get api
    @GetMapping("/canceled-orders")
    public List<CanceledOrderInfoGetResponse> getCanceledOrders() {
        return orderService.getTotalCanceledOrders();
    }

    // order event creation api
    @PostMapping("/order")
    public OrderCreateEventResponse createOrder(@RequestBody @Valid OrderCreateEventRequest orderCreateEventRequest) {
        return orderService.createOrder(orderCreateEventRequest);
    }

    // order event deletion api
    @DeleteMapping("/order")
    public OrderCancelEventResponse cancelOrder(@RequestBody @Valid OrderCancelEventRequest orderCancelEventRequest) {
        return orderService.cancelOrder(orderCancelEventRequest);
    }

}
