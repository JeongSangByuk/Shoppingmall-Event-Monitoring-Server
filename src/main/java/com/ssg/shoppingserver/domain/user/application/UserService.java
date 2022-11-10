package com.ssg.shoppingserver.domain.user.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.domain.user.dto.UserGetResponse;
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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private Queue<User> users = new ConcurrentLinkedQueue<User>();

    @PostConstruct
    private void init() throws IOException, ParseException {
        createMockData();

        log.info(String.valueOf(users.size()));
    }


    public void signup(UserSignupEventRequest userSignupEventRequest) {

        User user = userSignupEventRequest.toEntity();

        // user add
        users.add(user);
    }

    public List<UserGetResponse> getUsers() {

        List<UserGetResponse> userGetResponseList = users.stream()
                .map(user -> UserGetResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .membershipLevel(user.getMembershipLevel())
                        .isSmileClubMember(user.isSmileClubMember()).build())
                .collect(Collectors.toList());

        return userGetResponseList;
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
            User build = User.builder()
                    .id(UUID.randomUUID())
                    .email((String) mockUser.get("email"))
                    .name((String) mockUser.get("name"))
                    .membershipLevel(MembershipLevel.valueOf((String)mockUser.get("membershipLevel")))
                    .isSmileClubMember( Boolean.parseBoolean((String) mockUser.get("isSmileClubMember"))).build();

            // mock user data add
            users.add(build);
        }


    }


}
