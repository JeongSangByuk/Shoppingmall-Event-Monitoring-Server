package com.ssg.shoppingserver.domain.order.api;

import com.ssg.shoppingserver.domain.order.application.OrderService;
import com.ssg.shoppingserver.domain.order.dto.OrderCreateEventRequest;
import com.ssg.shoppingserver.domain.order.dto.OrderTotalInfoGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;

    // total order list get api
    @GetMapping("/orders")
    public List<OrderTotalInfoGetResponse> getOrders() {
        return orderService.getTotalOrders();
    }

    // order event creation api
    @PostMapping("/order")
    public void createOrder(@RequestBody @Valid OrderCreateEventRequest orderCreateEventRequest) {
        orderService.createOrder(orderCreateEventRequest);
    }




}
