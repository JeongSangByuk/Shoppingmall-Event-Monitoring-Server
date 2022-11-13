package com.ssg.shoppingserver.domain.user.api;

import com.ssg.shoppingserver.domain.user.application.UserSearchService;
import com.ssg.shoppingserver.domain.user.domain.User;
import com.ssg.shoppingserver.domain.user.dto.UserInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.UserSearchRequest;
import com.ssg.shoppingserver.domain.user.dto.UserSignupEventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserSearchApi {

    private final UserSearchService userSearchService;

    @PostMapping("/users/searching")
    private List<UserInfoGetResponse> getUsers(@RequestBody @Valid UserSearchRequest userSearchRequest) {

        return userSearchService.searchUser(userSearchRequest);
    }

}
