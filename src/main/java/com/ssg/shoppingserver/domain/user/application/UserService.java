package com.ssg.shoppingserver.domain.user.application;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.domain.user.dto.UserInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.UserSignupEventRequest;
import com.ssg.shoppingserver.global.common.BaseLocalDateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Getter
    // user 메모리 보관 queue
    private Queue<User> users = new ConcurrentLinkedQueue<User>();

    @PostConstruct
    private void init() throws IOException, ParseException {

        // create mock user data
        createMockData();
    }

    // sign up event creation, user add
    public void signup(UserSignupEventRequest userSignupEventRequest) {

        // get user entity
        User user = userSignupEventRequest.toEntity();

        // user add
        users.add(user);
    }

    // get total users
    public List<UserInfoGetResponse> getTotalUsers() {

        // get total users
        List<UserInfoGetResponse> userInfoGetResponse = users.stream()
                .map(user -> UserInfoGetResponse.builder()
                        .user(user).build())
                .collect(Collectors.toList());

        return userInfoGetResponse;
    }

    // create mock user data
    public void createMockData() throws IOException, ParseException {

        // get mock data json file
        ClassPathResource cpr = new ClassPathResource("mock-data/user-mock-data.json");
        String json = new String(FileCopyUtils.copyToByteArray(cpr.getInputStream()), StandardCharsets.UTF_8);

        // parsing json
        JSONArray mockUsers = (JSONArray) new JSONParser().parse(json);

        // add mock user data
        for (Object mockUserObjet : mockUsers) {

            JSONObject mockUser = (JSONObject) mockUserObjet;

            // create mock user entity
            User user = User.builder()
                    .id(UUID.fromString((String) mockUser.get("id")))
                    .email((String) mockUser.get("email"))
                    .name((String) mockUser.get("name"))
                    .membershipLevel(MembershipLevel.findByCode((Long) mockUser.get("membershipLevel")))
                    .isSmileClubMember(Boolean.parseBoolean((String) mockUser.get("isSmileClubMember")))
                    .createdAt(LocalDateTime.parse((String) mockUser.get("createdAt"), BaseLocalDateTimeFormatter.getLocalTimeFormatter())).build();

            // mock user data add
            users.add(user);
        }

    }


}
