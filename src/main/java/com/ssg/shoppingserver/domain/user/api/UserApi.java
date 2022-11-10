package com.ssg.shoppingserver.domain.user.api;

import com.ssg.shoppingserver.domain.user.application.UserService;
import com.ssg.shoppingserver.domain.user.dto.UserTotalInfoGetResponse;
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
public class UserApi {

    private final UserService userService;

    // user signup event creation api
    @PostMapping("/user/signup")
    public void sigUp(@RequestBody @Valid UserSignupEventRequest userSignupEventRequest){
        userService.signup(userSignupEventRequest);
    }

    // user list get api
    @GetMapping("/users")
    public List<UserTotalInfoGetResponse> getUsers(){
        return userService.getTotalUsers();
    }

}
