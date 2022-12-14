package com.ssg.shoppingserver.domain.user.api;

import com.ssg.shoppingserver.domain.user.application.UserService;
import com.ssg.shoppingserver.domain.user.dto.response.UserInfoGetResponse;
import com.ssg.shoppingserver.domain.user.dto.request.UserSignupEventRequest;
import com.ssg.shoppingserver.domain.user.dto.response.UserSignupEventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    // total user list get api
    @GetMapping("/users")
    public List<UserInfoGetResponse> getUsers(){
        return userService.getTotalUsers();
    }

    // user signup event creation api
    @PostMapping("/user/signup")
    public UserSignupEventResponse sigUp(@RequestBody @Valid UserSignupEventRequest userSignupEventRequest){
        return userService.signup(userSignupEventRequest);
    }

}
