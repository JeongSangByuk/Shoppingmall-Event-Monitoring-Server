package com.ssg.shoppingserver.domain.user.dto;

import com.ssg.shoppingserver.domain.user.domain.MembershipLevel;
import com.ssg.shoppingserver.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserGetResponse {

    // User 고유 id
    private UUID id;

    // User email
    private String email;

    // User 이름
    private String name;

    // User 멤버십 레벨
    private MembershipLevel membershipLevel;

    // User SSG Smile Club 회원 여부
    private boolean isSmileClubMember;

    @Builder
    public UserGetResponse(UUID id, String email, String name, MembershipLevel membershipLevel, boolean isSmileClubMember) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.membershipLevel = membershipLevel;
        this.isSmileClubMember = isSmileClubMember;
    }
}
