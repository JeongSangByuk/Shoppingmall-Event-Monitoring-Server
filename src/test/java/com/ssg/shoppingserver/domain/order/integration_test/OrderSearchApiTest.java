package com.ssg.shoppingserver.domain.order.integration_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.shoppingserver.domain.order.dto.request.OrderSearchRequest;
import com.ssg.shoppingserver.domain.user.dto.request.UserSearchRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.FileCopyUtils;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderSearchApiTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("[OrderSearchApi] 주문 전체 검색 API 성공 테스트")
    public void OrderSearch_성공() throws Exception {

        //given
        ClassPathResource cpr = new ClassPathResource("mock-data/order-mock-test-data1.json");
        String json = new String(FileCopyUtils.copyToByteArray(cpr.getInputStream()), StandardCharsets.UTF_8);
        OrderSearchRequest orderSearchRequest = objectMapper.readValue(json, OrderSearchRequest.class);

        //when
        ResultActions resultActions = mvc.perform(post("/api/orders/searching")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderSearchRequest)))
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
    @DisplayName("[OrderSearchApi] 주문 특정 조건으로 검색 API 성공 테스트")
    public void OrderSearch_특정_조건_검색_성공() throws Exception {

        //given
        ClassPathResource cpr = new ClassPathResource("mock-data/order-mock-test-data2.json");
        String json = new String(FileCopyUtils.copyToByteArray(cpr.getInputStream()), StandardCharsets.UTF_8);
        OrderSearchRequest orderSearchRequest = objectMapper.readValue(json, OrderSearchRequest.class);

        //when
        ResultActions resultActions = mvc.perform(post("/api/orders/searching")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderSearchRequest)))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.orderState != 'BEFORE_PAYMENT')]").doesNotExist())
                .andExpect(jsonPath("$.[?(@.productQuantity < 3)]").doesNotExist())
                .andExpect(jsonPath("$.[?(@.totalPrice < 5000)]").doesNotExist())
                .andExpect(jsonPath("$.[?(@.createdAt)]").isNotEmpty())
        ;
    }

}
