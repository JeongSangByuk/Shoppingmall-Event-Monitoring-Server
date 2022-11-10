package com.ssg.shoppingserver.domain.user.dto;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserSignupEventRequest {

    // User email
    @NotBlank
    private String email;

    // User 이름
    @NotBlank
    private String name;

    // User 멤버십 레벨
    @NotNull
    private MembershipLevel membershipLevel;

    // User SSG Smile Club 회원 여부
    @NotNull
    private boolean isSmileClubMember;

    // dto to entity
    public User toEntity() {
        return User.builder()
                .id(UUID.randomUUID())
                .email(this.email)
                .name(this.name)
                .membershipLevel(this.membershipLevel)
                .isSmileClubMember(this.isSmileClubMember)
                .build();
    }
}
