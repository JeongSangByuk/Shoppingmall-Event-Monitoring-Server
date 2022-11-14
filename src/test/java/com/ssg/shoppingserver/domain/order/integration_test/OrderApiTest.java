package com.ssg.shoppingserver.domain.order.integration_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.shoppingserver.domain.order.domain.OrderCancelReason;
import com.ssg.shoppingserver.domain.order.domain.OrderState;
import com.ssg.shoppingserver.domain.order.dto.request.OrderCancelEventRequest;
import com.ssg.shoppingserver.domain.order.dto.request.OrderCreateEventRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderApiTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("[OrderApi] 주문 리스트 전체 조회 API 성공 테스트")
    public void Orders_조회_성공() throws Exception {

        //when
        ResultActions resultActions = mvc.perform(get("/api/orders"))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.id)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.userId)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.productId)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.productQuantity)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.totalPrice)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.orderState)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.createdAt)]").isNotEmpty())
        ;
    }

    @Test
    @DisplayName("[OrderApi] 취소된 주문 리스트 전체 조회 API 성공 테스트")
    public void Canceled_Orders_조회_성공() throws Exception {

        //when
        ResultActions resultActions = mvc.perform(get("/api/canceled-orders"))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.id)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.userId)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.productId)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.productQuantity)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.totalPrice)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.orderState)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.createdAt)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.orderCancelReason)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.canceledAt)]").isNotEmpty())
        ;
    }

    @Test
    @DisplayName("[OrderApi] 주문하기 이벤트 발생 API 성공 테스트")
    public void Order_추가_성공() throws Exception {

        final String orderId = "a3563d7f-e2c0-438b-88b0-1e92e9e5d7a8";
        final String productId = "8041bbfd-2ebf-4a4e-bedd-157285d1333d";
        final Long productQuantity = 2L;
        final Long totalPrice = 10000L;
        final Long orderState = 2L;

        //given
        OrderCreateEventRequest orderCreateEventRequest = OrderCreateEventRequest.builder()
                .userId(UUID.fromString(orderId))
                .productId(UUID.fromString(productId))
                .productQuantity(productQuantity)
                .totalPrice(totalPrice)
                .orderStateCode(orderState).build();

        //when
        ResultActions resultActions = mvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreateEventRequest)))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("totalPrice").value(totalPrice))
                .andExpect(jsonPath("orderState").value(OrderState.findByCode(orderState).name()))
        ;
    }

    @Test
    @DisplayName("[OrderApi] 주문하기 이벤트 발생 API 성공 테스트")
    public void Order_취소_성공() throws Exception {

        final String orderId = "d8b56a33-97fa-4018-a261-0da238342818";
        final Long orderCancelReasonCode = 2L;

        //given
        OrderCancelEventRequest orderCancelEventRequest = OrderCancelEventRequest.builder()
                .orderId(UUID.fromString("d8b56a33-97fa-4018-a261-0da238342818"))
                .orderCancelReasonCode(orderCancelReasonCode).build();

        //when
        ResultActions resultActions = mvc.perform(delete("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCancelEventRequest)))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderId").value(orderId))
                .andExpect(jsonPath("orderCancelReason").value(OrderCancelReason.findByCode(orderCancelReasonCode).name()))
        ;
    }

}
