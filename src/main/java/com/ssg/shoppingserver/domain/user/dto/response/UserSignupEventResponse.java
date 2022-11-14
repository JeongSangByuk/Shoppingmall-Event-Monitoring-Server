package com.ssg.shoppingserver.domain.user.dto.response;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserSignupEventResponse {

    // User email
    private String email;

    // User 이름
    private String name;

    @Builder
    public UserSignupEventResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
