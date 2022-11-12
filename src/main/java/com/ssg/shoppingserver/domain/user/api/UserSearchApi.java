package com.ssg.shoppingserver.domain.user.api;

import com.ssg.shoppingserver.domain.user.application.UserSearchService;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.domain.user.dto.UserInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.UserSearchRequest;
import com.ssg.shoppingserver.domain.user.dto.UserSignupEventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserSearchApi {

    private final UserSearchService userSearchService;

    @GetMapping("/users/searching")
    private List<UserInfoGetResponse> getTest(@RequestBody @Valid UserSearchRequest userSearchRequest) {

        return userSearchService.searchUser(userSearchRequest);
    }

}
