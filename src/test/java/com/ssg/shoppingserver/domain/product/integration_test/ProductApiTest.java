package com.ssg.shoppingserver.domain.product.integration_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductApiTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("[ProductApi] 상품 리스트 전체 조회 API 성공 테스트")
    public void Products_조회_성공() throws Exception {

        //when
        ResultActions resultActions = mvc.perform(get("/api/products"))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.id)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.name)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.quantity)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.price)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.productCategory)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.createdAt)]").isNotEmpty())
        ;
    }



}
