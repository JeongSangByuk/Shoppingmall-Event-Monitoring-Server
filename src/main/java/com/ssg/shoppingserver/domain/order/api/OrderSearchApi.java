package com.ssg.shoppingserver.domain.order.api;

import com.ssg.shoppingserver.domain.order.application.OrderSearchService;
import com.ssg.shoppingserver.domain.order.dto.OrderInfoGetResponse;
import com.ssg.shoppingserver.domain.order.dto.OrderSearchRequest;
import com.ssg.shoppingserver.domain.user.dto.UserInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.UserSearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderSearchApi {

    private final OrderSearchService orderSearchService;

    @PostMapping("/orders/searching")
    private List<OrderInfoGetResponse> getOrders(@RequestBody @Valid OrderSearchRequest orderSearchRequest) {

        return orderSearchService.searchOrder(orderSearchRequest);
    }
}
