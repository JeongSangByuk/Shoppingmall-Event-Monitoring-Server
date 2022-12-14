package com.ssg.shoppingserver.domain.order.api;

import com.ssg.shoppingserver.domain.order.application.CartOrderService;
import com.ssg.shoppingserver.domain.order.dto.request.CartOrderCreateEventRequest;
import com.ssg.shoppingserver.domain.order.dto.response.CartOrderInfoGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartOrderApi {

    private final CartOrderService cartOrderService;

    // cart-order event creation api
    @PostMapping("/cart-order")
    public void createCartOrder(@RequestBody @Valid CartOrderCreateEventRequest cartOrderCreateEventRequest) {
        cartOrderService.createCartOrder(cartOrderCreateEventRequest);
    }

    // total cart order list get api
    @GetMapping("/cart-orders")
    public List<CartOrderInfoGetResponse> getCartOrders() {
        return cartOrderService.getTotalCartOrders();
    }
}
