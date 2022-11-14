package com.ssg.shoppingserver.domain.user.integration_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.dto.request.UserSignupEventRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserApiTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("[UserApi] 유저 회원 가입 이벤트 발생 API 성공 테스트")
    public void User_회원가입_성공() throws Exception {

        final String name = "정하벽";
        final String email = "test111@naver.com";
        final Boolean isSmileClubMember = false;
        final Long membershipLevelCode = 2L;

        //given
        UserSignupEventRequest userSignupEventRequest = UserSignupEventRequest.builder()
                .name(name)
                .email(email)
                .isSmileClubMember(isSmileClubMember)
                .membershipLevelCode(membershipLevelCode).build();

        //when
        ResultActions resultActions = mvc.perform(post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userSignupEventRequest)))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("email").value(email))
        ;
    }

    @Test
    @DisplayName("[UserApi] 유저 리스트 전체 조회 API 성공 테스트")
    public void Users_조회_성공() throws Exception {

        //when
        ResultActions resultActions = mvc.perform(get("/api/users"))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.id)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.email)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.name)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.membershipLevel)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.createdAt)]").isNotEmpty())
                .andExpect(jsonPath("$.[?(@.smileClubMember)]").isNotEmpty())
        ;
    }
}
