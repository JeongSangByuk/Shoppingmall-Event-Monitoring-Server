package com.ssg.shoppingserver.domain.user.application;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.domain.user.dto.response.UserInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.request.UserSignupEventRequest;
import com.ssg.shoppingserver.domain.user.dto.response.UserSignupEventResponse;
import com.ssg.shoppingserver.domain.user.repository.UserRepository;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // sign up event creation, user add
    public UserSignupEventResponse signup(UserSignupEventRequest userSignupEventRequest) {

        // get user entity
        User user = userSignupEventRequest.toEntity();

        // user add
        userRepository.getUsers().add(user);

        // create response dto
        UserSignupEventResponse userSignupEventResponse = UserSignupEventResponse.builder()
                .email(user.getEmail())
                .name(user.getName()).build();

        return userSignupEventResponse;
    }

    // get total users
    public List<UserInfoGetResponse> getTotalUsers() {

        // get total users
        List<UserInfoGetResponse> userInfoGetResponse = userRepository.getUsers().stream()
                .map(user -> UserInfoGetResponse.builder()
                        .user(user).build())
                .collect(Collectors.toList());

        return userInfoGetResponse;
    }
}
