package com.ssg.shoppingserver.domain.user.application;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.domain.user.dto.UserTotalInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.UserSignupEventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

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
    public List<UserTotalInfoGetResponse> getTotalUsers() {

        // get total users
        List<UserTotalInfoGetResponse> userTotalInfoGetResponses = users.stream()
                .map(user -> UserTotalInfoGetResponse.builder()
                        .user(user).build())
                .collect(Collectors.toList());

        return userTotalInfoGetResponses;
    }

    // create mock user data
    public void createMockData() throws IOException, ParseException {

        // get mock data json file
        ClassPathResource resource = new ClassPathResource("mock-data/user-mock-data.json");
        Path path = Paths.get(resource.getURI());
        String json = Files.readString(path);

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
                    .isSmileClubMember(Boolean.parseBoolean((String) mockUser.get("isSmileClubMember"))).build();

            // mock user data add
            users.add(user);
        }

    }


}
